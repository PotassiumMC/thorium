package io.github.potassiummc.thorium.mixin.client;

import io.github.potassiummc.thorium.mixin.server.EntityAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.tutorial.TutorialManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

	// Fix MC-35361
	@Redirect(method = "handleInputEvents()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/tutorial/TutorialManager;onInventoryOpened()V"))
	private void handleInputEventsOnInventoryOpened(TutorialManager instance) {
		ClientPlayerEntity me = ((MinecraftClient) (Object) this).player;
		// Don't call the 'event' if we're in a nether portal.
		if (((EntityAccessor) me).inNetherPortal()) return;

		instance.onInventoryOpened();
	}

	// Fix MC-46766
	@Redirect(method = "handleBlockBreaking(Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isAir()Z"))
	private boolean handleBlockBreakingIsAirOrSpectator(BlockState instance) {
		ClientPlayerEntity me = ((MinecraftClient) (Object) this).player;
		// Pretend the block is air if we're a spectator.
		return instance.isAir() || me.isSpectator();
	}

}
