package net.james.gui.components.clickgui;

import net.james.gui.components.AbstractGuiComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class HeaderComponent extends AbstractGuiComponent {
    public static int SPACING = 1;
    public static int COLOR = 0xff262221;
    private static int HEADER_HEIGHT = 15;
    private final PanelComponent panelComponent;
    private final String categoryName;
    private boolean dragging = false;
    private int dragOffsetX = 0;
    private int dragOffsetY = 0;


    public HeaderComponent(PanelComponent panelComponent, String categoryName) {
        super(panelComponent.getX(), panelComponent.getY(),100);
        this.panelComponent = panelComponent;
        this.categoryName = categoryName;
    }



    @Override
    protected void drawContents(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getInstance();
        guiGraphicsExtractor.text(mc.font, getCategoryName(), getX()+2, getY() + HEADER_HEIGHT/3 - 1, 0xFFFFFFFF);
    }

    @Override
    protected void drawBorder(GuiGraphicsExtractor guiGraphicsExtractor) {
        guiGraphicsExtractor.horizontalLine(getX(), getX() + getWidth(), getY(),ModuleButtonComponent.MODULE_BUTTON_COLOR);
        guiGraphicsExtractor.horizontalLine(getX(), getX() + getWidth(), getY() + getHeight(),ModuleButtonComponent.MODULE_BUTTON_COLOR);
        guiGraphicsExtractor.verticalLine(getX(), getY(), getY() + getHeight() ,ModuleButtonComponent.MODULE_BUTTON_COLOR);
        guiGraphicsExtractor.verticalLine(getX() + getWidth(), getY(), getY() + getHeight() ,ModuleButtonComponent.MODULE_BUTTON_COLOR);
    }

    public PanelComponent getPanel() {
        return panelComponent;
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
        panelComponent.setPosition((int) mouseX - dragOffsetX, (int) mouseY - dragOffsetY);
        return true;
    }

    @Override
    public int getHeight() {
        return HEADER_HEIGHT;
    }
}
