package io.github.potassiummc.thorium.mixin.client.mc184029;

import io.github.potassiummc.thorium.mixin.access.LanguageSelectionListWidgetAccess;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class LanguageOptionsScreenMixin {

    @Mixin(LanguageOptionsScreen.LanguageSelectionListWidget.class)
    public static class LanguageSelectionListWidgetMixin implements LanguageSelectionListWidgetAccess {

        @Shadow
        @Final
        LanguageOptionsScreen field_18744;

        public LanguageOptionsScreen getLanguageOptionsScreen() {
            return field_18744;
        }

    }

    @Mixin(LanguageOptionsScreen.LanguageSelectionListWidget.LanguageEntry.class)
    public static class LanguageEntryMixin {

        @Final
        @Shadow
        LanguageOptionsScreen.LanguageSelectionListWidget field_19100;

        @Inject(method = "onPressed()V", at = @At("TAIL"))
        public void onPressedUnfocusOtherButtons(CallbackInfo ci) {
            Element focused = ((LanguageSelectionListWidgetAccess) field_19100).getLanguageOptionsScreen().getFocused();
            if (focused == null) return;
            focused.changeFocus(false);
        }
    }

}
