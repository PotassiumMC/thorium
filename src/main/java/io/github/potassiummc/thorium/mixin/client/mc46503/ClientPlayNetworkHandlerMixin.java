package io.github.potassiummc.thorium.mixin.client.mc46503;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    // Maybe servers somehow rely on this behaviour? If your server (ab)uses this bug, please make a GH issue.
    @Inject(method = "onPlayerRespawn(Lnet/minecraft/network/packet/s2c/play/PlayerRespawnS2CPacket;)V", at = @At("TAIL"))
    private void onPlayerRespawnOnCameraEntitySet(PlayerRespawnS2CPacket packet, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.gameRenderer.onCameraEntitySet(client.player);
    }

}
