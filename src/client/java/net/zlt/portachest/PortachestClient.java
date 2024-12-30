package net.zlt.portachest;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.zlt.portachest.gui.screen.ingame.PortableChestScreen;
import net.zlt.portachest.networking.AllNetworkingConstants;
import net.zlt.portachest.option.AllKeyBindings;
import net.zlt.portachest.screen.AllScreenHandlerTypes;

@Environment(EnvType.CLIENT)
public class PortachestClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(AllScreenHandlerTypes.PORTABLE_CHEST, PortableChestScreen::new);

        AllNetworkingConstants.init();
        AllKeyBindings.init();
    }
}
