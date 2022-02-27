package io.github.potassiummc.thorium.mixin.client;

import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.render.entity.model.CowEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CowEntityModel.class)
public class CowEntityModelMixin {

	// Fix MC-246257
	@ModifyArg(method = "getTexturedModelData()Lnet/minecraft/client/model/TexturedModelData;", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPartData;addChild(Ljava/lang/String;Lnet/minecraft/client/model/ModelPartBuilder;Lnet/minecraft/client/model/ModelTransform;)Lnet/minecraft/client/model/ModelPartData;", ordinal = 0), index = 1)
	private static ModelPartBuilder getTexturedModelDataInvertRightHorn(ModelPartBuilder builder) {
		return ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F).uv(22, 0).mirrored().cuboid("right_horn", -5.0F, -5.0F, -4.0F, 1.0F, 3.0F, 1.0F).mirrored(false).uv(22, 0).cuboid("left_horn", 4.0F, -5.0F, -4.0F, 1.0F, 3.0F, 1.0F);
	}

}
