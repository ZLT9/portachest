package net.zlt.portachest.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.text.Text;
import net.zlt.portachest.Portachest;
import net.zlt.portachest.client.gui.screen.ingame.PortableChestScreen;
import net.zlt.portachest.client.gui.screen.ingame.PortableLargeChestScreen;
import net.zlt.portachest.client.gui.tooltip.PortableChestTooltipComponent;
import net.zlt.portachest.client.item.PortableChestTooltipData;
import net.zlt.portachest.client.option.AllKeyBindings;
import net.zlt.portachest.compat.Mods;
import net.zlt.portachest.networking.AllNetworkingConstants;
import net.zlt.portachest.screen.AllScreenHandlerTypes;

@Environment(EnvType.CLIENT)
public class PortachestClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        if (Mods.VARIANT_VANILLA_BLOCKS.isLoaded) {
            ResourceManagerHelper.registerBuiltinResourcePack(Portachest.asId("compat_variantvanillablocks"), FabricLoader.getInstance().getModContainer(Portachest.MOD_ID).orElseThrow(() -> new IllegalStateException("Port-a-Chest's ModContainer couldn't be found!")), Text.translatable("portachest.resource_pack.compat_variantvanillablocks"), ResourcePackActivationType.NORMAL);
        }

        HandledScreens.register(AllScreenHandlerTypes.PORTABLE_CHEST, PortableChestScreen::new);
        HandledScreens.register(AllScreenHandlerTypes.PORTABLE_LARGE_CHEST, PortableLargeChestScreen::new);

        TooltipComponentCallback.EVENT.register(data -> data instanceof PortableChestTooltipData portableChestTooltipData ? new PortableChestTooltipComponent(portableChestTooltipData) : null);

        AllNetworkingConstants.init();
        AllKeyBindings.init();
    }
}
