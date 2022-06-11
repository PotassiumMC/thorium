package io.github.potassiummc.thorium.mixin.client.mc168016;

import net.minecraft.client.gui.widget.PageTurnWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PageTurnWidget.class)
public class PageTurnWidgetMixin {

    @Redirect(method = "playDownSound(Lnet/minecraft/client/sound/SoundManager;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sound/PositionedSoundInstance;master(Lnet/minecraft/sound/SoundEvent;F)Lnet/minecraft/client/sound/PositionedSoundInstance;"))
    public PositionedSoundInstance playDownSoundGetSound(SoundEvent sound, float pitch) {
        return new PositionedSoundInstance(sound.getId(), SoundCategory.PLAYERS, 0.25F, pitch, SoundInstance.createRandom(), false, 0, SoundInstance.AttenuationType.LINEAR, 0.0D, 0.0D, 0.0D, true);
    }

}
