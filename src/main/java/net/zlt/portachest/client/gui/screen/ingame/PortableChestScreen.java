package net.zlt.portachest.client.gui.screen.ingame;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.zlt.portachest.screen.PortableChestScreenHandler;

@Environment(EnvType.CLIENT)
public class PortableChestScreen extends AbstractPortableChestScreen<PortableChestScreenHandler> {
    public PortableChestScreen(PortableChestScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, 3);
    }
}
