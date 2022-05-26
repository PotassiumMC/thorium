package io.github.potassiummc.thorium.mixin.server.mc225364;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChorusFlowerBlock.class)
public class ChorusFlowerBlockMixin {

    @Inject(method = "onProjectileHit(Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/hit/BlockHitResult;Lnet/minecraft/entity/projectile/ProjectileEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;breakBlock(Lnet/minecraft/util/math/BlockPos;ZLnet/minecraft/entity/Entity;)Z", shift = At.Shift.BEFORE), cancellable = true)
    private void onProjectileHitCheckMayBreak(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile, CallbackInfo ci) {
        if (!(projectile.getOwner() instanceof PlayerEntity player)) return;
        if (player.getAbilities().allowModifyWorld) return;

        // Cancel if we aren't allowed to build.
        ci.cancel();
    }

}
