package io.github.potassiummc.thorium.mixin.client.mc147766;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextFieldWidget.class)
public class TextFieldWidgetMixin {

    @Shadow
    private boolean selecting;

    @Inject(method = "setCursor(I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setSelectionStart(I)V", shift = At.Shift.AFTER))
    private void setCursorSetSelectionEnd(int cursor, CallbackInfo ci) {
        this.selecting = Screen.hasShiftDown();
    }

}
