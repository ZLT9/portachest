package net.zlt.portachest.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.zlt.portachest.client.render.entity.feature.PortableChestFeatureRenderer;
import net.zlt.portachest.screen.PortableLargeChestScreenHandlerFactory;

public class PortableLargeChestItem extends PortableChestItem {
    public PortableLargeChestItem(Identifier entityTextureId, Settings settings) {
        super(entityTextureId, settings);
    }

    @Override
    public void open(PlayerEntity player, ItemStack stack) {
        player.openHandledScreen(new PortableLargeChestScreenHandlerFactory(stack));
    }

    @Override
    public boolean canBeExtendedWith(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack getLarge() {
        return getDefaultStack();
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Model getModel() {
        return PortableChestFeatureRenderer.LARGE_MODEL;
    }
}
