package io.github.potassiummc.thorium.mixin.client;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ClientPlayerEntity.class)
public interface ClientPlayerEntityInvoker {

	@Invoker("isCamera")
	boolean invokeIsCamera();

}
