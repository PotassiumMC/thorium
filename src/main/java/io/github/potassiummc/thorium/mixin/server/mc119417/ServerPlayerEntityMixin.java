package io.github.potassiummc.thorium.mixin.server.mc119417;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(method = "changeGameMode(Lnet/minecraft/world/GameMode;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;stopRiding()V", shift = At.Shift.AFTER))
    private void changeGameModeWakeUp(GameMode gameMode, CallbackInfoReturnable<Boolean> cir) {
        if (((ServerPlayerEntity) (Object) this).isSleeping()) {
            ((ServerPlayerEntity) (Object) this).wakeUp(true, true);
        }
    }

}
