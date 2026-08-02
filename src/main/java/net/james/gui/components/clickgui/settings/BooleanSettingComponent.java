package net.james.gui.components.clickgui.settings;

import net.james.gui.components.clickgui.ModuleButtonComponent;
import net.james.gui.components.clickgui.PanelComponent;
import net.james.setting.settings.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;

public class BooleanSettingComponent extends AbstractSettingComponent<BooleanSetting> {
    private final static int BOOLEAN_SETTING_HEIGHT = 20;
    private final static int BOOLEAN_SETTING_COLOR = 0xff646464;
    private final static int BOOLEAN_TEXT_COLOR = 0xFFFFFFFF;
    public static final int SETTING_BACKGROUND_COLOR       = 0xFF3A4F66;
    public static final int SETTING_HOVER_BACKGROUND_COLOR = 0xFF39414D;
    public static final int BORDER_COLOR = 0x903B82F6;


    public BooleanSettingComponent(BooleanSetting setting) {
        super(setting);
    }

    @Override
    protected void drawBackground(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY) {
        int color;
        if (setting.isEnabled()) {
            color = isMouseOver(mouseX, mouseY)
                    ? ARGB.multiplyAlpha(ENABLED_COLOR, 0.2f)
                    : ARGB.multiplyAlpha(ENABLED_COLOR, 0.7f);
        } else {
            color = isMouseOver(mouseX, mouseY)
                    ? SETTING_HOVER_BACKGROUND_COLOR
                    : SETTING_BACKGROUND_COLOR;
        }

        guiGraphicsExtractor.fill(
                getX() + 1,
                getY(),
                getX() + PanelComponent.PANEL_WIDTH,
                getY() + getHeight() - 1,
                color
        );
    }

    @Override
    protected void drawBorder(GuiGraphicsExtractor guiGraphicsExtractor) {
        guiGraphicsExtractor.horizontalLine(getX() + 1, getX() + getWidth() - 1,getY() + getHeight() - 1, ModuleButtonComponent.MODULE_BUTTON_COLOR);
    }

    @Override
    protected void drawContents(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getInstance();
        guiGraphicsExtractor.text(mc.font, setting.getName(), getX() + 3, getY() + 4,BOOLEAN_TEXT_COLOR);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if(!isMouseOver(mouseX, mouseY)) {
            return false;
        }
        if(buttonPressed == 0) {
            ((BooleanSetting) setting).toggle();
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
        return BOOLEAN_SETTING_HEIGHT;
    }


}
