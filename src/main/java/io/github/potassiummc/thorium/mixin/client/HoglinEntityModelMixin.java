package io.github.potassiummc.thorium.mixin.client;

import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.render.entity.model.HoglinEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(HoglinEntityModel.class)
public class HoglinEntityModelMixin {

	// Fix MC-246258
	@ModifyArg(method = "getTexturedModelData()Lnet/minecraft/client/model/TexturedModelData;", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPartData;addChild(Ljava/lang/String;Lnet/minecraft/client/model/ModelPartBuilder;Lnet/minecraft/client/model/ModelTransform;)Lnet/minecraft/client/model/ModelPartData;", ordinal = 5), index = 1)
	private static ModelPartBuilder getTexturedModelDataInvertRightHorn(ModelPartBuilder builder) {
		return ModelPartBuilder.create().uv(10, 13).mirrored().cuboid(-1.0F, -11.0F, -1.0F, 2.0F, 11.0F, 2.0F);
	}

}
