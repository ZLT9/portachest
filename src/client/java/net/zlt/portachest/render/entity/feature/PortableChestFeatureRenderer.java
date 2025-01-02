package net.zlt.portachest.render.entity.feature;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.zlt.portachest.Portachest;
import net.zlt.portachest.compat.Mods;
import net.zlt.portachest.item.PortableChestItem;
import net.zlt.portachest.render.entity.model.PortableChestModel;

import java.util.Optional;

@Environment(EnvType.CLIENT)
public class PortableChestFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    private static final PortableChestModel portableChestModel = new PortableChestModel(PortableChestModel.getTexturedModelData().createModel());
    private static final Identifier TEXTURE_ID = Portachest.asId("textures/entity/portable_chest.png");

    public PortableChestFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        PortableChestItem portableChest = null;

        if (entity.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof PortableChestItem portableChestItem) {
            portableChest = portableChestItem;
        } else if (Mods.TRINKETS.isLoaded) {
            Optional<TrinketComponent> trinketComponent = TrinketsApi.getTrinketComponent(entity);

            if (trinketComponent.isPresent()) {
                for (Pair<SlotReference, ItemStack> equipped : trinketComponent.get().getAllEquipped()) {
                    ItemStack stack = equipped.getRight();

                    if (stack.getItem() instanceof PortableChestItem portableChestItem) {
                        portableChest = portableChestItem;
                        break;
                    }
                }
            }
        }

        if (portableChest == null) {
            return;
        }

        matrices.push();
        matrices.translate(-0.25, 0, 0.125);
        getContextModel().body.rotate(matrices);
        portableChestModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntitySolid(TEXTURE_ID)), light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        matrices.pop();
    }
}
