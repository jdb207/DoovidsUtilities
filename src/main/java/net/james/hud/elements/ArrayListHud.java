package net.james.hud.elements;

import net.james.hud.AbstractHudElement;
import net.james.hud.Anchor;
import net.james.hud.IHudElement;
import net.james.module.Module;
import net.james.module.ModuleManager;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;

import java.util.Comparator;
import java.util.List;

public class ArrayListHud extends AbstractHudElement {
    private final static int COLOR = 0xFFFFFFFF;
    private List<Module> enabledModules;

    public ArrayListHud() {
        super(Anchor.TOP_RIGHT,-5,5);

    }

    @Override
    public void render(GuiGraphicsExtractor guiGraphicsExtractor) {
        drawEnabledModules(guiGraphicsExtractor);
    }

    @Override
    public void calculateDimensions() {
        Minecraft mc = Minecraft.getInstance();
        enabledModules = ModuleManager.getInstance().getSortedEnabledModules();
        if(enabledModules != null) {
            String longestName = enabledModules.stream().max(Comparator.comparingInt(module ->mc.font.width(module.getName()))).map(Module::getName).orElse("");
            width = mc.font.width(longestName);
            height = (mc.font.lineHeight + 1) * enabledModules.size();
        }
    }

    public void drawEnabledModules(GuiGraphicsExtractor graphics) {
        Minecraft mc = Minecraft.getInstance();
        if(mc.player == null) return;
        int yPos = getY();
        for(Module module : enabledModules) {
            graphics.text(mc.font, module.getName(),getX(), yPos+1, COLOR);
            yPos += mc.font.lineHeight + 2;
        }
    }








}
