package net.zlt.portachest.compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.stack.EmiStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.zlt.portachest.Portachest;
import net.zlt.portachest.compat.Mods;
import net.zlt.portachest.compat.variantvanillablocks.VariantVanillaBlocks;
import net.zlt.portachest.item.AllItems;
import net.zlt.portachest.recipe.AllRecipeSerializers;

import java.util.List;

@Environment(EnvType.CLIENT)
public class PortachestEmiPlugin implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        Identifier portableChestExtendingRecipeId = Registries.RECIPE_SERIALIZER.getId(AllRecipeSerializers.PORTABLE_CHEST_EXTENDING);
        if (portableChestExtendingRecipeId == null) {
            Portachest.LOGGER.error("Failed to find Portable Chest Extending recipe id!");
            return;
        }

        String portableChestExtendingRecipeIdPath = portableChestExtendingRecipeId.getPath();
        registerPortableChestExtendingRecipe(registry, AllItems.PORTABLE_CHEST, AllItems.PORTABLE_LARGE_CHEST, Items.CHEST, "base", portableChestExtendingRecipeIdPath);
        if (Mods.VARIANT_VANILLA_BLOCKS.isLoaded) {
            registerPortableChestExtendingRecipe(registry, AllItems.PORTABLE_SPRUCE_CHEST, AllItems.PORTABLE_LARGE_SPRUCE_CHEST, VariantVanillaBlocks.getSpruceChest(), "spruce", portableChestExtendingRecipeIdPath);
            registerPortableChestExtendingRecipe(registry, AllItems.PORTABLE_BIRCH_CHEST, AllItems.PORTABLE_LARGE_BIRCH_CHEST, VariantVanillaBlocks.getBirchChest(), "birch", portableChestExtendingRecipeIdPath);
            registerPortableChestExtendingRecipe(registry, AllItems.PORTABLE_JUNGLE_CHEST, AllItems.PORTABLE_LARGE_JUNGLE_CHEST, VariantVanillaBlocks.getJungleChest(), "jungle", portableChestExtendingRecipeIdPath);
            registerPortableChestExtendingRecipe(registry, AllItems.PORTABLE_ACACIA_CHEST, AllItems.PORTABLE_LARGE_ACACIA_CHEST, VariantVanillaBlocks.getAcaciaChest(), "acacia", portableChestExtendingRecipeIdPath);
            registerPortableChestExtendingRecipe(registry, AllItems.PORTABLE_DARK_OAK_CHEST, AllItems.PORTABLE_LARGE_DARK_OAK_CHEST, VariantVanillaBlocks.getDarkOakChest(), "dark_oak", portableChestExtendingRecipeIdPath);
            registerPortableChestExtendingRecipe(registry, AllItems.PORTABLE_MANGROVE_CHEST, AllItems.PORTABLE_LARGE_MANGROVE_CHEST, VariantVanillaBlocks.getMangroveChest(), "mangrove", portableChestExtendingRecipeIdPath);
            registerPortableChestExtendingRecipe(registry, AllItems.PORTABLE_CHERRY_CHEST, AllItems.PORTABLE_LARGE_CHERRY_CHEST, VariantVanillaBlocks.getCherryChest(), "cherry", portableChestExtendingRecipeIdPath);
            registerPortableChestExtendingRecipe(registry, AllItems.PORTABLE_BAMBOO_CHEST, AllItems.PORTABLE_LARGE_BAMBOO_CHEST, VariantVanillaBlocks.getBambooChest(), "bamboo", portableChestExtendingRecipeIdPath);
            registerPortableChestExtendingRecipe(registry, AllItems.PORTABLE_CRIMSON_CHEST, AllItems.PORTABLE_LARGE_CRIMSON_CHEST, VariantVanillaBlocks.getCrimsonChest(), "crimson", portableChestExtendingRecipeIdPath);
            registerPortableChestExtendingRecipe(registry, AllItems.PORTABLE_WARPED_CHEST, AllItems.PORTABLE_LARGE_WARPED_CHEST, VariantVanillaBlocks.getWarpedChest(), "warped", portableChestExtendingRecipeIdPath);
        }
    }

    private void registerPortableChestExtendingRecipe(EmiRegistry registry, Item portableChest, Item portableLargeChest, Item chest, String woodName, String portableChestExtendingRecipeIdPath) {
        registry.addRecipe(new EmiCraftingRecipe(
            List.of(EmiStack.of(portableChest), EmiStack.of(chest)),
            EmiStack.of(portableLargeChest),
            Portachest.asId(portableChestExtendingRecipeIdPath + "/" + woodName)
        ));
    }

    private void registerPortableChestExtendingRecipe(EmiRegistry registry, Item portableChest, Item portableLargeChest, Block chest, String woodName, String portableChestExtendingRecipeIdPath) {
        registerPortableChestExtendingRecipe(registry, portableChest, portableLargeChest, chest.asItem(), woodName, portableChestExtendingRecipeIdPath);
    }
}
