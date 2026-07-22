package net.james.gui.components;

import net.james.module.Category;
import net.james.module.Module;
import net.james.module.ModuleManager;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.ArrayList;
import java.util.List;

public class Panel {

    private List<ModuleButton> buttons = new ArrayList<>();
    private final Category category;


    private int x;
    private int y;

    public static final int PANEL_WIDTH = 100;
    public static int PANEL_HEIGHT = 400;
    public static int PANEL_BACK_COLOR = 0xFFDBC0F7;
    public static int PANEL_BORDER_COLOR = 0xFF0000FF;
    public static final int HEADER_HEIGHT = 18;
    public static int PANEL_SPACING = 5;

    public Panel(Category category, int x, int y) {
        this.category = category;
        this.x = x;
        this.y = y;

        for(Module module : ModuleManager.getInstance().getModules(category)) {
            buttons.add(new ModuleButton(module));
        }
    }


    public void render(GuiGraphicsExtractor graphics) {
        //TODO make height/width based on module names / number of modules
        //Draw panel background
        graphics.fill(getX(), getY(), getX() + PANEL_WIDTH, getY() + PANEL_HEIGHT, PANEL_BACK_COLOR);
        //Draw panel border
        graphics.outline(getX(), getY(), PANEL_WIDTH, PANEL_HEIGHT,PANEL_BORDER_COLOR );
        //Draw panel header
        Header header = new Header(this, getCategoryName(), getX(), getY());
        header.render(graphics);
        for(ModuleButton button : buttons) {
            button.render(graphics);
        }
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




}
