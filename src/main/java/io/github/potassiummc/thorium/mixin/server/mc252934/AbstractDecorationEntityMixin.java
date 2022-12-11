package io.github.potassiummc.thorium.mixin.server.mc252934;

import net.minecraft.entity.decoration.AbstractDecorationEntity;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractDecorationEntity.class)
public class AbstractDecorationEntityMixin {

    // The position is corrected in the same tick the entity is spawned, no need to do it ourselves
    @Redirect(method= "readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V", at=@At(value="INVOKE", target="Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V"))
    public void readCustomDataCorrectBlockpos(Logger instance, String s, Object o) {
    }

}
