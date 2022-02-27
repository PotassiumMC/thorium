package io.github.potassiummc.thorium.mixin.server;

import net.minecraft.server.function.CommandFunction;
import net.minecraft.server.function.CommandFunctionManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.List;

@Mixin(CommandFunctionManager.class)
public abstract class CommandFunctionManagerMixin {

	@Shadow
	protected abstract void executeAll(Collection<CommandFunction> functions, Identifier label);

	@Shadow
	private List<CommandFunction> tickFunctions;

	@Final
	@Shadow
	private static Identifier TICK_TAG_ID;

	// Fix MC-187539
	@Redirect(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/function/CommandFunctionManager;executeAll(Ljava/util/Collection;Lnet/minecraft/util/Identifier;)V", ordinal = 0))
	private void tickExecuteAll(CommandFunctionManager instance, Collection<CommandFunction> functions, Identifier label) {
	}

	@Inject(method = "tick()V", at = @At("TAIL"))
	private void tickExecuteAllAtEnd(CallbackInfo ci) {
		this.executeAll(this.tickFunctions, TICK_TAG_ID);
	}

}
