package net.james.hud;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.ArrayList;
import java.util.List;

public class HudManager {
    private final List<AbstractHudElement> elements;
    private static final HudManager INSTANCE = new HudManager();

    private HudManager() {
        elements = new ArrayList<>();
    }

    public static HudManager getInstance() {
        return INSTANCE;
    }

    public void register(AbstractHudElement element) {
        elements.add(element);
    }

    public void remove(AbstractHudElement element) {
        elements.remove(element);
    }

    public void render(GuiGraphicsExtractor guiGraphicsExtractor) {
        for(AbstractHudElement element : elements) {
            element.render(guiGraphicsExtractor);
        }
    }

    public List<AbstractHudElement> getElements() {
        return this.elements;
    }
}
