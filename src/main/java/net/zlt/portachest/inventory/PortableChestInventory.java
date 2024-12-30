package net.zlt.portachest.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.zlt.portachest.item.PortableChestItem;

public class PortableChestInventory implements Inventory {
    protected static NbtCompound asNbt(ItemStack stack) {
        return stack.isEmpty() ? new NbtCompound() : stack.writeNbt(new NbtCompound());
    }

    public final ItemStack stack;

    public PortableChestInventory(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public int size() {
        return 27;
    }

    @Override
    public boolean isEmpty() {
        NbtCompound nbt = stack.getNbt();

        if (nbt == null || nbt.isEmpty()) {
            return true;
        }

        return nbt.getList(PortableChestItem.ITEMS_KEY, NbtElement.COMPOUND_TYPE).isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        if (slot < 0 || slot >= size()) {
            return ItemStack.EMPTY;
        }

        NbtCompound nbt = stack.getNbt();

        if (nbt == null || nbt.isEmpty()) {
            return ItemStack.EMPTY;
        }

        NbtList itemList = nbt.getList(PortableChestItem.ITEMS_KEY, NbtElement.COMPOUND_TYPE);

        if (slot >= itemList.size()) {
            return ItemStack.EMPTY;
        }

        NbtCompound compound = (NbtCompound) itemList.get(slot);

        if (compound.isEmpty()) {
            return ItemStack.EMPTY;
        }

        return ItemStack.fromNbt(compound);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (slot < 0 || slot >= size()) {
            return ItemStack.EMPTY;
        }

        if (amount <= 0) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = getStack(slot);

        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack removed = stack.split(amount);

        this.stack.getNbt().getList(PortableChestItem.ITEMS_KEY, NbtElement.COMPOUND_TYPE).set(slot, asNbt(stack));

        return removed;
    }

    @Override
    public ItemStack removeStack(int slot) {
        if (slot < 0 || slot >= size()) {
            return ItemStack.EMPTY;
        }

        NbtCompound nbt = stack.getNbt();

        if (nbt == null || nbt.isEmpty()) {
            return ItemStack.EMPTY;
        }

        NbtList itemList = nbt.getList(PortableChestItem.ITEMS_KEY, NbtElement.COMPOUND_TYPE);

        if (slot >= itemList.size()) {
            return ItemStack.EMPTY;
        }

        NbtCompound compound = (NbtCompound) itemList.get(slot);

        if (compound.isEmpty()) {
            return ItemStack.EMPTY;
        }

        itemList.set(slot, new NbtCompound());

        return ItemStack.fromNbt(compound);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot < 0 || slot >= size()) {
            return;
        }

        NbtCompound nbt = this.stack.getOrCreateNbt();

        if (nbt.isEmpty() || !nbt.contains(PortableChestItem.ITEMS_KEY, NbtElement.LIST_TYPE)) {
            nbt.put(PortableChestItem.ITEMS_KEY, new NbtList());
        }

        NbtList itemList = nbt.getList(PortableChestItem.ITEMS_KEY, NbtElement.COMPOUND_TYPE);

        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }

        if (slot >= itemList.size()) {
            for (int i = itemList.size(); i < slot; ++i) {
                itemList.add(new NbtCompound());
            }

            itemList.add(stack.isEmpty() ? new NbtCompound() : stack.writeNbt(new NbtCompound()));

            return;
        }

        itemList.set(slot, asNbt(stack));
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
        NbtCompound nbt = stack.getNbt();

        if (nbt == null || nbt.isEmpty() || !nbt.contains(PortableChestItem.ITEMS_KEY, NbtElement.LIST_TYPE)) {
            return;
        }

        nbt.getList(PortableChestItem.ITEMS_KEY, NbtElement.COMPOUND_TYPE).clear();
    }
}
