package net.zlt.portachest.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.zlt.portachest.Portachest;

public final class AllRecipeSerializers {
    private AllRecipeSerializers() {
    }

    public static final RecipeSerializer<?> PORTABLE_CHEST_EXTENDING = register("crafting_special_portablechestextending", new SpecialRecipeSerializer<>(PortableChestExtendingRecipe::new));

    private static <T extends Recipe<?>> RecipeSerializer<T> register(String id, RecipeSerializer<T> serializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER, Portachest.asId(id), serializer);
    }

    public static void init() {
    }
}
