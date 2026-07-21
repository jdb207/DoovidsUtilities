package net.james.gui;

import net.james.hud.HudManager;
import net.james.module.ModuleManager;
import net.james.module.modules.hud.FpsModule;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ClickGuiScreen extends Screen {


    public ClickGuiScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        Button fpsToggle = Button.builder(Component.literal("FPS Toggle"), button -> {
            ModuleManager.getInstance().getModule(FpsModule.class).toggle();
                }
        ).bounds(5,5,120,20).build();
        this.addRenderableWidget(fpsToggle);
    }
}
