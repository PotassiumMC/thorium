package io.github.potassiummc.thorium.mixin.server;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BundleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BundleItem.class)
public abstract class BundleItemMixin {

    @Shadow
    protected abstract void playRemoveOneSound(Entity entity);

    // Fix MC-244948
    @Redirect(method = "onStackClicked(Lnet/minecraft/item/ItemStack;Lnet/minecraft/screen/slot/Slot;Lnet/minecraft/util/ClickType;Lnet/minecraft/entity/player/PlayerEntity;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BundleItem;playRemoveOneSound(Lnet/minecraft/entity/Entity;)V"))
    private void voidOriginalClickSound(BundleItem instance, Entity entity) {
    }

    @Inject(method = "onStackClicked(Lnet/minecraft/item/ItemStack;Lnet/minecraft/screen/slot/Slot;Lnet/minecraft/util/ClickType;Lnet/minecraft/entity/player/PlayerEntity;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BundleItem;playRemoveOneSound(Lnet/minecraft/entity/Entity;)V"))
    private void onlyPlayClickSoundIfBundleHasItems(ItemStack bundle, Slot slot, ClickType clickType, PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        NbtCompound nbtCompound = bundle.getOrCreateNbt();
        if (!(nbtCompound.get("Items") instanceof NbtList items)) return;
        if (items.isEmpty()) return;

        playRemoveOneSound(player);
    }

}
