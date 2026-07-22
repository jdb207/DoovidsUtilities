package net.james.gui;

import net.james.gui.components.Panel;
import net.james.module.Category;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.animal.feline.Cat;

import java.util.ArrayList;
import java.util.List;

public class ClickGuiScreen extends Screen {

    private final List<Panel> panels = new ArrayList<>();


    public ClickGuiScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int currentX = 5;
        int currentY = 5;
        for(Category category : Category.values()) {
            panels.add(new Panel(category,currentX, currentY));
            currentX += Panel.PANEL_WIDTH + Panel.PANEL_SPACING;
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics,mouseX,mouseY,delta);
        for(Panel panel : panels) {
            panel.render(graphics);
        }

    }
}
