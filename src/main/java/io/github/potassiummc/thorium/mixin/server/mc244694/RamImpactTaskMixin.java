package io.github.potassiummc.thorium.mixin.server.mc244694;

import net.minecraft.entity.ai.brain.task.RamImpactTask;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(RamImpactTask.class)
public class RamImpactTaskMixin {

    @ModifyArg(method = "keepRunning(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/GoatEntity;J)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;playSoundFromEntity(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"), index = 3, require = 3)
    private SoundCategory playRaidHornMakeSoundPacket(SoundCategory sound) {
        return SoundCategory.NEUTRAL;
    }

}
