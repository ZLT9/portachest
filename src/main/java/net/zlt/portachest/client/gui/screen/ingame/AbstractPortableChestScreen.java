package net.zlt.portachest.client.gui.screen.ingame;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.zlt.portachest.screen.AbstractPortableChestScreenHandler;

@Environment(EnvType.CLIENT)
public abstract class AbstractPortableChestScreen<T extends AbstractPortableChestScreenHandler> extends HandledScreen<T> {
    private static final Identifier TEXTURE = new Identifier("textures/gui/container/generic_54.png");

    private final int rows;

    protected AbstractPortableChestScreen(T handler, PlayerInventory inventory, Text title, int rows) {
        super(handler, inventory, title);
        this.rows = rows;
        backgroundHeight = 114 + rows * 18;
        playerInventoryTitleY = backgroundHeight - 94;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawDisabledSlots(context);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int i = (width - backgroundWidth) / 2;
        int j = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, i, j, 0, 0, backgroundWidth, rows * 18 + 17);
        context.drawTexture(TEXTURE, i, j + rows * 18 + 17, 0, 126, backgroundWidth, 96);
    }

    protected void drawDisabledSlots(DrawContext context) {
        context.setShaderColor(1.0f, 1.0f, 1.0f, 0.6f);
        for (int i = 0; i < handler.slots.size(); ++i) {
            Slot slot = handler.slots.get(i);
            if (!slot.isEnabled()) {
                context.drawItem(slot.getStack(), slot.x + x, slot.y + y);
            }
        }
        context.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }
}
