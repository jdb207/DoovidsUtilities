package net.james.hud;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;

public abstract class AbstractHudElement implements IHudElement {
    protected int x;
    protected int y;

    protected int width;
    protected int height;

    private boolean dragging = false;
    private int dragOffsetX = 0;
    private int dragOffsetY = 0;
    protected boolean editing = false;

    private static final int BORDER_COLOR = 0xff434343;

    public abstract void render(GuiGraphicsExtractor guiGraphicsExtractor);

    public void renderEditor(GuiGraphicsExtractor graphicsExtractor, int mouseX, int mouseY) {
        render(graphicsExtractor);
        drawEditorBox(graphicsExtractor);

        if(isMouseOver(mouseX, mouseY)) {
            drawHoverOutline(graphicsExtractor);
        }
    }

    protected void drawEditorBox(GuiGraphicsExtractor guiGraphicsExtractor) {
        guiGraphicsExtractor.outline(getX(), getY(), getWidth(), getHeight(), BORDER_COLOR);
    }

    protected void drawHoverOutline(GuiGraphicsExtractor guiGraphicsExtractor) {
        guiGraphicsExtractor.outline(getX(), getY(), getWidth(), getHeight(), ARGB.multiplyAlpha(BORDER_COLOR, 0.5f));
    }

    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if(!isMouseOver(mouseX, mouseY) || buttonPressed != 0) {
            return false;
        }
        dragging = true;
        dragOffsetX = (int) mouseX - this.getX();
        dragOffsetY = (int) mouseY - this.getY();
        return true;
    }

    public boolean mouseReleased(double mouseX, double mouseY, int buttonPressed) {
        if(!dragging) return false;
        dragging = false;
        return true;
    }

    public boolean isMouseOver(double mouseX, double mouseY) {
        return (mouseX >= getX() &&
                mouseX <= getX() + getWidth() &&
                mouseY >= getY() &&
                mouseY <= getY() + getHeight());
    }

    public boolean mouseDragged(double mouseX, double mouseY, int buttonPressed, double dragX, double dragY) {
       if(!dragging) return false;
       setPosition((int) mouseX - dragOffsetX, (int) mouseY - dragOffsetY);
       return true;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
