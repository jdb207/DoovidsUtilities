package net.james.gui.components.clickgui.settings;

import net.james.gui.components.clickgui.ModuleButtonComponent;
import net.james.gui.components.clickgui.PanelComponent;
import net.james.setting.Setting;
import net.james.setting.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;

public class NumberSettingComponent extends AbstractSettingComponent<NumberSetting> {
    private static final int NUMBER_SETTING_HEIGHT = 20;
    private boolean dragging = false;
    private int sliderPosition;
    private int sliderWidth;
    private static final int SLIDER_HEIGHT = 2;

    public NumberSettingComponent(NumberSetting setting) {
        super(setting);
    }


    @Override
    protected void drawContents(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getInstance();
        sliderPosition = getX() + 2;
        sliderWidth = getWidth() - mc.font.width("100");

        double percentage = (setting.getValue() - setting.getMin()) / (setting.getMax() - setting.getMin());
        int filledWidth = (int) (percentage * sliderWidth);

        guiGraphicsExtractor.text(mc.font, setting.getName(), getX() + 3, getY() + 4, EnumSettingComponent.TEXT_COLOR);
        guiGraphicsExtractor.text(mc.font, setting.getValue().toString(), getX() + getWidth() - mc.font.width("1000"), getY() + 4, EnumSettingComponent.TEXT_COLOR);
        guiGraphicsExtractor.horizontalLine(sliderPosition, sliderPosition + sliderWidth,getY() + mc.font.lineHeight + 7, EnumSettingComponent.TEXT_COLOR);
        guiGraphicsExtractor.horizontalLine(sliderPosition, sliderPosition + filledWidth, getY() + mc.font.lineHeight + 7, ARGB.color(255,255,0,0));
    }

    @Override
    protected void drawBorder(GuiGraphicsExtractor guiGraphicsExtractor) {
        guiGraphicsExtractor.horizontalLine(getX() + 1, getX() + getWidth() - 1,getY() + getHeight() - 1, ModuleButtonComponent.MODULE_BUTTON_COLOR);
    }

    @Override
    protected void drawBackground(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY) {
        //TODO add Theme.java to contain all global colors etc
        int color = isMouseOver(mouseX, mouseY)
                ? EnumSettingComponent.SETTING_HOVER_BACKGROUND_COLOR
                : EnumSettingComponent.SETTING_BACKGROUND_COLOR;
        guiGraphicsExtractor.fill(
                getX() + 1,
                getY(),
                getX() + PanelComponent.PANEL_WIDTH,
                getY() + getHeight() - 1,
                color
        );
    }



    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        Minecraft mc = Minecraft.getInstance();

        int sliderY = getY() + mc.font.lineHeight + 7;

        return mouseX >= sliderPosition &&
                mouseX <= sliderPosition + sliderWidth &&
                mouseY >= sliderY - 3 &&
                mouseY <= sliderY + 3;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if(!isMouseOver(mouseX, mouseY) || buttonPressed != 0) {
            return false;
        }
        dragging=true;
        updateValue(mouseX);
        return true;
    }

    public void updateValue(double mouseX) {
        double percentage = (mouseX - sliderPosition) / sliderWidth;

        percentage = Math.clamp(percentage, 0.0, 1.0);

        int value = (int) Math.round(
                setting.getMin()
                        + percentage * (setting.getMax() - setting.getMin())
        );

        setting.setValue((double) value);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int buttonPressed) {
        if(!dragging) return false;
        dragging = false;
        return true;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int buttonPressed, double dragX, double dragY) {
        if(!dragging) return false;
        updateValue(mouseX);
        return true;
    }

    @Override
    public int getHeight() {
        return NUMBER_SETTING_HEIGHT;
    }
}
