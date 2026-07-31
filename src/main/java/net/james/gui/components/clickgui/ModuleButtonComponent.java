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
    public static int MODULE_BUTTON_COLOR = 0xff646464;
    public static int ENABLED_COLOR = 0xff434343;


    private boolean expanded = false;


    public ModuleButtonComponent(Module module) {
        super(0,0, 100);
        this.module = module;
        for(Setting<?> setting : module.getSettings()) {
            settingComponents.add(createComponent(setting));
        }
    }

    @Override
    public void render(GuiGraphicsExtractor graphicsExtractor, int mouseX, int mouseY) {
        int color;
        if(module.isEnabled()) {
            if(isMouseOver(mouseX, mouseY)) {
                color = ARGB.multiplyAlpha(ENABLED_COLOR, 0.5f);
            }
            else {
                color = ENABLED_COLOR;
            }
        }
        else {
             color = isMouseOver(mouseX, mouseY) ? ENABLED_COLOR : MODULE_BUTTON_COLOR;
        }

        renderButton(graphicsExtractor, color);

        if(expanded) {
            renderSettings(graphicsExtractor, mouseX, mouseY);
        }
    }

    public void renderButton(GuiGraphicsExtractor graphicsExtractor, int color) {
        Minecraft mc = Minecraft.getInstance();
        graphicsExtractor.horizontalLine(getX()+1, getX() + PanelComponent.PANEL_WIDTH - 1, getY(), PanelComponent.PANEL_BACK_COLOR);
        graphicsExtractor.fill(getX()+1, getY()+1, getX() + PanelComponent.PANEL_WIDTH - 1, getY() + BUTTON_HEIGHT - 1, color);
        graphicsExtractor.text(mc.font, module.getName(), getX() + 2, getY() + BUTTON_HEIGHT/3, MODULE_NAME_COLOR);
    }

    public void renderSettings(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY) {
        int currentY = getY();
        for(AbstractSettingComponent settingComponent : settingComponents) {
            settingComponent.setPosition(getX(), currentY + settingComponent.getHeight());
            settingComponent.render(guiGraphicsExtractor, mouseX, mouseY);
            currentY += settingComponent.getHeight();
        }
    }


    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        if(expanded) {
            return mouseX >= x &&
                    mouseX <= x + width &&
                    mouseY >= y &&
                    mouseY <= y + ModuleButtonComponent.BUTTON_HEIGHT;
        }
        return mouseX >= x &&
                mouseX <= x + width &&
                mouseY >= y &&
                mouseY <= y + getHeight();

    }



    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if(!isMouseOver(mouseX, mouseY)) {
            return false;
        }

        if(buttonPressed == 0) {
            module.toggle();
            return true;
        }
        if(buttonPressed == 1) {
            expanded = !expanded;
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
        int height = BUTTON_HEIGHT;
        if(expanded) {
            for(AbstractSettingComponent settingComponent : settingComponents) {
                height += settingComponent.getHeight();
            }
        }
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
