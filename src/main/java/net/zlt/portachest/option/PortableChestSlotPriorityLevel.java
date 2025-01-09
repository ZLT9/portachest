package net.zlt.portachest.option;

import net.minecraft.text.Text;

public enum PortableChestSlotPriorityLevel {
    VANILLA_CHEST(Text.translatable("config.portachest.portable_chest_slot_priority_level.vanilla_chest")),
    TRINKETS_CHEST_BACK(Text.translatable("config.portachest.portable_chest_slot_priority_level.trinkets_chest_back")),
    VANILLA_MAIN_HAND(Text.translatable("config.portachest.portable_chest_slot_priority_level.vanilla_main_hand")),
    VANILLA_OFF_HAND(Text.translatable("config.portachest.portable_chest_slot_priority_level.vanilla_off_hand"));

    private static final PortableChestSlotPriorityLevel[] values = values();

    public final Text label;

    PortableChestSlotPriorityLevel(Text label) {
        this.label = label;
    }

    public static PortableChestSlotPriorityLevel fromOrdinal(int ordinal) {
        return values[ordinal];
    }
}
