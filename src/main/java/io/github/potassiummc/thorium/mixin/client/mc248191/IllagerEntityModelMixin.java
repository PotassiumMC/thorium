package io.github.potassiummc.thorium.mixin.client.mc248191;

import net.minecraft.client.render.entity.model.IllagerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(IllagerEntityModel.class)
public class IllagerEntityModelMixin {

    @ModifyConstant(method = "setAngles(Lnet/minecraft/entity/mob/IllagerEntity;FFFFF)V", constant = @Constant(floatValue = -2.3561945F, ordinal = 1))
    private static float getTexturedModelDataRightArm(float constant) {
        return -2.670354F;
    }

}
