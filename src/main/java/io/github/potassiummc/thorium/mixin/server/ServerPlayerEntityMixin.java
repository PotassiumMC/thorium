package io.github.potassiummc.thorium.mixin.server;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    // Fix MC-6431
    // Based on code analysis by PR0CESS: https://bugs.mojang.com/browse/MC-6431?focusedCommentId=1081896#comment-1081896
    @Inject(method = "copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;setScore(I)V", shift = At.Shift.AFTER))
    private void copyFromAddEffects(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        ((ServerPlayerEntity) (Object) this).getActiveStatusEffects().putAll(oldPlayer.getActiveStatusEffects());
    }

    // Fix MC-81773, MC-129909 and MC-206705
    @Inject(method = "changeGameMode(Lnet/minecraft/world/GameMode;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;stopRiding()V", shift = At.Shift.AFTER))
    private void changeGameModeStopInteracting(GameMode gameMode, CallbackInfoReturnable<Boolean> cir) {
        ((ServerPlayerEntity) (Object) this).stopUsingItem();
    }

    // Fix MC-69216
    @Inject(method = "changeGameMode(Lnet/minecraft/world/GameMode;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;stopRiding()V", shift = At.Shift.AFTER))
    private void changeGameModeRemoveFishingHook(GameMode gameMode, CallbackInfoReturnable<Boolean> cir) {
        if (((ServerPlayerEntity) (Object) this).fishHook != null) {
            ((ServerPlayerEntity) (Object) this).fishHook.discard();
        }
    }

    // Fix MC-119417
    @Inject(method = "changeGameMode(Lnet/minecraft/world/GameMode;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;stopRiding()V", shift = At.Shift.AFTER))
    private void changeGameModeWakeUp(GameMode gameMode, CallbackInfoReturnable<Boolean> cir) {
        if (((ServerPlayerEntity) (Object) this).isSleeping()) {
            ((ServerPlayerEntity) (Object) this).wakeUp(true, true);
        }
    }

    // Fix MC-193343
    @Inject(method = "changeGameMode(Lnet/minecraft/world/GameMode;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;stopRiding()V", shift = At.Shift.AFTER))
    private void changeGameModeRemoveSoulSpeed(GameMode gameMode, CallbackInfoReturnable<Boolean> cir) {
        ((LivingEntityInvoker) this).invokeRemoveSoulSpeedBoost();
    }

    // Fix MC-215530
    @Inject(method = "changeGameMode(Lnet/minecraft/world/GameMode;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;stopRiding()V", shift = At.Shift.AFTER))
    private void changeGameModeResetFrozenTicks(GameMode gameMode, CallbackInfoReturnable<Boolean> cir) {
        ((ServerPlayerEntity) (Object) this).setFrozenTicks(0);
    }

    // Fix MC-86252, MC-195732 and MC-212926
    @Inject(method = "moveToWorld(Lnet/minecraft/server/world/ServerWorld;)Lnet/minecraft/entity/Entity;", at = @At("RETURN"))
    private void moveToWorldStopUsingItem(ServerWorld destination, CallbackInfoReturnable<Entity> cir) {
        ((ServerPlayerEntity) (Object) this).stopUsingItem();
    }

}
