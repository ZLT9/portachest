package net.zlt.portachest.screen;

import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.Text;

public abstract class AbstractPortableChestScreenHandlerFactory implements NamedScreenHandlerFactory {
    protected final ItemStack stack;

    protected AbstractPortableChestScreenHandlerFactory(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public Text getDisplayName() {
        return stack.getName();
    }
}
