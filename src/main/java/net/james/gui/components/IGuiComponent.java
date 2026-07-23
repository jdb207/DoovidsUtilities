package net.james.gui.components;

import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface IGuiComponent {


    void render(GuiGraphicsExtractor graphics);


    boolean mouseClicked(double mouseX, double mouseY, int buttonPressed);
    boolean mouseReleased(double mouseX, double mouseY, int buttonPressed);
    boolean mouseDragged(double mouseX, double mouseY, int buttonPressed, double dragX, double dragY);
    boolean isMouseOver(double mouseX, double mouseY);


}
