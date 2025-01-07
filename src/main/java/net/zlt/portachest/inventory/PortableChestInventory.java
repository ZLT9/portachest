package net.zlt.portachest.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.collection.DefaultedList;
import net.zlt.portachest.item.PortableChestItem;

public class PortableChestInventory implements Inventory {
    protected final ItemStack portableChest;
    protected final DefaultedList<ItemStack> stacks = DefaultedList.ofSize(size(), ItemStack.EMPTY);

    public PortableChestInventory(ItemStack portableChest) {
        this.portableChest = portableChest;

        if (portableChest.hasNbt()) {
            NbtCompound stackNbt = portableChest.getNbt();
            if (stackNbt.contains(PortableChestItem.ITEMS_KEY, NbtElement.LIST_TYPE)) {
                Inventories.readNbt(stackNbt, stacks);
            } else {
                stackNbt.put(PortableChestItem.ITEMS_KEY, new NbtList());
            }
        } else {
            portableChest.getOrCreateNbt().put(PortableChestItem.ITEMS_KEY, new NbtList());
        }
    }

    @Override
    public int size() {
        return 27;
    }

    @Override
    public boolean isEmpty() {
        return stacks.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStack(int slot) {
        return stacks.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(stacks, slot, amount);
        Inventories.writeNbt(portableChest.getNbt(), stacks);
        return result;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack itemStack = stacks.get(slot);
        if (itemStack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        stacks.set(slot, ItemStack.EMPTY);
        Inventories.writeNbt(portableChest.getNbt(), stacks);
        return itemStack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        stacks.set(slot, stack);
        if (!stack.isEmpty() && stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
        Inventories.writeNbt(portableChest.getOrCreateNbt(), stacks);
    }

    @Override
    public void markDirty() {
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        stacks.clear();
        Inventories.writeNbt(portableChest.getOrCreateNbt(), stacks);
    }
}
