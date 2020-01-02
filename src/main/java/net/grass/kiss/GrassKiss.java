package net.grass.kiss;

import net.fabricmc.api.ClientModInitializer;
import net.grass.kiss.config.GrassKissConfig;
import net.grass.kiss.config.GrassKissConfigManager;

public class GrassKiss implements ClientModInitializer {
    public static final String MOD_ID = "grass_kiss";

    @Override
    public void onInitializeClient() {
        GrassKissConfig config = GrassKissConfigManager.init();
        config.refreshOnSave();
    }
}

