package io.github.potassiummc.thorium.mixin.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.telemetry.TelemetrySender;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

	// Fix MC-12062 and MC-143474
	@Inject(method = "onPlayerRespawn(Lnet/minecraft/network/packet/s2c/play/PlayerRespawnS2CPacket;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;setId(I)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
	private void onPlayerRespawnSetInventorySlot(PlayerRespawnS2CPacket packet, CallbackInfo ci, RegistryKey<?> registryKey, RegistryEntry<?> registryEntry, ClientPlayerEntity oldClientPlayerEntity, int i, String string, ClientPlayerEntity newClientPlayerEntity) {
		newClientPlayerEntity.getInventory().selectedSlot = oldClientPlayerEntity.getInventory().selectedSlot;
	}

	// Fix MC-46503
	// Maybe servers somehow rely on this behaviour? If your server (ab)uses this bug, please make a GH issue.
	@Inject(method = "onPlayerRespawn(Lnet/minecraft/network/packet/s2c/play/PlayerRespawnS2CPacket;)V", at = @At("TAIL"))
	private void onPlayerRespawnOnCameraEntitySet(PlayerRespawnS2CPacket packet, CallbackInfo ci) {
		MinecraftClient client = MinecraftClient.getInstance();
		client.gameRenderer.onCameraEntitySet(client.player);
	}

	// Fix MC-55347
	// We reset the title in the ctor, because the GameJoin packet is sent when the player switches worlds and proxied servers.
	@Inject(method = "<init>", at = @At("TAIL"))
	private void onInitResetTitle(MinecraftClient client, Screen screen, ClientConnection connection, GameProfile profile, TelemetrySender telemetrySender, CallbackInfo ci) {
		client.inGameHud.clearTitle();
	}

}
