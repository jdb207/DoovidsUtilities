package net.james.gui.components.clickgui.settings;

import net.james.setting.Setting;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class NumberSettingComponent extends AbstractSettingComponent {
    private static final int NUMBER_SETTING_HEIGHT = 25;

    public NumberSettingComponent(Setting setting) {
        super(setting);
    }


    @Override
    public void render(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
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
        return NUMBER_SETTING_HEIGHT;
    }
}
