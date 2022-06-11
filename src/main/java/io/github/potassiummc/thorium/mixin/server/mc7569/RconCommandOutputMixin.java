package io.github.potassiummc.thorium.mixin.server.mc7569;

import net.minecraft.server.rcon.RconCommandOutput;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RconCommandOutput.class)
public class RconCommandOutputMixin {

    @Final
    @Shadow
    private StringBuffer buffer;

    @Inject(method = "sendMessage(Lnet/minecraft/text/Text;)V", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuffer;append(Ljava/lang/String;)Ljava/lang/StringBuffer;", shift = At.Shift.AFTER))
    public void sendNewlineAfterMessage(Text message, CallbackInfo ci) {
        this.buffer.append("\n");
    }

}
