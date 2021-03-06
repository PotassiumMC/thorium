package io.github.potassiummc.thorium.mixin.server.mc170462;

import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(StatusEffects.class)
public class StatusEffectsMixin {

    @ModifyArg(method = "<clinit>()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffects$1;<init>(Lnet/minecraft/entity/effect/StatusEffectCategory;I)V", ordinal = 0), index = 0)
    private static StatusEffectCategory createBadOmenEffectSetCategory(StatusEffectCategory category) {
        return StatusEffectCategory.HARMFUL;
    }

}
