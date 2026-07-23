package net.james.gui.components;

import net.james.module.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;

public class Header implements IGuiComponent{
    //TODO fix magic numbers here
    public static int WIDTH = 100;
    public static int HEIGHT = Minecraft.getInstance().font.lineHeight+5;
    public static int SPACING = 1;
    public static int COLOR = 0xF2423B3F;
    private final Panel panel;
    private final String categoryName;
    private boolean dragging = false;
    private int dragOffsetX = 0;
    private int dragOffsetY = 0;


    public Header(Panel panel, String categoryName) {
        this.panel = panel;
        this.categoryName = categoryName;
    }


    public void render(GuiGraphicsExtractor graphics) {
        Minecraft mc = Minecraft.getInstance();
        graphics.fill(panel.getX(), panel.getY(),panel.getX()+Header.WIDTH ,panel.getY() + Header.HEIGHT, Header.COLOR);
        graphics.text(mc.font, getCategoryName(), panel.getX()+2, panel.getY() + HEIGHT/3, 0xFFFFFFFF);
    }

    public Panel getPanel() {
        return panel;
    }

    public String getCategoryName() {
        return categoryName;
    }


    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if(!isMouseOver(mouseX, mouseY) || buttonPressed != 0) {
            return false;
        }
        dragging = true;
        dragOffsetX = (int) mouseX - panel.getX();
        dragOffsetY = (int) mouseY - panel.getY();
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
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= panel.getX()
                && mouseX <= panel.getX() + Header.WIDTH
                && mouseY >= panel.getY()
                && mouseY <= panel.getY() + Header.HEIGHT;
    }
}
