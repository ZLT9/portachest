package net.zlt.portachest.inventory;

import net.minecraft.item.ItemStack;

public class PortableLargeChestInventory extends PortableChestInventory {
    public PortableLargeChestInventory(ItemStack stack) {
        super(stack);
    }

    @Override
    public int size() {
        return 54;
    }
}
