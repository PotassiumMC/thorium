package io.github.potassiummc.thorium.mixin.server.mc139041;

import net.minecraft.item.FishingRodItem;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FishingRodItem.class)
public class FishingRodItemMixin {

    @ModifyArg(method = "use(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/TypedActionResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"), index = 5)
    private SoundCategory playRaidHornMakeSoundPacket(SoundCategory sound) {
        return SoundCategory.PLAYERS;
    }

}
