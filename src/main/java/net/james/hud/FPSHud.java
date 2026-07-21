package net.james.hud;
import net.james.util.FrameUtil;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.util.Util;

import java.util.ArrayDeque;
import java.util.Deque;

public class FPSHud implements IHudElement {


    @Override
    public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        Minecraft mc = Minecraft.getInstance();
        int fps = FrameUtil.CalculateFPS();
        graphics.text(mc.font, "FPS: " + String.valueOf(fps), 5, 5, ARGB.color(255,255,255,255), false);
    }
}
