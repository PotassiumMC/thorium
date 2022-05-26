package io.github.potassiummc.thorium.mixin.client;

import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FishingBobberEntityRenderer.class)
public abstract class FishingBobberEntityRendererMixin {

    // Fix MC-4490
    @ModifyConstant(method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", constant = @Constant(floatValue = -0.1875F))
    private float renderSneakOffset(float constant) {
        return -0.2875F;
    }

}
