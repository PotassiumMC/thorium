package io.github.potassiummc.thorium.mixin.client.mc12062;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    // Also fixes MC-143474
    @Inject(method = "onPlayerRespawn(Lnet/minecraft/network/packet/s2c/play/PlayerRespawnS2CPacket;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;setId(I)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void onPlayerRespawnSetInventorySlot(PlayerRespawnS2CPacket packet, CallbackInfo ci, RegistryKey<?> registryKey, RegistryEntry<?> registryEntry, ClientPlayerEntity oldClientPlayerEntity, int i, String string, ClientPlayerEntity newClientPlayerEntity) {
        newClientPlayerEntity.getInventory().selectedSlot = oldClientPlayerEntity.getInventory().selectedSlot;
    }

}
