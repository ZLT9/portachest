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
        });
    }
}
