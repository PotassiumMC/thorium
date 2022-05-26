package io.github.potassiummc.thorium.mixin.server.mc7569;

import net.minecraft.server.rcon.RconCommandOutput;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(RconCommandOutput.class)
public class RconCommandOutputMixin {

    @Final
    @Shadow
    private StringBuffer buffer;

    @Inject(method = "sendSystemMessage(Lnet/minecraft/text/Text;Ljava/util/UUID;)V", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuffer;append(Ljava/lang/String;)Ljava/lang/StringBuffer;", shift = At.Shift.AFTER))
    public void sendNewlineAfterMessage(Text message, UUID sender, CallbackInfo ci) {
        this.buffer.append("\n");
    }

}
