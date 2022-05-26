package io.github.potassiummc.thorium.mixin.client.mc148993;

import io.github.potassiummc.thorium.mixin.access.ClientPlayerEntityInvoker;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Redirect(method = "sendMovementPackets()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isCamera()Z"))
    private boolean sendMovementPacketsIsCameraOrSpectator(ClientPlayerEntity instance) {
        return ((ClientPlayerEntityInvoker) instance).invokeIsCamera() || instance.isSpectator();
    }

}
