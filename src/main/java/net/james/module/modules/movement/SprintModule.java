package net.james.module.modules.movement;

import net.james.hud.HudManager;
import net.james.module.Category;
import net.james.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class SprintModule extends Module {


    public SprintModule() {
        super("Sprint", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        Minecraft mc = Minecraft.getInstance();

    }

    @Override
    protected void onDisable() {

    }
}
