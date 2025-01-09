package net.zlt.portachest.client.gui.screen.option;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.zlt.portachest.client.gui.widget.ArrowButtonWidget;
import net.zlt.portachest.client.option.Config;
import net.zlt.portachest.option.PortableChestSlotPriorityLevel;

@Environment(EnvType.CLIENT)
public class ConfigScreen extends Screen {
    private final Screen parent;
    private final ButtonWidget portableChestSlotPriorityLevelButton1 = ButtonWidget.builder(Config.getPortableChestSlotPriorityLevel(0).label, ConfigScreen::noAction)
        .size(200, 20)
        .build();
    private final ButtonWidget portableChestSlotPriorityLevelButton2 = ButtonWidget.builder(Config.getPortableChestSlotPriorityLevel(1).label, ConfigScreen::noAction)
        .size(200, 20)
        .build();
    private final ButtonWidget portableChestSlotPriorityLevelButton3 = ButtonWidget.builder(Config.getPortableChestSlotPriorityLevel(2).label, ConfigScreen::noAction)
        .size(200, 20)
        .build();
    private final ButtonWidget portableChestSlotPriorityLevelButton4 = ButtonWidget.builder(Config.getPortableChestSlotPriorityLevel(3).label, ConfigScreen::noAction)
        .size(200, 20)
        .build();
    private final ButtonWidget[] portableChestSlotPriorityLevelButtons = {
        portableChestSlotPriorityLevelButton1,
        portableChestSlotPriorityLevelButton2,
        portableChestSlotPriorityLevelButton3,
        portableChestSlotPriorityLevelButton4
    };
    private final ArrowButtonWidget downArrowButton1 = createDownArrowButton(button -> swapPortableChestSlotPriorityLevels(0, 1));
    private final ArrowButtonWidget upArrowButton2 = createUpArrowButton(button -> swapPortableChestSlotPriorityLevels(0, 1));
    private final ArrowButtonWidget downArrowButton2 = createDownArrowButton(button -> swapPortableChestSlotPriorityLevels(1, 2));
    private final ArrowButtonWidget upArrowButton3 = createUpArrowButton(button -> swapPortableChestSlotPriorityLevels(1, 2));
    private final ArrowButtonWidget downArrowButton3 = createDownArrowButton(button -> swapPortableChestSlotPriorityLevels(2, 3));
    private final ArrowButtonWidget upArrowButton4 = createUpArrowButton(button -> swapPortableChestSlotPriorityLevels(2, 3));
    private final ButtonWidget doneButton = ButtonWidget.builder(Text.translatable("gui.done"), button -> close())
        .size(200, 20)
        .build();
    private final ButtonWidget donateButton = ButtonWidget.builder(Text.translatable("screen.portachest.config.donate"), button -> Util.getOperatingSystem().open("https://ko-fi.com/zlt09"))
        .size(100, 20)
        .build();

    public ConfigScreen(Screen parent) {
        super(Text.translatable("screen.portachest.config.title"));
        this.parent = parent;

        portableChestSlotPriorityLevelButton1.active = false;
        portableChestSlotPriorityLevelButton2.active = false;
        portableChestSlotPriorityLevelButton3.active = false;
        portableChestSlotPriorityLevelButton4.active = false;

        updateBackTrinketSlotTooltip();
    }

    @Override
    protected void init() {
        int buttonX = width / 2 - 100;
        portableChestSlotPriorityLevelButton1.setPosition(buttonX, height / 2 - 32);
        portableChestSlotPriorityLevelButton2.setPosition(buttonX, height / 2 - 10);
        portableChestSlotPriorityLevelButton3.setPosition(buttonX, height / 2 + 12);
        portableChestSlotPriorityLevelButton4.setPosition(buttonX, height / 2 + 34);

        int arrowButtonX = width / 2 + 100;
        downArrowButton1.setPosition(arrowButtonX, height / 2 - 22);
        upArrowButton2.setPosition(arrowButtonX, height / 2 - 10);
        downArrowButton2.setPosition(arrowButtonX, height / 2);
        upArrowButton3.setPosition(arrowButtonX, height / 2 + 12);
        downArrowButton3.setPosition(arrowButtonX, height / 2 + 22);
        upArrowButton4.setPosition(arrowButtonX, height / 2 + 34);

        doneButton.setPosition(buttonX, height - 32);

        donateButton.setPosition(width - 120, 9);

        addDrawableChild(portableChestSlotPriorityLevelButton1);
        addDrawableChild(portableChestSlotPriorityLevelButton2);
        addDrawableChild(portableChestSlotPriorityLevelButton3);
        addDrawableChild(portableChestSlotPriorityLevelButton4);

        addDrawableChild(downArrowButton1);
        addDrawableChild(upArrowButton2);
        addDrawableChild(downArrowButton2);
        addDrawableChild(upArrowButton3);
        addDrawableChild(downArrowButton3);
        addDrawableChild(upArrowButton4);

        addDrawableChild(doneButton);

        addDrawableChild(donateButton);
    }

    @Override
    public void close() {
        Config.saveFile();
        client.setScreen(parent);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackgroundTexture(context);
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("screen.portachest.config.title"), width / 2, 15, 0xffffff);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("screen.portachest.config.portable_chest_priority"), width / 2, height / 2 - 48, 0xffffff);
    }

    protected ArrowButtonWidget createUpArrowButton(ButtonWidget.PressAction onPress) {
        return ArrowButtonWidget.builder(onPress)
            .size(20, 10)
            .up()
            .build();
    }

    protected ArrowButtonWidget createDownArrowButton(ButtonWidget.PressAction onPress) {
        return ArrowButtonWidget.builder(onPress)
            .size(20, 10)
            .down()
            .build();
    }

    protected void updateBackTrinketSlotTooltip() {
        portableChestSlotPriorityLevelButton1.setTooltip(null);
        portableChestSlotPriorityLevelButton2.setTooltip(null);
        portableChestSlotPriorityLevelButton3.setTooltip(null);
        portableChestSlotPriorityLevelButton4.setTooltip(null);

        int level;
        for (level = 0; level < 4; ++level) {
            if (Config.getPortableChestSlotPriorityLevel(level) == PortableChestSlotPriorityLevel.TRINKETS_CHEST_BACK) {
                break;
            }
        }

        portableChestSlotPriorityLevelButtons[level].setTooltip(Tooltip.of(Text.translatable("config.portachest.portable_chest_slot_priority_level.trinkets_chest_back.description")));
    }

    protected void swapPortableChestSlotPriorityLevels(int a, int b) {
        Config.swapPortableChestSlotPriorityLevels(a, b);
        portableChestSlotPriorityLevelButtons[a].setMessage(Config.getPortableChestSlotPriorityLevel(a).label);
        portableChestSlotPriorityLevelButtons[b].setMessage(Config.getPortableChestSlotPriorityLevel(b).label);
        updateBackTrinketSlotTooltip();
    }

    protected static void noAction(ButtonWidget button) {
    }
}
