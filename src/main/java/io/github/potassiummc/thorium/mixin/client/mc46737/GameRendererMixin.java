package io.github.potassiummc.thorium.mixin.client;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Shadow
    protected abstract void loadShader(Identifier id);

    // Fix MC-46737
    // Maybe servers somehow rely on this behaviour? If your server (ab)uses this bug, please make a GH issue.
    @Redirect(method = "onCameraEntitySet(Lnet/minecraft/entity/Entity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;loadShader(Lnet/minecraft/util/Identifier;)V"))
    private void onCameraEntitySetEarlyExit(GameRenderer instance, Identifier id) {
        if (!instance.getClient().options.getPerspective().isFirstPerson()) return;
        this.loadShader(id);
    }

}
