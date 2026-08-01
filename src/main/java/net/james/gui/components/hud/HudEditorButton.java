package net.james.gui.components.hud;

import net.james.gui.components.AbstractGuiComponent;
import net.james.gui.components.clickgui.ModuleButtonComponent;
import net.james.gui.components.clickgui.PanelComponent;
import net.james.gui.screens.HudEditorScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;

public class HudEditorButton extends AbstractGuiComponent {

    private static int HEIGHT = 20;


    public HudEditorButton(PanelComponent hudPanelComponent) {
        int x = hudPanelComponent.getX();
        int y = hudPanelComponent.getY() + hudPanelComponent.getHeight() + PanelComponent.PANEL_SPACING;
        super(x,y,100);
    }



    @Override
    protected void drawBackground(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY) {
        int color = isMouseOver(mouseX, mouseY) ? ARGB.multiplyAlpha(ModuleButtonComponent.ENABLED_COLOR, 0.5f)
                : ModuleButtonComponent.ENABLED_COLOR;
        guiGraphicsExtractor.fill(
                getX() + 1,
                getY(),
                getX() + PanelComponent.PANEL_WIDTH,
                getY() + getHeight(),
                color
        );
    }

    @Override
    protected void drawBorder(GuiGraphicsExtractor graphics) {
        int height = getHeight();
        graphics.fill(x, y, x + width, y + 1, ModuleButtonComponent.MODULE_BUTTON_COLOR);
        graphics.fill(x, y + height - 1, x + width, y + height, ModuleButtonComponent.MODULE_BUTTON_COLOR);
        graphics.fill(x, y, x + 1, y + height, ModuleButtonComponent.MODULE_BUTTON_COLOR);
        graphics.fill(x + width, y, x + width + 1, y + height, ModuleButtonComponent.MODULE_BUTTON_COLOR);
    }

    @Override
    protected void drawContents(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getInstance();
        guiGraphicsExtractor.text(mc.font, "Hud Editor", getX() + 2, getY() + getHeight()/3 - 1, 0xFFFFFFFF);
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
