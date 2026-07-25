package net.james.gui.components;

import net.james.gui.ClickGuiManager;
import net.james.gui.screens.HudEditorScreen;
import net.james.module.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.awt.*;

public class HudEditorButton extends AbstractGuiComponent {

    private static int HEIGHT = 20;


    public HudEditorButton(Panel hudPanel) {
        int x = hudPanel.getX();
        int y = hudPanel.getY() + hudPanel.getHeight() + Panel.PANEL_SPACING;
        super(x,y,100);
    }

    @Override
    public void render(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getInstance();
        graphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), 0xff585858);
        graphics.text(mc.font, "Hud Editor", getX() + 2, getY() + getHeight()/3 - 1, 0xFFFFFFFF);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if(!isMouseOver(mouseX, mouseY) || buttonPressed != 0) {
            return false;
        }
        Minecraft.getInstance().gui.setScreen(new HudEditorScreen(Component.empty(), Minecraft.getInstance().gui.screen()));
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
    public int getHeight() {
        return HEIGHT;
    }
}
