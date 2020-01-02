package net.grass.kiss.config;


import io.github.prospector.modmenu.api.ModMenuApi;
import net.grass.kiss.GrassKiss;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.Function;

@SuppressWarnings("unused")
public class GrassKissModMenuCompat implements ModMenuApi {
    @Override
    public String getModId() {
        return GrassKiss.MOD_ID;
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return GrassKissConfig::createConfigScreen;
    }
}