package net.zlt.portachest.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.zlt.portachest.Portachest;
import net.zlt.portachest.compat.Mods;
import net.zlt.portachest.compat.variantvanillablocks.VariantVanillaBlocks;

public final class AllItems {
    private AllItems() {
    }

    public static final Item PORTABLE_CHEST = register("portable_chest", new PortableChestItem(Portachest.asId("textures/entity/portable_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_LARGE_CHEST = register("portable_large_chest", new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_chest.png"), new Item.Settings().maxCount(1)));

    public static final Item PORTABLE_SPRUCE_CHEST = register("portable_spruce_chest", new PortableChestItem(Portachest.asId("textures/entity/portable_spruce_chest.png"), new Item.Settings().maxCount(1)) {
        @Override
        public boolean canBeExtendedWith(ItemStack stack) {
            return Mods.VARIANT_VANILLA_BLOCKS.isLoaded && !stack.isEmpty() && stack.isOf(VariantVanillaBlocks.getSpruceChest().asItem());
        }

        @Override
        public ItemStack getLarge() {
            return PORTABLE_LARGE_SPRUCE_CHEST.getDefaultStack();
        }
    });
    public static final Item PORTABLE_LARGE_SPRUCE_CHEST = register("portable_large_spruce_chest", new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_spruce_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_BIRCH_CHEST = register("portable_birch_chest", new PortableChestItem(Portachest.asId("textures/entity/portable_birch_chest.png"), new Item.Settings().maxCount(1)) {
        @Override
        public boolean canBeExtendedWith(ItemStack stack) {
            return Mods.VARIANT_VANILLA_BLOCKS.isLoaded && !stack.isEmpty() && stack.isOf(VariantVanillaBlocks.getBirchChest().asItem());
        }

        @Override
        public ItemStack getLarge() {
            return PORTABLE_LARGE_BIRCH_CHEST.getDefaultStack();
        }
    });
    public static final Item PORTABLE_LARGE_BIRCH_CHEST = register("portable_large_birch_chest", new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_birch_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_JUNGLE_CHEST = register("portable_jungle_chest", new PortableChestItem(Portachest.asId("textures/entity/portable_jungle_chest.png"), new Item.Settings().maxCount(1)) {
        @Override
        public boolean canBeExtendedWith(ItemStack stack) {
            return Mods.VARIANT_VANILLA_BLOCKS.isLoaded && !stack.isEmpty() && stack.isOf(VariantVanillaBlocks.getJungleChest().asItem());
        }

        @Override
        public ItemStack getLarge() {
            return PORTABLE_LARGE_JUNGLE_CHEST.getDefaultStack();
        }
    });
    public static final Item PORTABLE_LARGE_JUNGLE_CHEST = register("portable_large_jungle_chest", new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_jungle_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_ACACIA_CHEST = register("portable_acacia_chest", new PortableChestItem(Portachest.asId("textures/entity/portable_acacia_chest.png"), new Item.Settings().maxCount(1)) {
        @Override
        public boolean canBeExtendedWith(ItemStack stack) {
            return Mods.VARIANT_VANILLA_BLOCKS.isLoaded && !stack.isEmpty() && stack.isOf(VariantVanillaBlocks.getAcaciaChest().asItem());
        }

        @Override
        public ItemStack getLarge() {
            return PORTABLE_LARGE_ACACIA_CHEST.getDefaultStack();
        }
    });
    public static final Item PORTABLE_LARGE_ACACIA_CHEST = register("portable_large_acacia_chest", new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_acacia_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_DARK_OAK_CHEST = register("portable_dark_oak_chest", new PortableChestItem(Portachest.asId("textures/entity/portable_dark_oak_chest.png"), new Item.Settings().maxCount(1)) {
        @Override
        public boolean canBeExtendedWith(ItemStack stack) {
            return Mods.VARIANT_VANILLA_BLOCKS.isLoaded && !stack.isEmpty() && stack.isOf(VariantVanillaBlocks.getDarkOakChest().asItem());
        }

        @Override
        public ItemStack getLarge() {
            return PORTABLE_LARGE_DARK_OAK_CHEST.getDefaultStack();
        }
    });
    public static final Item PORTABLE_LARGE_DARK_OAK_CHEST = register("portable_large_dark_oak_chest", new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_dark_oak_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_MANGROVE_CHEST = register("portable_mangrove_chest", new PortableChestItem(Portachest.asId("textures/entity/portable_mangrove_chest.png"), new Item.Settings().maxCount(1)) {
        @Override
        public boolean canBeExtendedWith(ItemStack stack) {
            return Mods.VARIANT_VANILLA_BLOCKS.isLoaded && !stack.isEmpty() && stack.isOf(VariantVanillaBlocks.getMangroveChest().asItem());
        }

        @Override
        public ItemStack getLarge() {
            return PORTABLE_LARGE_MANGROVE_CHEST.getDefaultStack();
        }
    });
    public static final Item PORTABLE_LARGE_MANGROVE_CHEST = register("portable_large_mangrove_chest", new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_mangrove_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_CHERRY_CHEST = register("portable_cherry_chest", new PortableChestItem(Portachest.asId("textures/entity/portable_cherry_chest.png"), new Item.Settings().maxCount(1)) {
        @Override
        public boolean canBeExtendedWith(ItemStack stack) {
            return Mods.VARIANT_VANILLA_BLOCKS.isLoaded && !stack.isEmpty() && stack.isOf(VariantVanillaBlocks.getCherryChest().asItem());
        }

        @Override
        public ItemStack getLarge() {
            return PORTABLE_LARGE_CHERRY_CHEST.getDefaultStack();
        }
    });
    public static final Item PORTABLE_LARGE_CHERRY_CHEST = register("portable_large_cherry_chest", new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_cherry_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_BAMBOO_CHEST = register("portable_bamboo_chest", new PortableChestItem(Portachest.asId("textures/entity/portable_bamboo_chest.png"), new Item.Settings().maxCount(1)) {
        @Override
        public boolean canBeExtendedWith(ItemStack stack) {
            return Mods.VARIANT_VANILLA_BLOCKS.isLoaded && !stack.isEmpty() && stack.isOf(VariantVanillaBlocks.getBambooChest().asItem());
        }

        @Override
        public ItemStack getLarge() {
            return PORTABLE_LARGE_BAMBOO_CHEST.getDefaultStack();
        }
    });
    public static final Item PORTABLE_LARGE_BAMBOO_CHEST = register("portable_large_bamboo_chest", new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_bamboo_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_CRIMSON_CHEST = register("portable_crimson_chest", new PortableChestItem(Portachest.asId("textures/entity/portable_crimson_chest.png"), new Item.Settings().maxCount(1)) {
        @Override
        public boolean canBeExtendedWith(ItemStack stack) {
            return Mods.VARIANT_VANILLA_BLOCKS.isLoaded && !stack.isEmpty() && stack.isOf(VariantVanillaBlocks.getCrimsonChest().asItem());
        }

        @Override
        public ItemStack getLarge() {
            return PORTABLE_LARGE_CRIMSON_CHEST.getDefaultStack();
        }
    });
    public static final Item PORTABLE_LARGE_CRIMSON_CHEST = register("portable_large_crimson_chest", new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_crimson_chest.png"), new Item.Settings().maxCount(1)));
    public static final Item PORTABLE_WARPED_CHEST = register("portable_warped_chest", new PortableChestItem(Portachest.asId("textures/entity/portable_warped_chest.png"), new Item.Settings().maxCount(1)) {
        @Override
        public boolean canBeExtendedWith(ItemStack stack) {
            return Mods.VARIANT_VANILLA_BLOCKS.isLoaded && !stack.isEmpty() && stack.isOf(VariantVanillaBlocks.getWarpedChest().asItem());
        }

        @Override
        public ItemStack getLarge() {
            return PORTABLE_LARGE_WARPED_CHEST.getDefaultStack();
        }
    });
    public static final Item PORTABLE_LARGE_WARPED_CHEST = register("portable_large_warped_chest", new PortableLargeChestItem(Portachest.asId("textures/entity/portable_large_warped_chest.png"), new Item.Settings().maxCount(1)));

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Portachest.asId(id), item);
    }

    public static void init() {
    }
}
