package net.zlt.portachest;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.zlt.portachest.compat.Mods;
import net.zlt.portachest.compat.trinkets.Trinkets;
import net.zlt.portachest.item.AllItemGroups;
import net.zlt.portachest.item.AllItems;
import net.zlt.portachest.item.PortableChestItem;
import net.zlt.portachest.networking.AllNetworkingConstants;
import net.zlt.portachest.recipe.AllRecipeSerializers;
import net.zlt.portachest.screen.AllScreenHandlerTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Portachest implements ModInitializer {
    public static final String MOD_ID = "portachest";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        Mods.init();
        AllScreenHandlerTypes.init();
        AllItems.init();
        AllItemGroups.init();
        AllRecipeSerializers.init();

        if (Mods.TRINKETS.isLoaded) {
            Trinkets.init();
        } else {
            ServerPlayNetworking.registerGlobalReceiver(AllNetworkingConstants.OPEN_PORTABLE_CHEST_PACKET_ID, (server, player, handler, buf, responseSender) -> server.execute(() -> {
                ItemStack chestStack = player.getEquippedStack(EquipmentSlot.CHEST);
                if (chestStack.getItem() instanceof PortableChestItem portableChest) {
                    portableChest.open(player, chestStack);
                    return;
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
    }

    public static Identifier asId(String path) {
        return new Identifier(MOD_ID, path);
    }
}
