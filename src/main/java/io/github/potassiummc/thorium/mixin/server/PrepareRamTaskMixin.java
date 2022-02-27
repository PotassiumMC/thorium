package io.github.potassiummc.thorium.mixin.server;

import net.minecraft.entity.ai.brain.task.PrepareRamTask;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PrepareRamTask.class)
public class PrepareRamTaskMixin {

	// Fix MC-244694
	@ModifyArg(method = "keepRunning(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/mob/PathAwareEntity;J)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;playSoundFromEntity(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"), index = 3)
	private SoundCategory playRaidHornMakeSoundPacket(SoundCategory sound) {
		return SoundCategory.NEUTRAL;
	}

}
