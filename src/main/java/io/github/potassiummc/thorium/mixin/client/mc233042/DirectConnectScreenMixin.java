package io.github.potassiummc.thorium.mixin.client.mc233042;

import net.minecraft.client.gui.screen.DirectConnectScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DirectConnectScreen.class)
public class DirectConnectScreenMixin {

    // Based on code analysis by null: https://bugs.mojang.com/browse/MC-233042?focusedCommentId=1128941#comment-1128941
    @Redirect(method = "init()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setTextFieldFocused(Z)V"))
    public void initIgnoreSetTextFieldFocused(TextFieldWidget instance, boolean focused) {
    }

}
