package io.github.potassiummc.thorium.mixin.client;

import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WolfEntityModel.class)
public class WolfEntityModelMixin {

	private static final ModelPartBuilder RIGHT_LEG = ModelPartBuilder.create().uv(0, 18).mirrored().cuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F);

	// Fix MC-230603
	@Redirect(method = "getTexturedModelData()Lnet/minecraft/client/model/TexturedModelData;", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPartBuilder;cuboid(FFFFFF)Lnet/minecraft/client/model/ModelPartBuilder;", ordinal = 2))
	private static ModelPartBuilder getTexturedModelDataInvertRightEar(ModelPartBuilder instance, float offsetX, float offsetY, float offsetZ, float sizeX, float sizeY, float sizeZ) {
		return instance.mirrored().cuboid(offsetX, offsetY, offsetZ, sizeX, sizeY, sizeZ).mirrored(false);
	}

	@ModifyArg(method = "getTexturedModelData()Lnet/minecraft/client/model/TexturedModelData;", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPartData;addChild(Ljava/lang/String;Lnet/minecraft/client/model/ModelPartBuilder;Lnet/minecraft/client/model/ModelTransform;)Lnet/minecraft/client/model/ModelPartData;", ordinal = 4), index = 1)
	private static ModelPartBuilder getTexturedModelDataInvertRightLegBack(ModelPartBuilder builder) {
		return RIGHT_LEG;
	}

	@ModifyArg(method = "getTexturedModelData()Lnet/minecraft/client/model/TexturedModelData;", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPartData;addChild(Ljava/lang/String;Lnet/minecraft/client/model/ModelPartBuilder;Lnet/minecraft/client/model/ModelTransform;)Lnet/minecraft/client/model/ModelPartData;", ordinal = 6), index = 1)
	private static ModelPartBuilder getTexturedModelDataInvertRightLegFront(ModelPartBuilder builder) {
		return RIGHT_LEG;
	}

}
