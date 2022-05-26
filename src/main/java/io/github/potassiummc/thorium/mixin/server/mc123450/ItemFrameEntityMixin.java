package io.github.potassiummc.thorium.mixin.server.mc123450;

import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemFrameEntity.class)
public class ItemFrameEntityMixin {

    @Redirect(method = "setHeldItemStack(Lnet/minecraft/item/ItemStack;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ItemFrameEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V"))
    private void setHeldItemStackPlaySound(ItemFrameEntity instance, SoundEvent soundEvent, float v, float p, ItemStack value, boolean update) {
        if (!update) return;
        instance.playSound(soundEvent, v, p);
    }

}
