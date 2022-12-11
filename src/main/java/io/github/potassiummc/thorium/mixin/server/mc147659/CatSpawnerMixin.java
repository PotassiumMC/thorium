package io.github.potassiummc.thorium.mixin.server.mc147659;

import net.minecraft.entity.passive.CatEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.spawner.CatSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CatSpawner.class)
public class CatSpawnerMixin {

    @Redirect(method = "spawn(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/server/world/ServerWorld;)I", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/CatEntity;refreshPositionAndAngles(Lnet/minecraft/util/math/BlockPos;FF)V"))
    public void spawnCancelLateRefreshPos(CatEntity instance, BlockPos blockPos, float a, float b) {
    }

    @Inject(method = "spawn(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/server/world/ServerWorld;)I", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/CatEntity;initialize(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/world/LocalDifficulty;Lnet/minecraft/entity/SpawnReason;Lnet/minecraft/entity/EntityData;Lnet/minecraft/nbt/NbtCompound;)Lnet/minecraft/entity/EntityData;", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    public void spawnRefreshPosBeforeInit(BlockPos pos, ServerWorld world, CallbackInfoReturnable<Integer> cir, CatEntity catEntity) {
        catEntity.refreshPositionAndAngles(pos, 0.0F, 0.0F);
    }

}
