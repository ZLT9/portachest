package net.zlt.portachest.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.zlt.portachest.Portachest;

public final class AllItems {
    private AllItems() {
    }

    public static final Item PORTABLE_CHEST = register("portable_chest", new PortableChestItem(new Item.Settings().maxCount(1)));

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Portachest.asId(id), item);
    }

    public static void init() {
    }
}
