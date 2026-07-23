package net.james.gui;

import net.james.gui.components.Panel;
import net.james.module.Category;

import java.util.ArrayList;
import java.util.List;

public class ClickGuiManager {
    private static ClickGuiManager INSTANCE = new ClickGuiManager();
    private final List<Panel> panels = new ArrayList<>();


    private ClickGuiManager() {
        int runningX = 10;
        for(Category category : Category.values()) {
            panels.add(new Panel(category, runningX, 10));
            runningX += Panel.PANEL_WIDTH + 1;
        }
    }

    public static ClickGuiManager getInstance() {
        return INSTANCE;
    }

    public List<Panel> getPanels() {
        return this.panels;
    }
}
