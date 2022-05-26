package io.github.potassiummc.thorium;

import io.github.potassiummc.thorium.config.ThoriumConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Thorium implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("thorium");

    @Override
    public void onInitialize() {
        ThoriumConfig.getInstance().save();
    }

}
