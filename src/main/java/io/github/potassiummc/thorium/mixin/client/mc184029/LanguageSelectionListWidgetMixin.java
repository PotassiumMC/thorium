package io.github.potassiummc.thorium.mixin.client.mc184029;

import io.github.potassiummc.thorium.access.LanguageSelectionListWidgetAccess;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LanguageOptionsScreen.LanguageSelectionListWidget.class)
public class LanguageSelectionListWidgetMixin implements LanguageSelectionListWidgetAccess {

    @Final
    @Shadow
    LanguageOptionsScreen field_18744;

    public LanguageOptionsScreen getLanguageOptionsScreen() {
        return field_18744;
    }

}
