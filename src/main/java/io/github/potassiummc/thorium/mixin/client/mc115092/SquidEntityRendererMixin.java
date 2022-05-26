package io.github.potassiummc.thorium.mixin.client.mc115092;

import net.minecraft.client.render.entity.SquidEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SquidEntityRenderer.class)
public class SquidEntityRendererMixin<T extends SquidEntity> {

    // Based on code analysis by OMGItzAndrew: https://bugs.mojang.com/browse/MC-115092
    @Inject(method = "setupTransforms(Lnet/minecraft/entity/passive/SquidEntity;Lnet/minecraft/client/util/math/MatrixStack;FFF)V", at = @At("TAIL"))
    private void setupTransformsRotate(T squidEntity, MatrixStack matrixStack, float f, float g, float h, CallbackInfo ci) {
        String name = squidEntity.getName().asString();

        if (name.equals("Dinnerbone") || name.equals("Grumm")) {
            matrixStack.translate(0.0D, squidEntity.getHeight() + 0.1F, 0.0D);
            matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(180.0F));
        }
    }

}
