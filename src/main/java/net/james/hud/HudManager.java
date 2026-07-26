package net.james.hud;

import net.james.hud.elements.ArrayListHud;
import net.james.hud.elements.FPSHud;
import net.james.hud.elements.OrdinatesHud;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.ArrayList;
import java.util.List;

public class HudManager {
    HudContext hudContext = new HudContext();


    private final List<AbstractHudElement> elements;
    private static final HudManager INSTANCE = new HudManager();

    private HudManager() {
        elements = new ArrayList<>();
    }

    public void init() {
        register(new FPSHud());
        register(new OrdinatesHud());
        register(new ArrayListHud());
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
        hudContext.update(guiGraphicsExtractor);

        for(AbstractHudElement element : elements) {
            element.calculateDimensions();
            if(!element.isEnabled()) continue;
            element.render(guiGraphicsExtractor);
        }
    }

    public List<AbstractHudElement> getElements() {
        return this.elements;
    }

    protected HudContext getHudContext() {
        return hudContext;
    }
}
