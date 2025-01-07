package net.zlt.portachest.compat.trinkets;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import net.zlt.portachest.item.PortableChestItem;
import net.zlt.portachest.networking.AllNetworkingConstants;

import java.util.Optional;

public final class Trinkets {
    private Trinkets() {
    }

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(AllNetworkingConstants.OPEN_PORTABLE_CHEST_PACKET_ID, (server, player, handler, buf, responseSender) -> server.execute(() -> {
            ItemStack chestStack = player.getEquippedStack(EquipmentSlot.CHEST);
            if (chestStack.getItem() instanceof PortableChestItem portableChest) {
                portableChest.open(player, chestStack);
                return;
            }

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
