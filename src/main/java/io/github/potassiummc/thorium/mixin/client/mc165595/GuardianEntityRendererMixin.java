package io.github.potassiummc.thorium.mixin.client.mc165595;

import net.minecraft.client.render.entity.GuardianEntityRenderer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = GuardianEntityRenderer.class, priority = 900)
public class GuardianEntityRendererMixin {

    @Redirect(method = "render(Lnet/minecraft/entity/mob/GuardianEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getTime()J"))
    public long getTimeOfDay(World instance) {
        return instance.getTimeOfDay();
    }

}
