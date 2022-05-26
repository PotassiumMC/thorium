package io.github.potassiummc.thorium.mixin.server.mc84661;

import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(StatusEffects.class)
public class StatusEffectsMixin {

    @ModifyArg(method = "<clinit>()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffect;<init>(Lnet/minecraft/entity/effect/StatusEffectCategory;I)V", ordinal = 16), index = 0)
    private static StatusEffectCategory createGlowingEffectSetCategory(StatusEffectCategory category) {
        return StatusEffectCategory.HARMFUL;
    }

}
