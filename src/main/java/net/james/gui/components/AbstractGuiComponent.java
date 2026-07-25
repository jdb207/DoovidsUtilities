package net.james.gui.components;

import net.minecraft.client.gui.GuiGraphicsExtractor;

public class AbstractGuiComponent implements IGuiComponent{


    @Override
    public void render(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int buttonPressed) {
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int buttonPressed, double dragX, double dragY) {
        return false;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return false;
    }
}
