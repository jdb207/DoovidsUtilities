package net.james.gui.components.clickgui.settings;

import net.james.gui.components.AbstractGuiComponent;
import net.james.setting.Setting;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public abstract class AbstractSettingComponent<T extends Setting<?>> extends AbstractGuiComponent {

    protected T setting;
    public static final int BORDER_COLOR        = 0xFF3B82F6;
    public static final int HOVER_BORDER_COLOR  = 0xAAFFFFFF;

    public static final int TEXT_COLOR          = 0xFFFFFFFF;
    public static final int BACKGROUND_COLOR    = 0xFF3A4F66;
    public static final int HOVER_BACKGROUND_COLOR = 0x22000000;

    public static final int ENABLED_COLOR       = 0xFFB5ECFF;

    protected AbstractSettingComponent(T setting) {
       super(0,0,100);
       this.setting = setting;
    }

    public T getSetting() {
        return setting;
    }

    protected void drawBorder(GuiGraphicsExtractor graphicsExtractor) {

    }

    protected abstract void drawContents(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY);

    protected void drawBackground(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY) {
        int color = isMouseOver(mouseX, mouseY) ? HOVER_BACKGROUND_COLOR : BACKGROUND_COLOR;
        guiGraphicsExtractor.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), color);
    }




}
