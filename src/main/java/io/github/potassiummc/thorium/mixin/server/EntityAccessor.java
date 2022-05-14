package io.github.potassiummc.thorium.mixin.server;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface EntityAccessor {

    @Accessor("inNetherPortal")
    boolean inNetherPortal();

}
