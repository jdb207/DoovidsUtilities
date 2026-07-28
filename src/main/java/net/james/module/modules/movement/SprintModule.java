package net.james.module.modules.movement;

import net.james.hud.HudManager;
import net.james.module.Category;
import net.james.module.Module;
import net.james.setting.Setting;
import net.james.setting.settings.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class SprintModule extends Module {

    private BooleanSetting fortnite = register(new BooleanSetting("Fortnite", false));
    private BooleanSetting em = register(new BooleanSetting("em", false));


    public SprintModule() {
        super("Sprint", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
