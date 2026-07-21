package net.james.module.modules.hud;

import net.james.hud.FPSHud;
import net.james.hud.HudManager;
import net.james.module.Category;
import net.james.module.Module;

public class FpsModule extends Module {

    private final FPSHud hud = new FPSHud();

    public FpsModule() {
        super("FPS", Category.HUD);
    }

    @Override
    public void onEnable() {
        HudManager.getInstance().register(hud);
    }

    @Override
    public void onDisable() {
        HudManager.getInstance().remove(hud);
    }
}
