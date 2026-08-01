package net.james.gui.components.clickgui.settings;

import net.james.gui.components.clickgui.PanelComponent;
import net.james.setting.Setting;
import net.james.setting.settings.BooleanSetting;
import net.james.setting.settings.EnumSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;

public class EnumSettingComponent extends AbstractSettingComponent {
    private static final int ENUM_SETTING_HEIGHT = 20;
    public static final int SETTING_BACKGROUND_COLOR       = 0xFF3A4F66;
    public static final int SETTING_HOVER_BACKGROUND_COLOR = 0xFF39414D;
    public static final int ENUM_TEXT_COLOUR = 0xFFFFFFFF;


    public EnumSettingComponent(Setting setting) {
        super(setting);
    }

    @Override
    protected void drawContents(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY) {
        EnumSetting enumSetting = (EnumSetting) setting;
        Minecraft mc = Minecraft.getInstance();
        guiGraphicsExtractor.text(mc.font, "Mode: " + enumSetting.getValue().toString(), getX() + 3, getY() + 4,ENUM_TEXT_COLOUR);

    }

    @Override
    protected void drawBackground(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY) {

        int color = isMouseOver(mouseX, mouseY)
                ? SETTING_HOVER_BACKGROUND_COLOR
                : SETTING_BACKGROUND_COLOR;
        guiGraphicsExtractor.fill(
                getX() + 1,
                getY(),
                getX() + PanelComponent.PANEL_WIDTH - 1,
                getY() + getHeight() - 1,
                color
        );
    }


    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if(!isMouseOver(mouseX, mouseY)) {
            return false;
        }
        EnumSetting enumSetting = (EnumSetting) setting;
        if(buttonPressed == 0) {
            Enum nextValue = enumSetting.getNextValue();
            setting.setValue(nextValue);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int buttonPressed) {
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int buttonPressed, double dragX, double dragY) {
        return false;
    }

    @Override
    public int getHeight() {
        return ENUM_SETTING_HEIGHT;
    }
}
