package net.james.gui;

import net.james.gui.components.clickgui.PanelComponent;
import net.james.module.Category;

import java.util.ArrayList;
import java.util.List;

public class ClickGuiManager {
    private static ClickGuiManager INSTANCE = new ClickGuiManager();
    private final List<PanelComponent> panelComponents = new ArrayList<>();


    private ClickGuiManager() {
    }

    public void init() {
        int runningX = 10;
        for(Category category : Category.values()) {
            panelComponents.add(new PanelComponent(runningX, 10, category));
            runningX += PanelComponent.PANEL_WIDTH + 3;
        }
    }

    public static ClickGuiManager getInstance() {
        return INSTANCE;
    }

    public List<PanelComponent> getPanels() {
        return this.panelComponents;
    }

    public PanelComponent getPanel(Category category) {
        for(PanelComponent panelComponent : panelComponents) {
            if(panelComponent.getCategory() == Category.HUD) {
                return panelComponent;
            }
        }
        return null;
    }
}
