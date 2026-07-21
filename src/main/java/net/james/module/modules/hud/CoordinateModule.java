package net.james.module.modules.hud;

import net.james.hud.HudManager;
import net.james.hud.OrdinatesHud;
import net.james.module.Category;
import net.james.module.Module;
import net.james.module.ModuleManager;

public class CoordinateModule extends Module {

    OrdinatesHud coordinatesHud = new OrdinatesHud();

    public CoordinateModule() {
        super("Coordinates", Category.HUD);
    }

    @Override
    protected void onEnable() {
        HudManager.getInstance().register(coordinatesHud);
    }

    @Override
    protected void onDisable() {
        HudManager.getInstance().remove(coordinatesHud);
    }
}

