package net.james.gui.components;

import net.james.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;

public class ModuleButton extends AbstractGuiComponent {
    private final Module module;


    private static final int BUTTON_HEIGHT = 20;
    public static int MODULE_NAME_COLOR = 0xFFFFFFFF;
    public static int MODULE_BUTTON_COLOR = 0xff646464;
    public static int ENABLED_COLOR = 0xff434343;


    private int offsetX;
    private int offsetY;

    public ModuleButton(Module module) {
        super(0,0, 100);
        this.module = module;
    }

    @Override
    public void render(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getInstance();
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
        graphics.fill(getX()+1, getY(), getX() + Panel.PANEL_WIDTH - 1, getY() + BUTTON_HEIGHT - 1, color);
        graphics.text(mc.font, module.getName(), getX() + 2, getY() + BUTTON_HEIGHT/3, MODULE_NAME_COLOR);
    }



    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if(!isMouseOver(mouseX, mouseY) || buttonPressed != 0) {
            return false;
        }
        module.toggle();
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

    public int getY() {
        return y;
    }

    @Override
    public int getHeight() {
        return BUTTON_HEIGHT;
    }
}
