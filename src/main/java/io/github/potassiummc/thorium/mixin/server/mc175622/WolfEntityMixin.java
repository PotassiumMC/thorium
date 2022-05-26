package io.github.potassiummc.thorium.mixin.server.mc175622;

import net.minecraft.entity.passive.WolfEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WolfEntity.class)
public class WolfEntityMixin {

    @Inject(method = "getTailAngle()F", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/WolfEntity;isTamed()Z", shift = At.Shift.BEFORE), cancellable = true)
    private void fixedGetTailAngleWhenNotAngry(CallbackInfoReturnable<Float> cir) {
        WolfEntity me = (WolfEntity) (Object) this;
        // New formula taken from tryashtar's comment: https://bugs.mojang.com/browse/MC-175622?focusedCommentId=1134847#comment-1134847
        cir.setReturnValue(me.isTamed() ? (0.15F + 0.4F * (me.getHealth() / me.getMaxHealth())) * 3.1415927F : 0.62831855F);
    }

}
