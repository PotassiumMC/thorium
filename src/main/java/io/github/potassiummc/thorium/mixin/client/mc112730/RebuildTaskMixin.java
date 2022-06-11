package io.github.potassiummc.thorium.mixin.client.mc112730;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.chunk.ChunkBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(net.minecraft.client.render.chunk.ChunkBuilder.BuiltChunk.RebuildTask.class)
public class RebuildTaskMixin {

    @Redirect(method = "addBlockEntity(Lnet/minecraft/client/render/chunk/ChunkBuilder$BuiltChunk$RebuildTask$RenderData;Lnet/minecraft/block/entity/BlockEntity;)V", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    public boolean cancelBlockEntitiesAdd(List<?> instance, Object e) {
        return false;
    }

    @Inject(method = "addBlockEntity(Lnet/minecraft/client/render/chunk/ChunkBuilder$BuiltChunk$RebuildTask$RenderData;Lnet/minecraft/block/entity/BlockEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/entity/BlockEntityRenderer;rendersOutsideBoundingBox(Lnet/minecraft/block/entity/BlockEntity;)Z", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    public <E extends BlockEntity> void addToChunkBlockEntityList(ChunkBuilder.BuiltChunk.RebuildTask.RenderData renderData, E blockEntity, CallbackInfo ci, BlockEntityRenderer<E> blockEntityRenderer) {
        // Only add it to the *chunk* block entity list if it isn't already in the block entity renderer list.
        if (blockEntityRenderer.rendersOutsideBoundingBox(blockEntity)) {
            return;
        }
        renderData.blockEntities.add(blockEntity);
    }


}
