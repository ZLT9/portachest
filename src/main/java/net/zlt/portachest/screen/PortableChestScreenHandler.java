package net.zlt.portachest.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.zlt.portachest.item.PortableChestItem;

public class PortableChestScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public PortableChestScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(27));
    }

    public PortableChestScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(AllScreenHandlerTypes.PORTABLE_CHEST, syncId);

        checkSize(inventory, 27);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);

        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                addSlot(new PortableChestSlot(inventory, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 85 + j * 18));
            }
        }

        for (int j = 0; j < 9; ++j) {
            addSlot(new Slot(playerInventory, j, 8 + j * 18, 143));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = slots.get(slot);

        if (slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();

            if (slot < 27) {
                if (!insertItem(itemStack2, 27, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!insertItem(itemStack2, 0, 27, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else if (slot < 27) {
                slot2.setStack(itemStack2);
            } else {
                slot2.markDirty();
            }
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        inventory.onClose(player);
    }

    @Override
    protected boolean insertItem(ItemStack stack, int startIndex, int endIndex, boolean fromLast) {
        boolean bl = false;
        int i = startIndex;

        if (fromLast) {
            i = endIndex - 1;
        }

        if (stack.isStackable()) {
            while (!stack.isEmpty() && (fromLast ? i >= startIndex : i < endIndex)) {
                Slot slot = slots.get(i);
                ItemStack itemStack = slot.getStack();

                if (!itemStack.isEmpty() && ItemStack.canCombine(stack, itemStack)) {
                    int j = itemStack.getCount() + stack.getCount();

                    if (j <= stack.getMaxCount()) {
                        stack.setCount(0);
                        itemStack.setCount(j);
                        slot.setStack(itemStack);
                        bl = true;
                    } else if (itemStack.getCount() < stack.getMaxCount()) {
                        stack.decrement(stack.getMaxCount() - itemStack.getCount());
                        itemStack.setCount(stack.getMaxCount());
                        slot.setStack(itemStack);
                        bl = true;
                    }
                }

                if (fromLast) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        if (!stack.isEmpty()) {
            if (fromLast) {
                i = endIndex - 1;
            } else {
                i = startIndex;
            }

            while (fromLast ? i >= startIndex : i < endIndex) {
                Slot slot = slots.get(i);
                ItemStack itemStack = slot.getStack();

                if (itemStack.isEmpty() && slot.canInsert(stack)) {
                    if (stack.getCount() > slot.getMaxItemCount()) {
                        slot.setStack(stack.split(slot.getMaxItemCount()));
                    } else {
                        slot.setStack(stack.split(stack.getCount()));
                    }

                    slot.markDirty();
                    bl = true;
                    break;
                }

                if (fromLast) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        return bl;
    }

    private static class PortableChestSlot extends Slot {
        public PortableChestSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return !(stack.getItem() instanceof PortableChestItem);
        }
    }
}
