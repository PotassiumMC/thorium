package io.github.potassiummc.thorium.mixin.client.mc55347;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.util.telemetry.TelemetrySender;
import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    // We reset the title in the ctor, because the GameJoin packet is sent when the player switches worlds and proxied servers.
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInitResetTitle(MinecraftClient client, Screen screen, ClientConnection connection, GameProfile profile, TelemetrySender telemetrySender, CallbackInfo ci) {
        client.inGameHud.clearTitle();
        client.inGameHud.setDefaultTitleFade();
    }

}
