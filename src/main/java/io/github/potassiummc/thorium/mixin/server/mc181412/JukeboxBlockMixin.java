package io.github.potassiummc.thorium.mixin.server.mc181412;

import net.minecraft.block.JukeboxBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(JukeboxBlock.class)
public class JukeboxBlockMixin {

    @Redirect(method = "removeRecord(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;syncWorldEvent(ILnet/minecraft/util/math/BlockPos;I)V"))
    private void cancelLateWorldEvent(World instance, int i, BlockPos blockPos, int j) {
    }

    @Inject(method = "removeRecord(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/JukeboxBlockEntity;getRecord()Lnet/minecraft/item/ItemStack;", shift = At.Shift.BEFORE))
    private void callWorldEventEarlier(World world, BlockPos pos, CallbackInfo ci) {
        world.syncWorldEvent(1010, pos, 0);
    }

}
