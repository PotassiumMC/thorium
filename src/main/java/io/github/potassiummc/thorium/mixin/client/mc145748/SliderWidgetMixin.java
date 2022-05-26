package io.github.potassiummc.thorium.mixin.client.mc145748;

import net.minecraft.client.gui.widget.SliderWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SliderWidget.class)
public class SliderWidgetMixin {

    private boolean wasOpened = false;

    @Inject(method = "onClick(DD)V", at = @At("TAIL"))
    public void onClickSetWasOpened(double mouseX, double mouseY, CallbackInfo ci) {
        wasOpened = true;
    }

    @Inject(method = "onRelease(DD)V", at = @At("HEAD"), cancellable = true)
    public void onReleaseCancelIfNotOpened(double mouseX, double mouseY, CallbackInfo ci) {
        if (wasOpened) return;
        ci.cancel();
    }

}
