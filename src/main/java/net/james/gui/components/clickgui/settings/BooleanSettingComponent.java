package net.james.gui.components.clickgui.settings;

import net.james.gui.components.clickgui.ModuleButtonComponent;
import net.james.setting.settings.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class BooleanSettingComponent extends AbstractSettingComponent {
    private final static int BOOLEAN_SETTING_HEIGHT = 20;
    private final static int BOOLEAN_SETTING_COLOR = 0xff646464;
    private final static int BOOLEAN_TEXT_COLOR = 0xFFFFFFFF;


    public BooleanSettingComponent(BooleanSetting setting) {
        super(setting);
    }

    @Override
    public void render(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY) {
        drawBooleanBox(guiGraphicsExtractor);
    }


    public void drawBooleanBox(GuiGraphicsExtractor guiGraphicsExtractor) {
        Minecraft mc = Minecraft.getInstance();
        guiGraphicsExtractor.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), BOOLEAN_SETTING_COLOR);
        guiGraphicsExtractor.text(mc.font, setting.getName(), getX() + 2, getY() + getHeight()/3, BOOLEAN_TEXT_COLOR);
    }




    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if(!isMouseOver(mouseX, mouseY) || buttonPressed != 0) {
            return false;
        }
        ((BooleanSetting) setting).toggle();
        return true;
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
        return BOOLEAN_SETTING_HEIGHT;
    }


}
