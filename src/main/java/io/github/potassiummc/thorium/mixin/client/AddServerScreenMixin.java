package io.github.potassiummc.thorium.mixin.client;

import net.minecraft.client.gui.screen.AddServerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AddServerScreen.class)
public class AddServerScreenMixin {

    @Shadow
    private TextFieldWidget serverNameField;

    // Fix MC-151412
    // Based on code analysis by null: https://bugs.mojang.com/browse/MC-151412?focusedCommentId=1029727#comment-1029727
    @Inject(method = "init()V", at = @At("TAIL"))
    private void initSetFocus(CallbackInfo ci) {
        this.serverNameField.setTextFieldFocused(false);
        ((AddServerScreen) (Object) this).setInitialFocus(this.serverNameField);
    }

}
