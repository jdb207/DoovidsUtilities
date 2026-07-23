package net.james.gui.components;

import net.james.module.Category;
import net.james.module.Module;
import net.james.module.ModuleManager;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.ArrayList;
import java.util.List;

public class Panel implements IGuiComponent {

    private final List<ModuleButton> buttons = new ArrayList<>();
    private final Category category;
    private final Header header;


    private int x;
    private int y;

    public static final int PANEL_WIDTH = 100;
    public static int PANEL_BACK_COLOR = 0xFFDBC0F7;
    public static int PANEL_BORDER_COLOR = 0xFF0000FF;
    public static int PANEL_SPACING = 5;

    public Panel(Category category, int x, int y) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.header = new Header(this, getCategoryName());
        for(Module module : ModuleManager.getInstance().getModules(category)) {
            buttons.add(new ModuleButton(module));
        }
    }

    @Override
    public void render(GuiGraphicsExtractor graphics) {
        //TODO make height/width based on module names / number of modules
        //Draw panel background
        drawBackground(graphics);
        //Draw panel border
        drawBorder(graphics);
        //Draw panel header
        header.render(graphics);
        //Draw Module Buttons
        drawButtons(graphics);
    }

    public void drawBackground(GuiGraphicsExtractor graphics) {
        graphics.fill(getX(), getY(), getX() + PANEL_WIDTH, getY() + getHeight(), PANEL_BACK_COLOR);
    }

    public void drawBorder(GuiGraphicsExtractor graphics) {
        graphics.outline(getX(), getY(), PANEL_WIDTH, getHeight(),PANEL_BORDER_COLOR );
    }

    public void drawButtons(GuiGraphicsExtractor graphics) {
        int buttonY = this.getY() + Header.HEIGHT;
        for(ModuleButton button : buttons) {
            button.setX(getX());
            button.setY(buttonY);
            button.render(graphics);
            buttonY += ModuleButton.BUTTON_HEIGHT;
        }
    }

    public void layout() {

    }

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

    public int getHeight(){
        return Header.HEIGHT + buttons.size() * ModuleButton.BUTTON_HEIGHT;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if(header.mouseClicked(mouseX, mouseY,buttonPressed)) {
            return true;
        }
        for(ModuleButton button : buttons) {
            if(button.mouseClicked(mouseX, mouseY, buttonPressed)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int buttonPressed) {
        if(header.mouseReleased(mouseX, mouseY,buttonPressed)) {
            return true;
        }
        for(ModuleButton button : buttons) {
            if(button.mouseReleased(mouseX, mouseY, buttonPressed)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int buttonPressed, double dragX, double dragY) {
        if(header.mouseDragged(mouseX, mouseY, buttonPressed, dragX, dragY)) {
            return true;
        }
        for(ModuleButton button : buttons) {
            if(button.mouseDragged(mouseX, mouseY, buttonPressed, dragX, dragY)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        if(header.isMouseOver(mouseX, mouseY)) {
            return true;
        }
        for(ModuleButton button : buttons) {
            if(button.isMouseOver(mouseX, mouseY)) {
                return true;
            }
        }
        return false;
    }
}
