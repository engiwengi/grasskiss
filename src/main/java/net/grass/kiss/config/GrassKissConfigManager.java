package net.grass.kiss.config;

import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;
import net.grass.kiss.GrassKiss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class GrassKissConfigManager {
    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor(r -> new Thread(r, "GrassKiss Config Manager"));
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    private static GrassKissConfig config;
    private static Path configFile;

    public static GrassKissConfig getConfig() {
        return config != null ? config : init();
    }

    public static GrassKissConfig init() {
        configFile = FabricLoader.getInstance().getConfigDirectory().toPath().resolve(GrassKiss.MOD_ID + ".json");
        if (!Files.exists(configFile)) {
            System.out.println("Creating grasskiss config file");
            save().join();
        }
        load().thenApply(c -> config = c).join();
        return Objects.requireNonNull(config, "Failed to initialize graskiss config");
    }

    public static CompletableFuture<GrassKissConfig> load() {
        return CompletableFuture.supplyAsync(() -> {
            try (BufferedReader reader = Files.newBufferedReader(configFile)) {
                return GSON.fromJson(reader, GrassKissConfig.class);
            } catch (IOException | JsonParseException e) {
                System.err.println("Unable to read graskiss config, restoring defaults");
                save();
                return new GrassKissConfig();
            }
        }, EXECUTOR);
    }

    public static CompletableFuture<Void> save() {
        return CompletableFuture.runAsync(() -> {
            config = Optional.ofNullable(config).orElseGet(GrassKissConfig::new);
            config.refreshOnSave();
            try (BufferedWriter writer = Files.newBufferedWriter(configFile)) {
                GSON.toJson(config, writer);
            } catch (IOException | JsonIOException e) {
                System.err.println("Failed to write to grasskiss config file");
            }
        }, EXECUTOR);
    }
}
