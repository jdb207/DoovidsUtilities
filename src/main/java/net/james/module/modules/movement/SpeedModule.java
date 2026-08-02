package net.james.module.modules.movement;

import net.james.module.Category;
import net.james.module.Module;
import net.james.setting.settings.BooleanSetting;
import net.james.setting.settings.EnumSetting;
import net.james.setting.settings.NumberSetting;

public class SpeedModule extends Module {
    private enum Modes {
        BHOP,
        RAGE
    };

    private BooleanSetting rage = register(new BooleanSetting("Rage", false));
    private EnumSetting mode = register(new EnumSetting<>("Mode", Modes.BHOP));
    private NumberSetting speed = register(new NumberSetting("Speed", 1,0,10,1));

    public SpeedModule() {
        super("Speed", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}

