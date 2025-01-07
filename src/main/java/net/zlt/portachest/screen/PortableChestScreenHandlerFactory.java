package net.zlt.portachest.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.zlt.portachest.inventory.PortableChestInventory;

public class PortableChestScreenHandlerFactory extends AbstractPortableChestScreenHandlerFactory {
    public PortableChestScreenHandlerFactory(ItemStack stack) {
        super(stack);
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PortableChestScreenHandler(syncId, playerInventory, new PortableChestInventory(stack));
    }
}
