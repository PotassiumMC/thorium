package io.github.potassiummc.thorium.mixin.server;

import net.minecraft.entity.LivingEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    
    // Maybe servers somehow rely on this behaviour? If your server (ab)uses this bug, please make a GH issue.
    @Redirect(method = "updatePostDeath()V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/LivingEntity;deathTime:I", opcode = Opcodes.GETFIELD, ordinal = 1))
    private int updatePostDeathGetDeathTime(LivingEntity instance) {
        // The check explicitly checks == "20", instead of >=, so we just return 20 if it's higher.
        return Math.min(instance.deathTime, 20);
    }

}
