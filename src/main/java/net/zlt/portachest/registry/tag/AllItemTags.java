package net.zlt.portachest.registry.tag;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.zlt.portachest.compat.Mods;

import java.util.function.Supplier;

public final class AllItemTags {
    private AllItemTags() {
    }

    public static final TagKey<Item> TRINKETS_CHEST_BACK = createIf(Mods.TRINKETS.isLoaded, () -> new Identifier(Mods.TRINKETS.id, "chest/back"));

    private static TagKey<Item> create(Identifier id) {
        return TagKey.of(RegistryKeys.ITEM, id);
    }

    private static TagKey<Item> createIf(boolean condition, Supplier<Identifier> idSupplier) {
        return condition ? create(idSupplier.get()) : null;
    }

    public static void init() {
    }
}
