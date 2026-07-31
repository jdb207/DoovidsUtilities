package net.james.gui.components;

import net.minecraft.client.gui.GuiGraphicsExtractor;

public abstract class AbstractGuiComponent implements IGuiComponent{

    protected int x;
    protected int y;
    protected int width;


    protected AbstractGuiComponent(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;

    }


    public abstract void render(GuiGraphicsExtractor graphics, int mouseX, int mouseY);


    public abstract boolean mouseClicked(double mouseX, double mouseY, int buttonPressed);


    public abstract boolean mouseReleased(double mouseX, double mouseY, int buttonPressed);


    public abstract boolean mouseDragged(double mouseX, double mouseY, int buttonPressed, double dragX, double dragY);

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= x &&
                mouseX <= x + getWidth()-2 &&
                mouseY >= y &&
                mouseY <= y + getHeight()-2;
    }

    public int getWidth() {
        return width;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract int getHeight();

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

}
