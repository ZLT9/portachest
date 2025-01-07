package net.zlt.portachest.client.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

@Environment(EnvType.CLIENT)
public class PortableChestTooltipData implements TooltipData {
    private final DefaultedList<ItemStack> stacks;

    public PortableChestTooltipData(DefaultedList<ItemStack> stacks) {
        this.stacks = stacks;
    }

    public DefaultedList<ItemStack> getStacks() {
        return stacks;
    }
}
