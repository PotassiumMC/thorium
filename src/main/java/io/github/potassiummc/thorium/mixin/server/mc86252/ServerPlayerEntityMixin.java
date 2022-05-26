package io.github.potassiummc.thorium.mixin.server.mc86252;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    // Also fixes MC-195732 and MC-212926
    @Inject(method = "moveToWorld(Lnet/minecraft/server/world/ServerWorld;)Lnet/minecraft/entity/Entity;", at = @At("RETURN"))
    private void moveToWorldStopUsingItem(ServerWorld destination, CallbackInfoReturnable<Entity> cir) {
        ((ServerPlayerEntity) (Object) this).stopUsingItem();
    }

}
