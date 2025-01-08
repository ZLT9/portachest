package net.zlt.portachest.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.zlt.portachest.item.AllItems;
import org.jetbrains.annotations.Nullable;

public class PortableEnderChestScreenHandler extends AbstractPortableChestScreenHandler {
    public PortableEnderChestScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(27));
    }

    public PortableEnderChestScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(AllScreenHandlerTypes.PORTABLE_ENDER_CHEST, syncId, inventory);

        checkSize(inventory, 27);
        inventory.onOpen(playerInventory.player);

        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                addSlot(new Slot(inventory, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                addSlot(new PlayerSlot(playerInventory, k + j * 9 + 9, 8 + k * 18, 85 + j * 18));
            }
        }

        for (int j = 0; j < 9; ++j) {
            addSlot(new PlayerSlot(playerInventory, j, 8 + j * 18, 143));
        }
    }

    @Override
    @Nullable
    public ItemStack getPortableChest() {
        return AllItems.PORTABLE_ENDER_CHEST.getDefaultStack();
    }

    @Override
    protected int size() {
        return 27;
    }
}
