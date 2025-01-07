package net.zlt.portachest.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.zlt.portachest.screen.PortableLargeChestScreenHandlerFactory;

public class PortableLargeChestItem extends PortableChestItem {
    public PortableLargeChestItem(Identifier entityTextureId, Settings settings) {
        super(entityTextureId, settings);
    }

    @Override
    public void open(PlayerEntity player, ItemStack stack) {
        player.openHandledScreen(new PortableLargeChestScreenHandlerFactory(stack));
    }
}
