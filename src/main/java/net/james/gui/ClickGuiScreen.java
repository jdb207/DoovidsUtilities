package net.james.gui;

import net.james.gui.components.Panel;
import net.james.module.Category;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class ClickGuiScreen extends Screen {

    private List<Panel> panels;


    public ClickGuiScreen(Component title) {
        super(title);;
    }

    @Override
    protected void init() {
        int currentX = 5;
        int currentY = 5;
        this.panels = ClickGuiManager.getInstance().getPanels();
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics,mouseX,mouseY,delta);
        for(Panel panel : panels) {
            panel.render(graphics, mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        for(Panel panel : panels) {
            if(panel.mouseClicked(event.x(), event.y(), event.button())) {
                return true;
            }
        }
        return super.mouseClicked(event, doubleClick);
    }


    @Override
    public boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        for(Panel panel : panels) {
            if(panel.mouseDragged(event.x(), event.y(), event.button(), dx, dy)) {
                return true;
            }
        }
        return super.mouseDragged(event, dx, dy);
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        for(Panel panel : panels) {
            if(panel.mouseReleased(event.x(), event.y(), event.button())) {
                return true;
            }
        }
        return super.mouseReleased(event);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        for(Panel panel : panels) {
            if(panel.isMouseOver(mouseX, mouseY)) {
                return true;
            }
        }
        return super.isMouseOver(mouseX, mouseY);
    }
}
