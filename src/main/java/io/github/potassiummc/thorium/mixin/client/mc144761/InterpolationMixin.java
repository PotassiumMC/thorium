package io.github.potassiummc.thorium.mixin.client.mc144761;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.SpriteContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(targets = "net.minecraft.client.texture.SpriteContents$Interpolation", priority = 1100)
public abstract class InterpolationMixin {

    @Final
    @Shadow
    private NativeImage[] images;

    @Shadow
    protected abstract int lerp(double delta, int to, int from);

    // Based on code analysis by gudenau: https://bugs.mojang.com/browse/MC-144761?focusedCommentId=780425#comment-780425
    @Redirect(method = "apply(IILnet/minecraft/client/texture/SpriteContents$AnimatorImpl;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/NativeImage;setColor(III)V"))
    public void cancelSetColor(NativeImage instance, int x, int y, int color) {
    }

    @Inject(method = "apply(IILnet/minecraft/client/texture/SpriteContents$AnimatorImpl;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/NativeImage;setColor(III)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    public void setColorWithLerpedAlpha(int a, int b, SpriteContents.AnimatorImpl animator, CallbackInfo ci, SpriteContents.Animation animation, List<SpriteContents.AnimationFrame> list, SpriteContents.AnimationFrame animationFrame, double delta, int i, int j, int k, int l, int m, int y, int x, int dest, int source, int red, int green, int blue) {
        int alpha = lerp(delta, (dest >>> 24) & 255, (source >>> 24) & 255);
        images[k].setColor(x, y, (alpha << 24) | (red << 16) | (green << 8) | blue);
    }

}
