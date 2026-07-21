package net.james.hud;

import net.james.util.CoordinateUtil;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;
import net.minecraft.world.phys.Vec3;

public class OrdinatesHud implements IHudElement{

    @Override
    public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        Minecraft mc = Minecraft.getInstance();
        if(mc.player == null) return;
        Vec3 position = CoordinateUtil.getCurrentCoordinates(mc.player);
        Vec3 alternatePosition = CoordinateUtil.getAlternateCoordinates(mc.player, position);
            graphics.text(mc.font,"X: " + (int) position.x() + " Y: " + (int) position.y() + " Z: " + (int) position.z() +
                            " (X: " + (int) alternatePosition.x() + " Y: " + (int) alternatePosition.y() + " Z: " + (int) alternatePosition.z() + ")" ,
                    5, graphics.guiHeight() - mc.font.lineHeight, ARGB.color(255,255,255,255));

    }
}
