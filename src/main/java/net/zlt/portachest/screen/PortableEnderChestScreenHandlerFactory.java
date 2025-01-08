package net.zlt.portachest.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class PortableEnderChestScreenHandlerFactory extends AbstractPortableChestScreenHandlerFactory {
    public PortableEnderChestScreenHandlerFactory(ItemStack stack) {
        super(stack);
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PortableEnderChestScreenHandler(syncId, playerInventory, player.getEnderChestInventory());
    }
}
