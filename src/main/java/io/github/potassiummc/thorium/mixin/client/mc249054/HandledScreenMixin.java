package io.github.potassiummc.thorium.mixin.client.mc249054;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HandledScreen.class)
public class HandledScreenMixin {

    @Redirect(method = "mouseClicked(DDI)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V"))
    public void properlyCloseHandledScreen(MinecraftClient instance, Screen screen) {
        ((HandledScreen<?>) (Object) this).close();
    }

}
