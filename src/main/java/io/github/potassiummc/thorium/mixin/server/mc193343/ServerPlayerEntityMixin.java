package io.github.potassiummc.thorium.mixin.server.mc193343;

import io.github.potassiummc.thorium.mixin.access.LivingEntityInvoker;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(method = "changeGameMode(Lnet/minecraft/world/GameMode;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;stopRiding()V", shift = At.Shift.AFTER))
    private void changeGameModeRemoveSoulSpeed(GameMode gameMode, CallbackInfoReturnable<Boolean> cir) {
        ((LivingEntityInvoker) this).invokeRemoveSoulSpeedBoost();
    }

}
