package io.github.potassiummc.thorium.mixin.client;

import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.render.entity.model.GoatEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GoatEntityModel.class)
public class GoatEntityModelMixin {

	// Fix MC-246257
	@ModifyArg(method = "getTexturedModelData()Lnet/minecraft/client/model/TexturedModelData;", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPartData;addChild(Ljava/lang/String;Lnet/minecraft/client/model/ModelPartBuilder;Lnet/minecraft/client/model/ModelTransform;)Lnet/minecraft/client/model/ModelPartData;", ordinal = 2), index = 1)
	private static ModelPartBuilder getTexturedModelDataInvertRightHorn(ModelPartBuilder builder) {
		return ModelPartBuilder.create().uv(12, 55).mirrored().cuboid(-2.99F, -16.0F, -10.0F, 2.0F, 7.0F, 2.0F);
	}

}
