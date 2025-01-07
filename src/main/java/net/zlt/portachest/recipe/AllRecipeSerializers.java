package net.zlt.portachest.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.zlt.portachest.Portachest;

public final class AllRecipeSerializers {
    private AllRecipeSerializers() {
    }

    private static <T extends Recipe<?>> RecipeSerializer<T> register(String id, RecipeSerializer<T> serializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER, Portachest.asId(id), serializer);
    }

    public static void init() {
    }
}
