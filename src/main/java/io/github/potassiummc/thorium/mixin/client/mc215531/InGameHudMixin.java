package io.github.potassiummc.thorium.mixin.client.mc215531;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.option.Perspective;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Final
    @Shadow
    private MinecraftClient client;

    // Maybe servers somehow rely on this behaviour? If your server (ab)uses this bug, please make a GH issue.
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/Perspective;isFirstPerson()Z"))
    private boolean renderIsFirstPersonOrSpectator(Perspective instance) {
        return instance.isFirstPerson() && !client.player.isSpectator();
    }

}
