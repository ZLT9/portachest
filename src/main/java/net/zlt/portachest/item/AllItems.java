package net.zlt.portachest.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.zlt.portachest.Portachest;
import net.zlt.portachest.compat.Mods;

import java.util.function.Supplier;

public final class AllItems {
    private AllItems() {
    }

    public static final Item PORTABLE_CHEST = register("portable_chest", new PortableChestItem(Portachest.asId("textures/entity/portable_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_LARGE_CHEST = register("portable_large_chest", new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_chest.png"), new Item.Settings().maxCount(1)));

    public static final Item PORTABLE_SPRUCE_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_spruce_chest", () -> new PortableChestItem(Portachest.asId("textures/entity/portable_spruce_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_LARGE_SPRUCE_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_large_spruce_chest", () -> new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_spruce_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_BIRCH_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_birch_chest", () -> new PortableChestItem(Portachest.asId("textures/entity/portable_birch_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_LARGE_BIRCH_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_large_birch_chest", () -> new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_birch_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_JUNGLE_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_jungle_chest", () -> new PortableChestItem(Portachest.asId("textures/entity/portable_jungle_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_LARGE_JUNGLE_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_large_jungle_chest", () -> new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_jungle_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_ACACIA_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_acacia_chest", () -> new PortableChestItem(Portachest.asId("textures/entity/portable_acacia_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_LARGE_ACACIA_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_large_acacia_chest", () -> new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_acacia_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_CHERRY_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_cherry_chest", () -> new PortableChestItem(Portachest.asId("textures/entity/portable_cherry_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_LARGE_CHERRY_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_large_cherry_chest", () -> new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_cherry_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_DARK_OAK_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_dark_oak_chest", () -> new PortableChestItem(Portachest.asId("textures/entity/portable_dark_oak_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_LARGE_DARK_OAK_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_large_dark_oak_chest", () -> new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_dark_oak_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_MANGROVE_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_mangrove_chest", () -> new PortableChestItem(Portachest.asId("textures/entity/portable_mangrove_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_LARGE_MANGROVE_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_large_mangrove_chest", () -> new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_mangrove_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_BAMBOO_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_bamboo_chest", () -> new PortableChestItem(Portachest.asId("textures/entity/portable_bamboo_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_LARGE_BAMBOO_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_large_bamboo_chest", () -> new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_bamboo_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_WARPED_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_warped_chest", () -> new PortableChestItem(Portachest.asId("textures/entity/portable_warped_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_LARGE_WARPED_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_large_warped_chest", () -> new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_warped_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_CRIMSON_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_crimson_chest", () -> new PortableChestItem(Portachest.asId("textures/entity/portable_crimson_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_LARGE_CRIMSON_CHEST = registerIf(Mods.VARIANT_VANILLA_BLOCKS.isLoaded, "portable_large_crimson_chest", () -> new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_crimson_chest.png"), new Item.Settings().maxCount(1)));

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Portachest.asId(id), item);
    }

    private static Item registerIf(boolean condition, String id, Supplier<Item> itemSupplier) {
        return condition ? register(id, itemSupplier.get()) : null;
    }

    public static void init() {
    }
}
