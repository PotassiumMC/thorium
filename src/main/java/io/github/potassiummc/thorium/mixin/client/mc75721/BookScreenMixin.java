package io.github.potassiummc.thorium.mixin.client.mc75721;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BookScreen.class)
public abstract class BookScreenMixin extends Screen {

    protected BookScreenMixin(Text title) {
        super(title);
    }

    // Based on code analysis by Tobi14601: https://bugs.mojang.com/browse/MC-75721?focusedCommentId=920466#comment-920466
    @Redirect(method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V"))
    private void renderSuperAfterHover(Screen instance, MatrixStack matrices, int mouseX, int mouseY, float delta) {
    }

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/BookScreen;getTextStyleAt(DD)Lnet/minecraft/text/Style;", shift = At.Shift.BEFORE))
    private void renderSuperBeforeHover(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        super.render(matrices, mouseX, mouseY, delta);
    }

}
