package net.zlt.portachest.compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class PortachestEmiPlugin implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
    }
}
