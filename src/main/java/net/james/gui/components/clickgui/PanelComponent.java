package net.james.gui.components.clickgui;

import net.james.gui.components.AbstractGuiComponent;
import net.james.gui.components.hud.HudEditorButton;
import net.james.module.Category;
import net.james.module.Module;
import net.james.module.ModuleManager;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.ArrayList;
import java.util.List;

public class PanelComponent extends AbstractGuiComponent {

    private final List<AbstractGuiComponent> children = new ArrayList<>();
    private final Category category;
    private final HeaderComponent headerComponent;


    public static final int PANEL_WIDTH = 100;
    public static int PANEL_BACK_COLOR = 0xff585858;
    public static int PANEL_BORDER_COLOR = 0xFF0000FF;
    public static int PANEL_SPACING = 5;

    public PanelComponent(int x, int y, Category category) {
        super(x, y, 100);
        this.category = category;
        this.headerComponent = new HeaderComponent(this, getCategoryName());

        for(Module module : ModuleManager.getInstance().getModules(category)) {
            children.add(new ModuleButtonComponent(module));
        }
        if(this.getCategory() == Category.HUD) {
            children.add(new HudEditorButton(this));
        }
        layout();
    }

    @Override
    public void render(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        //TODO make height/width based on module names / number of modules
        //Draw panel background
        drawBackground(graphics);
        //Draw panel border

        //drawBorder(graphics);

        //Draw panel header
        headerComponent.render(graphics, mouseX, mouseY);
        //Draw Module Buttons
        renderChildren(graphics, mouseX, mouseY);
    }

    public void drawBackground(GuiGraphicsExtractor graphics) {
        graphics.fill(getX(), getY(), getX() + PANEL_WIDTH, getY() + getHeight(), PANEL_BACK_COLOR);
    }

    public void drawBorder(GuiGraphicsExtractor graphics) {
        graphics.outline(getX(), getY(), PANEL_WIDTH, getHeight(),PANEL_BORDER_COLOR );
    }

    public void renderChildren(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        for(AbstractGuiComponent child : children) {
            child.render(graphics, mouseX, mouseY);
        }
    }

    public void layout() {
        headerComponent.setPosition(x, y);
        int currentY = y + headerComponent.getHeight();
        for(AbstractGuiComponent child : children) {
            child.setPosition(x, currentY);
            currentY += child.getHeight();
        }

    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

        layout();
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    public String getCategoryName() {
        return category.toString();
    }

    public Category getCategory() {
        return category;
    }

    public int getHeight(){
        return headerComponent.getHeight() + children.stream().mapToInt(AbstractGuiComponent::getHeight).sum();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if(headerComponent.mouseClicked(mouseX, mouseY,buttonPressed)) {
            return true;
        }
        for(AbstractGuiComponent child : children) {
            if(child.mouseClicked(mouseX, mouseY, buttonPressed)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int buttonPressed) {
        if(headerComponent.mouseReleased(mouseX, mouseY,buttonPressed)) {
            return true;
        }
        for(AbstractGuiComponent child : children) {
            if(child.mouseReleased(mouseX, mouseY, buttonPressed)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int buttonPressed, double dragX, double dragY) {
        if(headerComponent.mouseDragged(mouseX, mouseY, buttonPressed, dragX, dragY)) {
            return true;
        }
        for(AbstractGuiComponent child : children) {
            if(child.mouseDragged(mouseX, mouseY, buttonPressed, dragX, dragY)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        if(headerComponent.isMouseOver(mouseX, mouseY)) {
            return true;
        }
        for(AbstractGuiComponent child : children) {
            if(child.isMouseOver(mouseX, mouseY)) {
                return true;
            }
        }
        return false;
    }
}
