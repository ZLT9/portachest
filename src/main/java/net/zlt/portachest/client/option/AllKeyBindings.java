package net.zlt.portachest.client.option;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import net.zlt.portachest.networking.AllNetworkingConstants;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public final class AllKeyBindings {
    private AllKeyBindings() {
    }

    public static final KeyBinding OPEN_PORTABLE_CHEST = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        "key.portachest.open_portable_chest",
        InputUtil.Type.KEYSYM,
        GLFW.GLFW_KEY_B,
        "category.portachest.portachest"
    ));

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_PORTABLE_CHEST.wasPressed()) {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeInt(Config.getPortableChestSlotPriorityLevel(0).ordinal());
                buf.writeInt(Config.getPortableChestSlotPriorityLevel(1).ordinal());
                buf.writeInt(Config.getPortableChestSlotPriorityLevel(2).ordinal());
                buf.writeInt(Config.getPortableChestSlotPriorityLevel(3).ordinal());
                ClientPlayNetworking.send(AllNetworkingConstants.OPEN_PORTABLE_CHEST_PACKET_ID, buf);
            }
        });
    }
}
