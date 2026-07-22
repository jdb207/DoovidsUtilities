package net.james.gui.components;

import net.james.module.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class Header {
    public static int WIDTH = 100;
    public static int HEIGHT = Minecraft.getInstance().font.lineHeight+5;
    public static int SPACING = 1;
    public static int COLOR = 0xF2423B3F;
    private final Panel panel;
    private final String categoryName;
    private int x;
    private int y;



    public Header(Panel panel, String categoryName, int x, int y) {
        this.panel = panel;
        this.categoryName = categoryName;
        this.x = x;
        this.y = y;
    }


    public void render(GuiGraphicsExtractor graphics) {
        Minecraft mc = Minecraft.getInstance();
        graphics.fill(getX(),getY() ,getX()+Header.WIDTH ,getY() + Header.HEIGHT, Header.COLOR);
        graphics.text(mc.font, getCategoryName(), getX()+2, getY() + HEIGHT/3, 0xFFFFFFFF);

    }

    public Panel getPanel() {
        return panel;
    }

    public int getY() {
        return y;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getX() {
        return x;
    }
}
