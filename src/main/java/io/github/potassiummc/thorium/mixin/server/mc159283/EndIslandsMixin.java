package io.github.potassiummc.thorium.mixin.server.mc159283;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.world.gen.densityfunction.DensityFunctionTypes$EndIslands")
public class EndIslandsMixin {

    @Redirect(method = "method_41529(Lnet/minecraft/util/math/noise/SimplexNoiseSampler;II)F", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;sqrt(F)F"))
    private static float getNoiseAt(float value, SimplexNoiseSampler simplexNoiseSampler, int i, int j) {
        // Explicitly cast i and j to longs.
        return MathHelper.sqrt((long) i * (long) i + (long) j * (long) j);
    }

}
