package net.zlt.portachest.compat;

import net.fabricmc.loader.api.FabricLoader;

public enum Mods {
    TRINKETS("trinkets"),
    VARIANT_VANILLA_BLOCKS("variantvanillablocks");

    public final String id;
    public final boolean isLoaded;

    Mods(String id) {
        this.id = id;
        isLoaded = FabricLoader.getInstance().isModLoaded(id);
    }

    public static void init() {
    }
}
