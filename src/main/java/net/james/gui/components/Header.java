package net.james.gui.components;

import net.james.module.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;

public class Header extends AbstractGuiComponent {
    public static int SPACING = 1;
    public static int COLOR = 0xff262221;
    private static int HEADER_HEIGHT = 20;
    private final Panel panel;
    private final String categoryName;
    private boolean dragging = false;
    private int dragOffsetX = 0;
    private int dragOffsetY = 0;


    public Header(Panel panel, String categoryName) {
        super(panel.getX(),panel.getY(),100);
        this.panel = panel;
        this.categoryName = categoryName;
    }


    public void render(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getInstance();
        graphics.fill(getX(), getY(),getX()+getWidth() ,getY() + getHeight(), Header.COLOR);
        graphics.text(mc.font, getCategoryName(), getX()+2, getY() + HEADER_HEIGHT/3 - 1, 0xFFFFFFFF);
    }

    public Panel getPanel() {
        return panel;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if(!isMouseOver(mouseX, mouseY) || buttonPressed != 0) {
            return false;
        }
        dragging = true;
        dragOffsetX = (int) mouseX - getX();
        dragOffsetY = (int) mouseY - getY();
        return true;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int buttonPressed) {
        if(!dragging) return false;
        dragging = false;
        return true;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int buttonPressed, double dragX, double dragY) {
        if(!dragging) return false;
        panel.setPosition((int) mouseX - dragOffsetX, (int) mouseY - dragOffsetY);
        return true;
    }

    @Override
    public int getHeight() {
        return HEADER_HEIGHT;
    }
}
