package net.james.module.modules.movement;

import net.james.module.Category;
import net.james.module.Module;
import net.james.setting.settings.BooleanSetting;

public class SpeedModule extends Module {

    private BooleanSetting rage =  register(new BooleanSetting("Rage", false));

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
