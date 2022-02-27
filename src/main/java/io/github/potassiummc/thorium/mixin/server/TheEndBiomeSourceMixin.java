package io.github.potassiummc.thorium.mixin.server;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.world.biome.source.TheEndBiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(TheEndBiomeSource.class)
public class TheEndBiomeSourceMixin {

	// Fix MC-159283
	@Redirect(method = "getNoiseAt(Lnet/minecraft/util/math/noise/SimplexNoiseSampler;II)F", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;sqrt(F)F"), slice = @Slice(to = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(FFF)F")))
	private static float getNoiseAt(float f, SimplexNoiseSampler simplexNoiseSampler, int i, int j) {
		// Explicitly cast i and j to longs.
		return MathHelper.sqrt((long) i * (long) i + (long) j * (long) j);
	}

}
