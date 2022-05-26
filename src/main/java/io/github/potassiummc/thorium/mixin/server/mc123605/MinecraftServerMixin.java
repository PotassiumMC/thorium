package io.github.potassiummc.thorium.mixin.server;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraft.world.level.ServerWorldProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    // Fix MC-123605
    @Redirect(method = "setToDebugWorldProperties(Lnet/minecraft/world/SaveProperties;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/ServerWorldProperties;setClearWeatherTime(I)V"))
    private void setDebugWorldPropertiesSetGamerule(ServerWorldProperties instance, int i) {
        instance.getGameRules().get(GameRules.DO_WEATHER_CYCLE).set(false, (MinecraftServer) (Object) this);
    }

}
