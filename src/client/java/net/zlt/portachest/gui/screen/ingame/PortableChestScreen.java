package net.zlt.portachest.gui.screen.ingame;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.zlt.portachest.screen.PortableChestScreenHandler;

@Environment(EnvType.CLIENT)
public class PortableChestScreen extends HandledScreen<PortableChestScreenHandler> implements ScreenHandlerProvider<PortableChestScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("textures/gui/container/generic_54.png");

    public PortableChestScreen(PortableChestScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

        backgroundHeight = 168;
        playerInventoryTitleY = backgroundHeight - 94;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int i = (width - backgroundWidth) / 2;
        int j = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, i, j, 0, 0, backgroundWidth, 71);
        context.drawTexture(TEXTURE, i, j + 71, 0, 126, backgroundWidth, 96);
    }
}
