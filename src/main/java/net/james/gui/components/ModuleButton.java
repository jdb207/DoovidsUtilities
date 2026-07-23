package net.james.gui.components;

import net.james.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class ModuleButton implements IGuiComponent{
    private final Module module;
    public static int BUTTON_HEIGHT = 20;
    public static int MODULE_NAME_COLOR = 0xFFFFFFFF;
    public static int MODULE_BUTTON_COLOR = 0xFFCCFFFF;
    private int x;
    private int y;

    public ModuleButton(Module module) {
        this.module = module;
    }

    @Override
    public void render(GuiGraphicsExtractor graphics) {
        Minecraft mc = Minecraft.getInstance();
        graphics.fill(getX()+1, y, getX() + Panel.PANEL_WIDTH - 1, getY() + BUTTON_HEIGHT - 1, MODULE_BUTTON_COLOR);
        graphics.text(mc.font, module.getName(), getX() + 2, y + BUTTON_HEIGHT/3, MODULE_NAME_COLOR);
    }

    public void layoutButtons() {

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

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= getX()+1 &&
                mouseX <= getX() + Panel.PANEL_WIDTH -1 &&
                mouseY >= getY() &&
                mouseY <= getY() + BUTTON_HEIGHT-1;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
