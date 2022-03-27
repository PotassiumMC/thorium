package io.github.potassiummc.thorium.mixin.client;

import net.minecraft.client.render.entity.GuardianEntityRenderer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuardianEntityRenderer.class)
public class GuardianEntityRendererMixin {

    // Fix MC-165595
    @Redirect(method= "render(Lnet/minecraft/entity/mob/GuardianEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",at=@At(value="INVOKE", target="Lnet/minecraft/world/World;getTime()J"))
    public long getTimeOfDay(World instance) {
        return instance.getTimeOfDay();
    }

}
