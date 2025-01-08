package net.zlt.portachest.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import net.zlt.portachest.screen.AbstractPortableChestScreenHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Predicate;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {
    @Shadow
    @Final
    public PlayerEntity player;

    @Inject(method = "remove", at = @At("HEAD"))
    private void portachest$remove(Predicate<ItemStack> shouldRemove, int maxCount, Inventory craftingInventory, CallbackInfoReturnable<Integer> cir) {
        if (!(player instanceof ServerPlayerEntity serverPlayer) || !(player.currentScreenHandler instanceof AbstractPortableChestScreenHandler portableChestScreenHandler)) {
            return;
        }

        ItemStack portableChest = portableChestScreenHandler.getPortableChest();
        if (portableChest != null && shouldRemove.test(portableChest)) {
            serverPlayer.closeHandledScreen();
        }
    }

    @Inject(method = "removeStack(II)Lnet/minecraft/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/Inventories;splitStack(Ljava/util/List;II)Lnet/minecraft/item/ItemStack;"))
    private void portachest$removeStack(int slot, int amount, CallbackInfoReturnable<ItemStack> cir, @Local List<ItemStack> list) {
        if (player instanceof ServerPlayerEntity serverPlayer && player.currentScreenHandler instanceof AbstractPortableChestScreenHandler portableChestScreenHandler) {
            ItemStack portableChest = portableChestScreenHandler.getPortableChest();
            if (portableChest != null && list.get(slot).isOf(portableChest.getItem())) {
                serverPlayer.closeHandledScreen();
            }
        }
    }

    @Inject(method = "removeStack(I)Lnet/minecraft/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/collection/DefaultedList;set(ILjava/lang/Object;)Ljava/lang/Object;"))
    private void portachest$removeStack(int slot, CallbackInfoReturnable<ItemStack> cir, @Local DefaultedList<ItemStack> defaultedList) {
        if (player instanceof ServerPlayerEntity serverPlayer && player.currentScreenHandler instanceof AbstractPortableChestScreenHandler portableChestScreenHandler) {
            ItemStack portableChest = portableChestScreenHandler.getPortableChest();
            if (portableChest != null && defaultedList.get(slot).isOf(portableChest.getItem())) {
                serverPlayer.closeHandledScreen();
            }
        }
    }
}
