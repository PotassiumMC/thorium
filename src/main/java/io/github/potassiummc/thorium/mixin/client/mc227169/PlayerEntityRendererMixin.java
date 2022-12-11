package io.github.potassiummc.thorium.mixin.client.mc227169;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlayerEntityRenderer.class, priority = 1100)
public class PlayerEntityRendererMixin {

    @Inject(method = "getArmPose(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;CROSSBOW_HOLD:Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;"), cancellable = true)
    private static void setArmPoseIfOffHand(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        // This issue happens because the game has already decided the hand position for the main hand before deciding
        // the crossbow orientation for the offhand. Because of this, the main hand will still appear, but in the wrong
        // rotation, which is intended to only be seen in third-person mode.
        // Thus, we should also keep the current arm pose if this is a different player, or if we're in third person mode.
        if (hand == Hand.MAIN_HAND || !player.isMainPlayer() || !MinecraftClient.getInstance().options.getPerspective().isFirstPerson()) {
            return;
        }

        cir.setReturnValue(BipedEntityModel.ArmPose.ITEM);
    }

}
