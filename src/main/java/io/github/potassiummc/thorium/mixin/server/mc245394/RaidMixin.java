package io.github.potassiummc.thorium.mixin.server;

import net.minecraft.sound.SoundCategory;
import net.minecraft.village.raid.Raid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Raid.class)
public class RaidMixin {

    // Fix MC-245394
    @ModifyArg(method = "playRaidHorn(Lnet/minecraft/util/math/BlockPos;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/s2c/play/PlaySoundS2CPacket;<init>(Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;DDDFF)V"), index = 1)
    private SoundCategory playRaidHornMakeSoundPacket(SoundCategory sound) {
        return SoundCategory.HOSTILE;
    }

}
