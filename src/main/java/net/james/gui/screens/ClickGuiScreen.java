package net.james.gui.screens;

import net.james.gui.ClickGuiManager;
import net.james.gui.components.clickgui.PanelComponent;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ClickGuiScreen extends Screen {

    private List<PanelComponent> panelComponents;


    public ClickGuiScreen(Component title) {
        super(title);;
    }

    @Override
    protected void init() {
        int currentX = 5;
        int currentY = 5;
        this.panelComponents = ClickGuiManager.getInstance().getPanels();
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY, float delta) {
        super.extractRenderState(guiGraphicsExtractor,mouseX,mouseY,delta);
        for(PanelComponent panelComponent : panelComponents) {
            panelComponent.render(guiGraphicsExtractor, mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        for(PanelComponent panelComponent : panelComponents) {
            if(panelComponent.mouseClicked(event.x(), event.y(), event.button())) {
                return true;
            }
        }
        return super.mouseClicked(event, doubleClick);
    }


    @Override
    public boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        for(PanelComponent panelComponent : panelComponents) {
            if(panelComponent.mouseDragged(event.x(), event.y(), event.button(), dx, dy)) {
                return true;
            }
        }
        return super.mouseDragged(event, dx, dy);
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        for(PanelComponent panelComponent : panelComponents) {
            if(panelComponent.mouseReleased(event.x(), event.y(), event.button())) {
                return true;
            }
        }
        return super.mouseReleased(event);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        for(PanelComponent panelComponent : panelComponents) {
            if(panelComponent.isMouseOver(mouseX, mouseY)) {
                return true;
            }
        }
        return super.isMouseOver(mouseX, mouseY);
    }
}
