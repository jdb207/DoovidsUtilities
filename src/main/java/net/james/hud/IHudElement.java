package net.james.hud;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface IHudElement {

     void render(GuiGraphicsExtractor guiGraphicsExtractor, DeltaTracker deltaTracker);
}
