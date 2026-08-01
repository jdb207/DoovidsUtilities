package net.james.gui.components.clickgui;

import net.james.gui.components.AbstractGuiComponent;
import net.james.gui.components.clickgui.settings.AbstractSettingComponent;
import net.james.gui.components.clickgui.settings.BooleanSettingComponent;
import net.james.gui.components.clickgui.settings.EnumSettingComponent;
import net.james.gui.components.clickgui.settings.NumberSettingComponent;
import net.james.module.Module;
import net.james.setting.Setting;
import net.james.setting.settings.BooleanSetting;
import net.james.setting.settings.EnumSetting;
import net.james.setting.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleButtonComponent extends AbstractGuiComponent {
    private final Module module;
    private List<AbstractSettingComponent<?>> settingComponents = new ArrayList<>();


    private static final int BUTTON_HEIGHT = 20;
    public static int MODULE_NAME_COLOR = 0xFFFFFFFF;
    public static int MODULE_BUTTON_COLOR = 0xff91d2ff;
    public static int ENABLED_COLOR = 0x903B82F6;
    public static int MODULE_BUTTON_HOVER_COLOR = 0x903B82F6;


    private boolean expanded = false;


    public ModuleButtonComponent(Module module) {
        super(0,0, 100);
        this.module = module;
        for(Setting<?> setting : module.getSettings()) {
            settingComponents.add(createComponent(setting));
        }
    }

    @Override
    protected void drawBackground(GuiGraphicsExtractor graphicsExtractor, int mouseX, int mouseY) {
        int color;

        if(module.isEnabled()) {
            color = isHeaderHovered(mouseX, mouseY)
                    ? ARGB.multiplyAlpha(ENABLED_COLOR, 0.5f)
                    : ENABLED_COLOR;
        }
        else {
            color = isHeaderHovered(mouseX, mouseY)
                    ? MODULE_BUTTON_HOVER_COLOR
                    : ARGB.multiplyAlpha(MODULE_BUTTON_COLOR,0.4f);
        }

        graphicsExtractor.fill(
                getX() + 1,
                getY(),
                getX() + PanelComponent.PANEL_WIDTH,
                getY() + BUTTON_HEIGHT,
                color
        );
    }



    @Override
    protected void drawBorder(GuiGraphicsExtractor graphics) {
        int height = getHeight();
        graphics.fill(x, y, x + width, y + 1, MODULE_BUTTON_COLOR);
        graphics.fill(x, y + height - 1, x + width, y + height, MODULE_BUTTON_COLOR);
        graphics.fill(x, y, x + 1, y + height, MODULE_BUTTON_COLOR);
        graphics.fill(x + width, y, x + width + 1, y + height, MODULE_BUTTON_COLOR);
    }

    @Override
    protected void drawContents(GuiGraphicsExtractor graphicsExtractor, int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getInstance();

        graphicsExtractor.text(
                mc.font,
                module.getName(),
                getX() + 2,
                getY() + BUTTON_HEIGHT / 3,
                MODULE_NAME_COLOR
        );

        if(expanded) {
            renderSettings(graphicsExtractor, mouseX, mouseY);
        }

    }

    public void renderSettings(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY) {
        int currentY = getY();
        for(AbstractSettingComponent settingComponent : settingComponents) {
            settingComponent.setPosition(getX(), currentY + settingComponent.getHeight());
            settingComponent.render(guiGraphicsExtractor, mouseX, mouseY);
            currentY += settingComponent.getHeight();
        }
    }


    public boolean isHeaderHovered(double mouseX, double mouseY) {
        return x <= mouseX && mouseX <= x + width && mouseY >= y && mouseY <= y + BUTTON_HEIGHT;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        if(isHeaderHovered(mouseX, mouseY)) {
            return true;
        }
        for(AbstractSettingComponent settingComponent : settingComponents) {
            if(settingComponent.isMouseOver(mouseX, mouseY)) {
                return true;
            }
        }

        return false;
    }



    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if(expanded) {
            for(AbstractSettingComponent settingComponent : settingComponents) {
                if(settingComponent.mouseClicked(mouseX, mouseY, buttonPressed)) {
                    return true;
                }
            }
        }

        if(isHeaderHovered(mouseX, mouseY)) {
            if(buttonPressed == 0) {
                module.toggle();
            }
            else if(buttonPressed == 1) {
                expanded = !expanded;
            }
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

    public int getY() {
        return y;
    }

    @Override
    public int getHeight() {
        if (!expanded)
            return BUTTON_HEIGHT;

        int height = BUTTON_HEIGHT;

        for (AbstractSettingComponent setting : settingComponents)
            height += setting.getHeight();

        return height;
    }

    private AbstractSettingComponent<?> createComponent(Setting<?> setting) {
        if(setting instanceof BooleanSetting booleanSetting) {
            return new BooleanSettingComponent(booleanSetting);
        }
        if(setting instanceof NumberSetting numberSetting) {
            return new NumberSettingComponent(numberSetting);
        }
        if(setting instanceof EnumSetting enumSetting) {
            return new EnumSettingComponent(enumSetting);
        }
        return null;
    }


}
