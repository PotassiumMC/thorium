package io.github.potassiummc.thorium.mixin.client;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Redirect(method = "handleBlockBreaking(Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isAir()Z"))
    private boolean handleBlockBreakingIsAirOrSpectator(BlockState instance) {
        ClientPlayerEntity me = ((MinecraftClient) (Object) this).player;
        // Pretend the block is air if we're a spectator.
        return instance.isAir() || me.isSpectator();
    }

}
