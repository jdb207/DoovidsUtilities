package net.james.hud.elements;
import net.james.hud.AbstractHudElement;
import net.james.hud.Anchor;
import net.james.hud.IHudElement;
import net.james.util.FrameUtil;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;

public class FPSHud extends AbstractHudElement {
    private int fps;

    public FPSHud() {
        super(Anchor.TOP_LEFT,5, 5);
    }

    @Override
    public void render(GuiGraphicsExtractor guiGraphicsExtractor) {
        Minecraft mc = Minecraft.getInstance();
        guiGraphicsExtractor.text(mc.font, getText(), getX(), getY()+1, ARGB.color(255,255,255,255), false);
    }

    @Override
    public void calculateDimensions() {
        Minecraft mc = Minecraft.getInstance();

        width = mc.font.width("FPS: 1000");
        height = mc.font.lineHeight + 1;
    }

    private String getText() {
        return "FPS: " + FrameUtil.CalculateFPS();
    }
}
