package net.zlt.portachest.client.gui.tooltip;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.zlt.portachest.Portachest;
import net.zlt.portachest.client.item.PortableChestTooltipData;

@Environment(EnvType.CLIENT)
public class PortableChestTooltipComponent implements TooltipComponent {
    public static final Identifier TEXTURE = Portachest.asId("textures/gui/container/portable_chest.png");

    private final DefaultedList<ItemStack> stacks;

    public PortableChestTooltipComponent(PortableChestTooltipData data) {
        stacks = data.getStacks();
    }

    @Override
    public int getHeight() {
        return getRows() * 18 + 6;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return getColumns() * 18 + 2;
    }

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, DrawContext context) {
        int columns = getColumns();
        int rows = getRows();
        int k = 0;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                drawSlot(x + j * 18 + 1, y + i * 18 + 1, k++, context, textRenderer);
            }
        }

        drawOutline(x, y, columns, rows, context);
    }

    private int getColumns() {
        return Math.min(9, stacks.size());
    }

    private int getRows() {
        return (stacks.size() - 1) / 9 + 1;
    }

    private void draw(DrawContext context, int x, int y, Sprite sprite) {
        context.drawTexture(TEXTURE, x, y, 0, sprite.u, sprite.v, sprite.width, sprite.height, 64, 64);
    }

    private void drawSlot(int x, int y, int index, DrawContext context, TextRenderer textRenderer) {
        if (index >= stacks.size()) {
            draw(context, x, y, Sprite.SLOT);
            return;
        }

        ItemStack stack = stacks.get(index);
        draw(context, x, y, Sprite.SLOT);
        context.drawItem(stack, x + 1, y + 1, index);
        context.drawItemInSlot(textRenderer, stack, x + 1, y + 1);
    }

    private void drawOutline(int x, int y, int columns, int rows, DrawContext context) {
        draw(context, x, y, Sprite.BORDER_CORNER);
        draw(context, x + columns * 18 + 1, y, Sprite.BORDER_CORNER);

        for (int i = 0; i < columns; ++i) {
            draw(context, x + 1 + i * 18, y, Sprite.BORDER_HORIZONTAL);
            draw(context, x + 1 + i * 18, y + rows * 18 + 1, Sprite.BORDER_HORIZONTAL);
        }

        for (int i = 0; i < rows; ++i) {
            draw(context, x, y + i * 18 + 1, Sprite.BORDER_VERTICAL);
            draw(context, x + columns * 18 + 1, y + i * 18 + 1, Sprite.BORDER_VERTICAL);
        }

        draw(context, x, y + rows * 18 + 1, Sprite.BORDER_CORNER);
        draw(context, x + columns * 18 + 1, y + rows * 18 + 1, Sprite.BORDER_CORNER);
    }

    @Environment(EnvType.CLIENT)
    private enum Sprite {
        SLOT(0, 0, 18, 18),
        BORDER_VERTICAL(0, 18, 1, 18),
        BORDER_HORIZONTAL(0, 18, 18, 1),
        BORDER_CORNER(0, 18, 1, 1);

        public final int u;
        public final int v;
        public final int width;
        public final int height;

        Sprite(int u, int v, int width, int height) {
            this.u = u;
            this.v = v;
            this.width = width;
            this.height = height;
        }
    }
}
