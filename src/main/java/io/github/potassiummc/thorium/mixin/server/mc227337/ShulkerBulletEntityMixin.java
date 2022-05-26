package io.github.potassiummc.thorium.mixin.server;

import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShulkerBulletEntity.class)
public class ShulkerBulletEntityMixin {

    // Fix MC-227337
    @Inject(method = "onEntityHit(Lnet/minecraft/util/hit/EntityHitResult;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/ProjectileEntity;onEntityHit(Lnet/minecraft/util/hit/EntityHitResult;)V", shift = At.Shift.AFTER))
    private void onEntityHitPlaySoundAndParticle(EntityHitResult entityHitResult, CallbackInfo ci) {
        // Based on code analysis by Avoma: https://bugs.mojang.com/browse/MC-227337?focusedCommentId=1135924#comment-1135924
        ShulkerBulletEntity me = (ShulkerBulletEntity) (Object) this;

        ((ServerWorld) me.world).spawnParticles(ParticleTypes.EXPLOSION, me.getX(), me.getY(), me.getZ(), 2, 0.2D, 0.2D, 0.2D, 0.0D);
        me.playSound(SoundEvents.ENTITY_SHULKER_BULLET_HIT, 1.0F, 1.0F);
    }

}
