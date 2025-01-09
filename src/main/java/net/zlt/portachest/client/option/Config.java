package net.zlt.portachest.client.option;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.zlt.portachest.Portachest;
import net.zlt.portachest.option.PortableChestSlotPriorityLevel;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Environment(EnvType.CLIENT)
public final class Config {
    private Config() {
    }

    private static final File file = FabricLoader.getInstance().getConfigDir().resolve("portachest.json").toFile();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();

    private static final PortableChestSlotPriorityLevel[] portableChestSlotPriorityLevels = {
        PortableChestSlotPriorityLevel.VANILLA_CHEST,
        PortableChestSlotPriorityLevel.TRINKETS_CHEST_BACK,
        PortableChestSlotPriorityLevel.VANILLA_MAIN_HAND,
        PortableChestSlotPriorityLevel.VANILLA_OFF_HAND
    };

    public static void swapPortableChestSlotPriorityLevels(int a, int b) {
        PortableChestSlotPriorityLevel levelA = portableChestSlotPriorityLevels[a];
        portableChestSlotPriorityLevels[a] = portableChestSlotPriorityLevels[b];
        portableChestSlotPriorityLevels[b] = levelA;
    }

    public static PortableChestSlotPriorityLevel getPortableChestSlotPriorityLevel(int level) {
        return portableChestSlotPriorityLevels[level];
    }

    public static void init() {
        if (!file.exists() || !file.isFile()) {
            Portachest.LOGGER.warn("Could not find config file!");
            saveFile();
            return;
        }

        try (BufferedReader configFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            ConfigJson configJson = gson.fromJson(configFileReader, ConfigJson.class);
            if (configJson == null) {
                Portachest.LOGGER.error("Failed to load config file: File is empty!");
                saveFile();
                return;
            }
            if (configJson.portable_chest_slot_priority_levels.length != 4) {
                Portachest.LOGGER.error("Failed to load config file: portable_chest_slot_priority_levels length is not 4!");
                saveFile();
                return;
            }
            int[] levelOrdinals = {-1, -1, -1, -1};
            for (int i = 0; i < configJson.portable_chest_slot_priority_levels.length; ++i) {
                int levelOrdinal = configJson.portable_chest_slot_priority_levels[i];
                if (levelOrdinal < 0 || levelOrdinal > 3) {
                    Portachest.LOGGER.error("Failed to load config file: portable_chest_slot_priority_levels has an invalid level!");
                    saveFile();
                    return;
                }

                for (int ordinal : levelOrdinals) {
                    if (ordinal == levelOrdinal) {
                        Portachest.LOGGER.error("Failed to load config file: portable_chest_slot_priority_levels has duplicate levels!");
                        saveFile();
                        return;
                    }
                }
                levelOrdinals[i] = levelOrdinal;
            }
            for (int i = 0; i < levelOrdinals.length; ++i) {
                portableChestSlotPriorityLevels[i] = PortableChestSlotPriorityLevel.fromOrdinal(levelOrdinals[i]);
            }
        } catch (Exception e) {
            Portachest.LOGGER.error("Failed to load config file!");
            saveFile();
        }
    }

    public static void saveFile() {
        Portachest.LOGGER.warn("Saving config file...");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            gson.toJson(ConfigJson.fromConfig(), writer);
            Portachest.LOGGER.warn("Successfully saved config file!");
        } catch (Exception e) {
            Portachest.LOGGER.error("Failed to save config file!");
        }
    }

    @Environment(EnvType.CLIENT)
    private static class ConfigJson {
        public int[] portable_chest_slot_priority_levels = {-1, -1, -1, -1};

        public static ConfigJson fromConfig() {
            ConfigJson configJson = new ConfigJson();
            for (int i = 0; i < portableChestSlotPriorityLevels.length; ++i) {
                configJson.portable_chest_slot_priority_levels[i] = portableChestSlotPriorityLevels[i].ordinal();
            }
            return configJson;
        }
    }
}
