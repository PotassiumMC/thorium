package io.github.potassiummc.thorium.config;

import io.github.potassiummc.thorium.Thorium;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.io.IOException;
import java.nio.file.Path;

public class ThoriumConfig {

    private final static ThoriumConfig INSTANCE = new ThoriumConfig();
    private final static String CLIENT_SIDE = "Client Side";
    private final static String CLIENT_SIDE_COMMENT = "These fixes will only apply for you and will do nothing if the mod is installed on a dedicated server.";
    private final static String SERVER_SIDE = "Server Side";
    private final static String SERVER_SIDE_COMMENT = "These fixes will apply for everyone connected to this dedicated server or LAN-opened world.";

    private final HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
        .path(getConfigPath())
        .build();
    private CommentedConfigurationNode root;

    public void load() {
        try {
            root = loader.load();
        } catch (IOException e) {
            Thorium.LOGGER.warn("Failed to load config!", e.getCause() == null ? e : e.getCause());
        }
    }

    public void loadIfUnloaded() {
        if (root != null) {
            return;
        }

        load();
    }

    public void save() {
        try {
            loader.save(root);
        } catch (IOException e) {
            Thorium.LOGGER.warn("Failed to save config!", e.getCause() == null ? e : e.getCause());
        }
    }

    public boolean isFixEnabled(String rawSide, String bug) {
        boolean isClientSide = rawSide.equals("client");
        String side = isClientSide ? CLIENT_SIDE : SERVER_SIDE;
        String comment = isClientSide ? CLIENT_SIDE_COMMENT : SERVER_SIDE_COMMENT;
        String bugName = "MC-" + bug;
        boolean enabledByDefault = enableFixesByDefault();

        return root.node("Fixes").node(side).commentIfAbsent(comment).node(bugName).getBoolean(enabledByDefault);
    }

    public boolean enableFixesByDefault() {
        return root.node("Default For New Fixes").getBoolean(true);
    }

    public static ThoriumConfig getInstance() {
        return INSTANCE;
    }

    private static Path getConfigPath() {
        return Path.of(FabricLoader.getInstance().getConfigDir().toString(), "thorium", "thorium.conf");
    }

}
