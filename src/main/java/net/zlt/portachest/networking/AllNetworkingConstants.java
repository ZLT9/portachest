package net.zlt.portachest.networking;

import net.minecraft.util.Identifier;
import net.zlt.portachest.Portachest;

public final class AllNetworkingConstants {
    private AllNetworkingConstants() {
    }

    public static final Identifier OPEN_PORTABLE_CHEST_PACKET_ID = Portachest.asId("open_portable_chest");

    public static void init() {
    }
}
