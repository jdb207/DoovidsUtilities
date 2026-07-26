package net.james.hud;

import net.minecraft.client.gui.GuiGraphicsExtractor;

public class HudContext {

    private int screenWidth;
    private int screenHeight;



    public void update(GuiGraphicsExtractor graphics) {
        this.screenWidth = graphics.guiWidth();
        this.screenHeight = graphics.guiHeight();
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
