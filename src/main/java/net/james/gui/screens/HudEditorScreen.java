package net.james.gui.screens;

import net.james.hud.AbstractHudElement;
import net.james.hud.HudManager;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

import java.util.List;

public class HudEditorScreen extends Screen {
    private List<AbstractHudElement> elementList;
    private Screen parent;


    @Override
    protected void init() {
        this.elementList = HudManager.getInstance().getElements();
    }

    @Override
    public void onClose() {
        this.minecraft.setScreenAndShow(this.parent);
    }


    @Override
    public void extractRenderState(GuiGraphicsExtractor guiGraphicsExtractor,int mouseX, int mouseY, float delta) {
        super.extractRenderState(guiGraphicsExtractor, mouseX, mouseY, delta);
        for(AbstractHudElement element : elementList) {
            element.renderEditor(guiGraphicsExtractor, mouseX, mouseY);
        }
    }


    public HudEditorScreen(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        for(AbstractHudElement element : elementList) {
            if(element.mouseReleased(event.x(), event.y(), event.button())) {
                return true;
            }
        }
        return super.mouseReleased(event);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        for(AbstractHudElement element : elementList) {
            if(element.mouseClicked(event.x(), event.y(), event.button())) {
                return true;
            }
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        for(AbstractHudElement element : elementList) {
            if(element.mouseClicked(event.x(), event.y(), event.button())) {
                return true;
            }
        }
        return super.mouseDragged(event, dx, dy);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        for(AbstractHudElement element : elementList) {
            if(element.isMouseOver(mouseX, mouseY)) {
                return true;
            }
        }
        return super.isMouseOver(mouseX, mouseY);
    }

}
