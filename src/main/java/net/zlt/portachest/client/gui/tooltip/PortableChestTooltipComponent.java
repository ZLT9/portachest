package net.zlt.portachest.client.gui.tooltip;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.zlt.portachest.Portachest;
import net.zlt.portachest.client.item.PortableChestTooltipData;

public class PortableChestTooltipComponent implements TooltipComponent {
    public static final Identifier TEXTURE = Portachest.asId("textures/gui/container/portable_chest.png");

    private final DefaultedList<ItemStack> stacks;

    public PortableChestTooltipComponent(PortableChestTooltipData data) {
        stacks = data.getStacks();
    }

    @Override
    public int getHeight() {
        return getRows() * 20 + 6;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return getColumns() * 18 + 2;
    }

    private int getColumns() {
        return Math.min(9, stacks.size());
    }

    private int getRows() {
        return (stacks.size() - 1) / 9 + 1;
    }
}
