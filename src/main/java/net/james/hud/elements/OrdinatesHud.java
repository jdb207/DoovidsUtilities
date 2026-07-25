package net.james.hud.elements;

import net.james.hud.AbstractHudElement;
import net.james.hud.IHudElement;
import net.james.util.CoordinateUtil;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;
import net.minecraft.world.phys.Vec3;

public class OrdinatesHud extends AbstractHudElement {

    @Override
    public void render(GuiGraphicsExtractor guiGraphicsExtractor) {
        Minecraft mc = Minecraft.getInstance();
        if(mc.player == null) return;
        Vec3 position = CoordinateUtil.getCurrentCoordinates(mc.player);
        Vec3 alternatePosition = CoordinateUtil.getAlternateCoordinates(mc.player, position);
            guiGraphicsExtractor.text(mc.font,"X: " + (int) position.x() + " Y: " + (int) position.y() + " Z: " + (int) position.z() +
                            " (X: " + (int) alternatePosition.x() + " Y: " + (int) alternatePosition.y() + " Z: " + (int) alternatePosition.z() + ")" ,
                    5, guiGraphicsExtractor.guiHeight() - mc.font.lineHeight, ARGB.color(255,255,255,255));

    }
}
