package net.zlt.portachest;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.zlt.portachest.compat.Mods;
import net.zlt.portachest.compat.trinkets.Trinkets;
import net.zlt.portachest.item.AllItemGroups;
import net.zlt.portachest.item.AllItems;
import net.zlt.portachest.item.PortableChestItem;
import net.zlt.portachest.networking.AllNetworkingConstants;
import net.zlt.portachest.option.PortableChestSlotPriorityLevel;
import net.zlt.portachest.recipe.AllRecipeSerializers;
import net.zlt.portachest.screen.AllScreenHandlerTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Portachest implements ModInitializer {
    public static final String MOD_ID = "portachest";
    public static final Logger LOGGER = LoggerFactory.getLogger("Port-a-Chest");

    @Override
    public void onInitialize() {
        Mods.init();
        AllScreenHandlerTypes.init();
        AllItems.init();
        AllItemGroups.init();
        AllRecipeSerializers.init();
        AllNetworkingConstants.init();

        if (Mods.TRINKETS.isLoaded) {
            Trinkets.init();
        } else {
            ServerPlayNetworking.registerGlobalReceiver(AllNetworkingConstants.OPEN_PORTABLE_CHEST_PACKET_ID, (server, player, handler, buf, responseSender) -> {
                int level1 = buf.readInt();
                int level2 = buf.readInt();
                int level3 = buf.readInt();
                int level4 = buf.readInt();

                server.execute(() -> {
                    if (openPortableChestIn(level1, player)) {
                        return;
                    }
                    if (openPortableChestIn(level2, player)) {
                        return;
                    }
                    if (openPortableChestIn(level3, player)) {
                        return;
                    }
                    openPortableChestIn(level4, player);
                });
            });
        }
    }

    public static boolean openPortableChestInChestSlot(ServerPlayerEntity player) {
        ItemStack chestStack = player.getEquippedStack(EquipmentSlot.CHEST);
        if (chestStack.getItem() instanceof PortableChestItem portableChest) {
            portableChest.open(player, chestStack);
            return true;
        }

        return false;
    }

    public static boolean openPortableChestInMainHand(ServerPlayerEntity player) {
        ItemStack mainHandStack = player.getMainHandStack();
        if (mainHandStack.getItem() instanceof PortableChestItem portableChest) {
            portableChest.open(player, mainHandStack);
            return true;
        }

        return false;
    }

    public static boolean openPortableChestInOffHand(ServerPlayerEntity player) {
        ItemStack offHandStack = player.getOffHandStack();
        if (offHandStack.getItem() instanceof PortableChestItem portableChest) {
            portableChest.open(player, offHandStack);
            return true;
        }

        return false;
    }

    public static boolean openPortableChestIn(int level, ServerPlayerEntity player) {
        if (level == PortableChestSlotPriorityLevel.VANILLA_CHEST.ordinal()) {
            return openPortableChestInChestSlot(player);
        }
        if (level == PortableChestSlotPriorityLevel.TRINKETS_CHEST_BACK.ordinal()) {
            return false;
        }
        if (level == PortableChestSlotPriorityLevel.VANILLA_MAIN_HAND.ordinal()) {
            return openPortableChestInMainHand(player);
        }
        if (level == PortableChestSlotPriorityLevel.VANILLA_OFF_HAND.ordinal()) {
            return openPortableChestInOffHand(player);
        }
        return false;
    }

    public static Identifier asId(String path) {
        return new Identifier(MOD_ID, path);
    }
}
