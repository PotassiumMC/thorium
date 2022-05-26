package io.github.potassiummc.thorium.mixin.server.mc6431;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    // Based on code analysis by PR0CESS: https://bugs.mojang.com/browse/MC-6431?focusedCommentId=1081896#comment-1081896
    @Inject(method = "copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;setScore(I)V", shift = At.Shift.AFTER))
    private void copyFromAddEffects(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        ((ServerPlayerEntity) (Object) this).getActiveStatusEffects().putAll(oldPlayer.getActiveStatusEffects());
    }

}
