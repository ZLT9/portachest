package net.zlt.portachest;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.text.Text;
import net.zlt.portachest.compat.Mods;
import net.zlt.portachest.gui.screen.ingame.PortableChestScreen;
import net.zlt.portachest.networking.AllNetworkingConstants;
import net.zlt.portachest.option.AllKeyBindings;
import net.zlt.portachest.screen.AllScreenHandlerTypes;

@Environment(EnvType.CLIENT)
public class PortachestClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        if (Mods.VARIANT_VANILLA_BLOCKS.isLoaded) {
            ResourceManagerHelper.registerBuiltinResourcePack(Portachest.asId("compat_variantvanillablocks"), FabricLoader.getInstance().getModContainer(Portachest.MOD_ID).orElseThrow(() -> new IllegalStateException("Port-a-Chest's ModContainer couldn't be found!")), Text.translatable("portachest.resource_pack.compat_variantvanillablocks"), ResourcePackActivationType.NORMAL);
        }

        HandledScreens.register(AllScreenHandlerTypes.PORTABLE_CHEST, PortableChestScreen::new);

        AllNetworkingConstants.init();
        AllKeyBindings.init();
    }
}
