package net.zlt.portachest;

import dev.emi.trinkets.TrinketsMain;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.zlt.portachest.item.AllItems;
import net.zlt.portachest.item.PortableChestItem;
import net.zlt.portachest.networking.AllNetworkingConstants;
import net.zlt.portachest.screen.AllScreenHandlerTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Portachest implements ModInitializer {
    public static final String MOD_ID = "portachest";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        AllScreenHandlerTypes.init();
        AllItems.init();

        ServerPlayNetworking.registerGlobalReceiver(AllNetworkingConstants.OPEN_PORTABLE_CHEST_PACKET_ID, (server, player, handler, buf, responseSender) -> server.execute(() -> {
            ItemStack chestStack = player.getEquippedStack(EquipmentSlot.CHEST);
            if (chestStack.getItem() instanceof PortableChestItem portableChest) {
                portableChest.open(player, chestStack);
                return;
            }

            if (FabricLoader.getInstance().isModLoaded(TrinketsMain.MOD_ID)) {
                Optional<TrinketComponent> trinketComponent = TrinketsApi.getTrinketComponent(player);

                if (trinketComponent.isPresent()) {
                    for (Pair<SlotReference, ItemStack> equipped : trinketComponent.get().getAllEquipped()) {
                        ItemStack stack = equipped.getRight();

                        if (stack.getItem() instanceof PortableChestItem portableChest) {
                            portableChest.open(player, stack);
                            return;
                        }
                    }
                }
            }

            ItemStack mainHandStack = player.getMainHandStack();
            if (mainHandStack.getItem() instanceof PortableChestItem portableChest) {
                portableChest.open(player, mainHandStack);
                return;
            }

            ItemStack offHandStack = player.getOffHandStack();
            if (offHandStack.getItem() instanceof PortableChestItem portableChest) {
                portableChest.open(player, offHandStack);
            }
        }));
    }

    public static Identifier asId(String path) {
        return new Identifier(MOD_ID, path);
    }
}
