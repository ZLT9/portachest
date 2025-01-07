package net.zlt.portachest.client.render.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.zlt.portachest.client.render.entity.model.PortableChestModel;
import net.zlt.portachest.compat.Mods;
import net.zlt.portachest.compat.trinkets.Trinkets;
import net.zlt.portachest.item.PortableChestItem;
import net.zlt.portachest.item.PortableLargeChestItem;

@Environment(EnvType.CLIENT)
public class PortableChestFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    private static final PortableChestModel MODEL = new PortableChestModel(PortableChestModel.getTexturedModelData().createModel());
    private static final PortableChestModel LARGE_MODEL = new PortableChestModel(PortableChestModel.getLargeTexturedModelData().createModel());

    public PortableChestFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        PortableChestItem portableChest = null;

        if (entity.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof PortableChestItem portableChestItem) {
            portableChest = portableChestItem;
        } else if (Mods.TRINKETS.isLoaded) {
            portableChest = Trinkets.getPortableChest(entity);
        }

        if (portableChest == null) {
            return;
        }

        matrices.push();
        matrices.translate(-0.25, 0, 0.125);
        getContextModel().body.rotate(matrices);
        PortableChestModel model = portableChest instanceof PortableLargeChestItem ? LARGE_MODEL : MODEL;
        model.render(matrices, vertexConsumers.getBuffer(model.getLayer(portableChest.entityTextureId)), light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        matrices.pop();
    }
}
