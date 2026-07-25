package net.james.hud.elements;
import net.james.hud.AbstractHudElement;
import net.james.hud.IHudElement;
import net.james.util.FrameUtil;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;

public class FPSHud extends AbstractHudElement {


    @Override
    public void render(GuiGraphicsExtractor guiGraphicsExtractor) {
        Minecraft mc = Minecraft.getInstance();
        int fps = FrameUtil.CalculateFPS();
        guiGraphicsExtractor.text(mc.font, "FPS: " + String.valueOf(fps), 5, 5, ARGB.color(255,255,255,255), false);
    }
}
