package io.github.potassiummc.thorium.mixin.client;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.Sprite;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(targets = "net.minecraft.client.texture.Sprite$Interpolation", priority = 1100)
public abstract class InterpolationMixin {

    @Final
    @Shadow
    private NativeImage[] images;

    @Shadow
    protected abstract int lerp(double delta, int to, int from);

    // Fix MC-144761
    // Based on code analysis by gudenau: https://bugs.mojang.com/browse/MC-144761?focusedCommentId=780425#comment-780425
    @Redirect(method = "apply(Lnet/minecraft/client/texture/Sprite$Animation;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/NativeImage;setColor(III)V"))
    public void cancelSetColor(NativeImage instance, int x, int y, int color) {
    }

    @Inject(method = "apply(Lnet/minecraft/client/texture/Sprite$Animation;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/NativeImage;setColor(III)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    public void setColorWithLerpedAlpha(Sprite.Animation animation, CallbackInfo ci, Sprite.AnimationFrame animationFrame, double delta, int i, int j, int k, int l, int m, int y, int x, int dest, int source, int red, int green, int blue) {
        int alpha = lerp(delta, (dest >>> 24) & 255, (source >>> 24) & 255);
        images[k].setColor(x, y, (alpha << 24) | (red << 16) | (green << 8) | blue);
    }

}
