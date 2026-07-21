package net.james.hud;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.ArrayList;
import java.util.List;

public class HudManager {
    private final List<IHudElement> elements;
    private static final HudManager INSTANCE = new HudManager();

    private HudManager() {
        elements = new ArrayList<>();
    }

    public static HudManager getInstance() {
        return INSTANCE;
    }

    public void register(IHudElement element) {
        elements.add(element);
    }

    public void remove(IHudElement element) {
        elements.remove(element);
    }

    public void render(GuiGraphicsExtractor guiGraphicsExtractor, DeltaTracker deltaTracker) {
        for(IHudElement element : elements) {
            element.render(guiGraphicsExtractor, deltaTracker);
        }
    }
}
