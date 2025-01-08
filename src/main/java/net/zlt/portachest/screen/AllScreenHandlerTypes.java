package net.zlt.portachest.screen;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.zlt.portachest.Portachest;

public final class AllScreenHandlerTypes {
    private AllScreenHandlerTypes() {
    }

    public static final ScreenHandlerType<PortableChestScreenHandler> PORTABLE_CHEST = register("portable_chest", PortableChestScreenHandler::new);
    public static final ScreenHandlerType<PortableLargeChestScreenHandler> PORTABLE_LARGE_CHEST = register("portable_large_chest", PortableLargeChestScreenHandler::new);
    public static final ScreenHandlerType<PortableEnderChestScreenHandler> PORTABLE_ENDER_CHEST = register("portable_ender_chest", PortableEnderChestScreenHandler::new);

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, Portachest.asId(id), new ScreenHandlerType<>(factory, FeatureSet.empty()));
    }

    public static void init() {
    }
}
