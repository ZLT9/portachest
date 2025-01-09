package net.zlt.portachest.compat.trinkets;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Pair;
import net.zlt.portachest.Portachest;
import net.zlt.portachest.item.PortableChestItem;
import net.zlt.portachest.networking.AllNetworkingConstants;
import net.zlt.portachest.option.PortableChestSlotPriorityLevel;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public final class Trinkets {
    private Trinkets() {
    }

    public static void init() {
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

    public static boolean openPortableChestInBackTrinketSlot(ServerPlayerEntity player) {
        Optional<TrinketComponent> trinketComponent = TrinketsApi.getTrinketComponent(player);
        if (trinketComponent.isPresent()) {
            for (Pair<SlotReference, ItemStack> equipped : trinketComponent.get().getAllEquipped()) {
                ItemStack stack = equipped.getRight();
                if (stack.getItem() instanceof PortableChestItem portableChest) {
                    portableChest.open(player, stack);
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean openPortableChestIn(int level, ServerPlayerEntity player) {
        if (level == PortableChestSlotPriorityLevel.VANILLA_CHEST.ordinal()) {
            return Portachest.openPortableChestInChestSlot(player);
        }
        if (level == PortableChestSlotPriorityLevel.TRINKETS_CHEST_BACK.ordinal()) {
            return openPortableChestInBackTrinketSlot(player);
        }
        if (level == PortableChestSlotPriorityLevel.VANILLA_MAIN_HAND.ordinal()) {
            return Portachest.openPortableChestInMainHand(player);
        }
        if (level == PortableChestSlotPriorityLevel.VANILLA_OFF_HAND.ordinal()) {
            return Portachest.openPortableChestInOffHand(player);
        }
        return false;
    }

    @Nullable
    public static PortableChestItem getPortableChest(LivingEntity entity) {
        Optional<TrinketComponent> trinketComponent = TrinketsApi.getTrinketComponent(entity);
        if (trinketComponent.isEmpty()) {
            return null;
        }

        for (Pair<SlotReference, ItemStack> equipped : trinketComponent.get().getAllEquipped()) {
            if (equipped.getRight().getItem() instanceof PortableChestItem portableChestItem) {
                return portableChestItem;
            }
        }

        return null;
    }
}
