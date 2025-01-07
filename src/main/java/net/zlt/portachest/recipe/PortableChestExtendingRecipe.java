package net.zlt.portachest.recipe;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.zlt.portachest.item.PortableChestItem;

public class PortableChestExtendingRecipe extends SpecialCraftingRecipe {
    public PortableChestExtendingRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        PortableChestItem portableChest = null;

        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty() && stack.getItem() instanceof PortableChestItem portableChestItem) {
                if (portableChest != null) {
                    return false;
                }

                portableChest = portableChestItem;
            }
        }

        if (portableChest == null) {
            return false;
        }

        boolean hasExtension = false;

        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty() && !(stack.getItem() instanceof PortableChestItem) && portableChest.canBeExtendedWith(stack)) {
                if (hasExtension) {
                    return false;
                }

                hasExtension = true;
            }
        }

        return hasExtension;
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        ItemStack portableChestStack = ItemStack.EMPTY;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty() && stack.getItem() instanceof PortableChestItem) {
                portableChestStack = stack;
                break;
            }
        }

        ItemStack large = ((PortableChestItem) portableChestStack.getItem()).getLarge();
        if (portableChestStack.hasNbt()) {
            large.setNbt(portableChestStack.getNbt().copy());
        }
        return large;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return AllRecipeSerializers.PORTABLE_CHEST_EXTENDING;
    }
}
