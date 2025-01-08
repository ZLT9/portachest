package net.zlt.portachest.item;

import net.minecraft.block.DispenserBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.zlt.portachest.client.item.PortableChestTooltipData;
import net.zlt.portachest.screen.PortableChestScreenHandlerFactory;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class PortableChestItem extends Item implements Equipment {
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
        if (!user.isSneaking()) {
            return equipAndSwap(this, world, user, hand);
        }

        if (world.isClient) {
            return TypedActionResult.success(user.getStackInHand(hand));
        }

        open(user, user.getStackInHand(hand));
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        if (!Screen.hasShiftDown()) {
            return Optional.empty();
        }

        DefaultedList<ItemStack> stacks = DefaultedList.of();
        if (stack.hasNbt()) {
            NbtList stackNbts = stack.getNbt().getList(PortableChestItem.ITEMS_KEY, NbtElement.COMPOUND_TYPE);
            for (int i = 0; i < stackNbts.size(); ++i) {
                NbtCompound stackNbt = stackNbts.getCompound(i);
                if (!stackNbt.isEmpty()) {
                    stacks.add(ItemStack.fromNbt(stackNbt));
                }
            }
        }
        if (stacks.isEmpty()) {
            stacks.add(ItemStack.EMPTY);
        }
        return Optional.of(new PortableChestTooltipData(stacks));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (!Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.portachest.portable_chest.hold_for_contents", Text.translatable("key.keyboard.left.shift").formatted(Formatting.GRAY)).formatted(Formatting.DARK_GRAY));
        }
    }

    public void open(PlayerEntity player, ItemStack stack) {
        player.openHandledScreen(new PortableChestScreenHandlerFactory(stack));
    }

    public boolean canBeExtendedWith(ItemStack stack) {
        return !stack.isEmpty() && stack.isOf(Items.CHEST);
    }

    public ItemStack getLarge() {
        return AllItems.PORTABLE_LARGE_CHEST.getDefaultStack();
    }
}
