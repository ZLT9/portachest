package net.zlt.portachest.item;

import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Equipment;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.zlt.portachest.inventory.PortableChestInventory;
import net.zlt.portachest.screen.PortableChestScreenHandler;

public class PortableChestItem extends TrinketItem implements Equipment {
    public static final String ITEMS_KEY = "Items";

    public final Identifier entityTextureId;

    public PortableChestItem(Identifier entityTextureId, Settings settings) {
        super(settings);
        this.entityTextureId = entityTextureId;

        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.CHEST;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            if (world.isClient) {
                return TypedActionResult.success(user.getStackInHand(hand));
            }

            open(user, user.getStackInHand(hand));

            return TypedActionResult.consume(user.getStackInHand(hand));
        }

        return equipAndSwap(this, world, user, hand);
    }

    public void open(PlayerEntity player, ItemStack stack) {
        player.openHandledScreen(new NamedScreenHandlerFactory() {
            @Override
            public Text getDisplayName() {
                return stack.getName();
            }

            @Override
            public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                return new PortableChestScreenHandler(syncId, playerInventory, new PortableChestInventory(stack));
            }
        });
    }
}
