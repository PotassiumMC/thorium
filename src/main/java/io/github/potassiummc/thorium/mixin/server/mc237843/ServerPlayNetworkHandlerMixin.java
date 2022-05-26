package io.github.potassiummc.thorium.mixin.server.mc237843;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

    @Shadow
    public ServerPlayerEntity player;

    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;disconnect(Lnet/minecraft/text/Text;)V", shift = At.Shift.BEFORE, ordinal = 3), cancellable = true)
    private void tickIdleKick(CallbackInfo ci) {
        if (this.player.notInAnyWorld) {
            ci.cancel();
        }
    }

}
