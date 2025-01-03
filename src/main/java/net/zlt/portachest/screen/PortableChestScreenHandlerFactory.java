package net.zlt.portachest.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.zlt.portachest.inventory.PortableChestInventory;

public class PortableChestScreenHandlerFactory implements NamedScreenHandlerFactory {
    private final ItemStack stack;

    public PortableChestScreenHandlerFactory(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public Text getDisplayName() {
        return stack.getName();
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PortableChestScreenHandler(syncId, playerInventory, new PortableChestInventory(stack));
    }
}
