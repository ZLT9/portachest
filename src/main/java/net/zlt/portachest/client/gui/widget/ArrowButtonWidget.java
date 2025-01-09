package net.zlt.portachest.client.gui.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.util.Identifier;
import net.zlt.portachest.Portachest;

@Environment(EnvType.CLIENT)
public class ArrowButtonWidget extends ButtonWidget {
    public static final Identifier BUTTONS_TEXTURE = Portachest.asId("textures/gui/buttons.png");

    protected final int u;

    protected ArrowButtonWidget(int x, int y, int width, int height, PressAction onPress, NarrationSupplier narrationSupplier, int u) {
        super(x, y, width, height, null, onPress, narrationSupplier);
        this.u = u;
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawTexture(BUTTONS_TEXTURE, getX(), getY(), u, isSelected() ? 10 : 0, width, height, 64, 64);
    }

    public static Builder builder(PressAction onPress) {
        return new Builder(onPress);
    }

    @Environment(EnvType.CLIENT)
    public static class Builder {
        private final PressAction onPress;
        private int x;
        private int y;
        private int width = 150;
        private int height = 20;
        private NarrationSupplier narrationSupplier;
        private int u;

        public Builder(PressAction onPress) {
            narrationSupplier = ButtonWidget.DEFAULT_NARRATION_SUPPLIER;
            this.onPress = onPress;
        }

        public Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder dimensions(int x, int y, int width, int height) {
            return position(x, y).size(width, height);
        }

        public Builder narrationSupplier(NarrationSupplier narrationSupplier) {
            this.narrationSupplier = narrationSupplier;
            return this;
        }

        public Builder up() {
            u = 0;
            return this;
        }

        public Builder down() {
            u = 20;
            return this;
        }

        public ArrowButtonWidget build() {
            return new ArrowButtonWidget(x, y, width, height, onPress, narrationSupplier, u);
        }
    }
}
