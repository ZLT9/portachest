package net.zlt.portachest.client.item;

import net.minecraft.client.item.TooltipData;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class PortableChestTooltipData implements TooltipData {
    private final DefaultedList<ItemStack> stacks;

    public PortableChestTooltipData(DefaultedList<ItemStack> stacks) {
        this.stacks = stacks;
    }

    public DefaultedList<ItemStack> getStacks() {
        return stacks;
    }
}
