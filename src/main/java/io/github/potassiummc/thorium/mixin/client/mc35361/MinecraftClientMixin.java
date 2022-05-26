package io.github.potassiummc.thorium.mixin.client.mc35361;

import io.github.potassiummc.thorium.mixin.access.EntityAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.tutorial.TutorialManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Redirect(method = "handleInputEvents()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/tutorial/TutorialManager;onInventoryOpened()V"))
    private void handleInputEventsOnInventoryOpened(TutorialManager instance) {
        ClientPlayerEntity me = ((MinecraftClient) (Object) this).player;
        // Don't call the 'event' if we're in a nether portal.
        if (((EntityAccessor) me).inNetherPortal()) return;

        instance.onInventoryOpened();
    }

}
