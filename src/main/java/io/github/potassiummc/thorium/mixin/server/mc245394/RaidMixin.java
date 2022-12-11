package io.github.potassiummc.thorium.mixin.server.mc245394;

import net.minecraft.sound.SoundCategory;
import net.minecraft.village.raid.Raid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Raid.class)
public class RaidMixin {

    @ModifyArg(method = "playRaidHorn(Lnet/minecraft/util/math/BlockPos;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/s2c/play/PlaySoundS2CPacket;<init>(Lnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/sound/SoundCategory;DDDFFJ)V"), index = 1)
    private SoundCategory playRaidHornMakeSoundPacket(SoundCategory sound) {
        return SoundCategory.HOSTILE;
    }

}
