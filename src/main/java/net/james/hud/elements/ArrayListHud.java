package net.james.hud.elements;

import net.james.hud.AbstractHudElement;
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

    @Override
    public void render(GuiGraphicsExtractor guiGraphicsExtractor) {
        List<Module> enabledModules = ModuleManager.getInstance().getSortedEnabledModules();
        drawEnabledModules(guiGraphicsExtractor, enabledModules);
    }

    public void drawEnabledModules(GuiGraphicsExtractor graphics, List<Module> enabledModules) {
        Minecraft mc = Minecraft.getInstance();
        if(mc.player == null) return;
        int xPos;
        int yPos = 5;
        for(Module module : enabledModules) {
            xPos = graphics.guiWidth() - mc.font.width(module.getName()) - 1;
            graphics.text(mc.font, module.getName(),xPos, yPos, COLOR);
            yPos += mc.font.lineHeight + 1;
        }
    }








}
