package net.zlt.portachest.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.zlt.portachest.Portachest;
import net.zlt.portachest.compat.Mods;

public final class AllItemGroups {
    private AllItemGroups() {
    }

    public static final RegistryKey<ItemGroup> BASE_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Portachest.asId("base"));

    public static final ItemGroup BASE = register(FabricItemGroup.builder()
        .icon(() -> new ItemStack(AllItems.PORTABLE_CHEST))
        .displayName(Text.translatable("itemGroup.portachest"))
        .build());

    private static ItemGroup register(ItemGroup itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, BASE_KEY, itemGroup);
    }

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(BASE_KEY).register(itemGroup -> {
            itemGroup.add(AllItems.PORTABLE_CHEST);
            itemGroup.add(AllItems.PORTABLE_LARGE_CHEST);
            itemGroup.add(AllItems.PORTABLE_ENDER_CHEST);

            if (Mods.VARIANT_VANILLA_BLOCKS.isLoaded) {
                itemGroup.add(AllItems.PORTABLE_SPRUCE_CHEST);
                itemGroup.add(AllItems.PORTABLE_LARGE_SPRUCE_CHEST);
                itemGroup.add(AllItems.PORTABLE_BIRCH_CHEST);
                itemGroup.add(AllItems.PORTABLE_LARGE_BIRCH_CHEST);
                itemGroup.add(AllItems.PORTABLE_JUNGLE_CHEST);
                itemGroup.add(AllItems.PORTABLE_LARGE_JUNGLE_CHEST);
                itemGroup.add(AllItems.PORTABLE_ACACIA_CHEST);
                itemGroup.add(AllItems.PORTABLE_LARGE_ACACIA_CHEST);
                itemGroup.add(AllItems.PORTABLE_DARK_OAK_CHEST);
                itemGroup.add(AllItems.PORTABLE_LARGE_DARK_OAK_CHEST);
                itemGroup.add(AllItems.PORTABLE_MANGROVE_CHEST);
                itemGroup.add(AllItems.PORTABLE_LARGE_MANGROVE_CHEST);
                itemGroup.add(AllItems.PORTABLE_CHERRY_CHEST);
                itemGroup.add(AllItems.PORTABLE_LARGE_CHERRY_CHEST);
                itemGroup.add(AllItems.PORTABLE_BAMBOO_CHEST);
                itemGroup.add(AllItems.PORTABLE_LARGE_BAMBOO_CHEST);
                itemGroup.add(AllItems.PORTABLE_CRIMSON_CHEST);
                itemGroup.add(AllItems.PORTABLE_LARGE_CRIMSON_CHEST);
                itemGroup.add(AllItems.PORTABLE_WARPED_CHEST);
                itemGroup.add(AllItems.PORTABLE_LARGE_WARPED_CHEST);
            }
        });
    }
}
