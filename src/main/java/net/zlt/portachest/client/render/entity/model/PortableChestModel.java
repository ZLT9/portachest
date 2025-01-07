package net.zlt.portachest.client.render.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class PortableChestModel extends Model {
    private static TexturedModelData getTexturedModelData(float modelHeight) {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(0, 0).cuboid(0.0f, 0.0f, 0.0f, 8.0f, modelHeight, 3.0f);
        modelPartData.addChild("portable_chest", modelPartBuilder, ModelTransform.NONE);
        return TexturedModelData.of(modelData, 24, 24);
    }

    public static TexturedModelData getTexturedModelData() {
        return getTexturedModelData(8.0f);
    }

    public static TexturedModelData getLargeTexturedModelData() {
        return getTexturedModelData(12.0f);
    }

    protected final ModelPart modelPart;

    public PortableChestModel(ModelPart modelPart) {
        super(RenderLayer::getEntitySolid);
        this.modelPart = modelPart;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        modelPart.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
