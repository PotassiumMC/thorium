package io.github.potassiummc.thorium;

import io.github.potassiummc.thorium.config.ThoriumConfig;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThoriumMixinPlugin implements IMixinConfigPlugin {

    private static final Pattern THORIUM_FIX_PATTERN = Pattern.compile("io\\.github\\.potassiummc\\.thorium\\.mixin\\.(client|server)\\.mc(\\d+)");

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        Pair<String, String> bug = getBugFromFQN(mixinClassName);

        // This mixin doesn't belong to a specific bug (e.g. an accessor), so it should always be enabled.
        if (bug == null) {
            return true;
        }

        ThoriumConfig config = ThoriumConfig.getInstance();
        config.loadIfUnloaded();

        return config.isFixEnabled(bug.getLeft(), bug.getRight());
    }

    @Nullable
    private Pair<String, String> getBugFromFQN(String fqn) {
        Matcher matcher = THORIUM_FIX_PATTERN.matcher(fqn);

        if (!matcher.find()) {
            return null;
        }

        return new Pair<>(matcher.group(1), matcher.group(2));
    }

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

}
