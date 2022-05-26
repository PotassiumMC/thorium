package io.github.potassiummc.thorium.mixin.server.mc6431;

import net.minecraft.network.packet.s2c.play.EntityStatusEffectS2CPacket;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    // Based on code analysis by PR0CESS: https://bugs.mojang.com/browse/MC-6431?focusedCommentId=1081896#comment-1081896
    @Inject(method = "respawnPlayer(Lnet/minecraft/server/network/ServerPlayerEntity;Z)Lnet/minecraft/server/network/ServerPlayerEntity;", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;sendWorldInfo(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/server/world/ServerWorld;)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private void respawnPlayerAddEffects(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfoReturnable<ServerPlayerEntity> cir, BlockPos blockPos, float f, boolean bl, ServerWorld serverWorld, Optional<?> optional, ServerWorld serverWorld2, ServerPlayerEntity newPlayer, boolean bl2, WorldProperties worldProperties) {
        newPlayer.getActiveStatusEffects().forEach((name, eff) -> newPlayer.networkHandler.sendPacket(new EntityStatusEffectS2CPacket(newPlayer.getId(), eff)));
    }

}
