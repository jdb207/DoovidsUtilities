package net.james.module.modules.hud;

import net.james.hud.elements.ArrayListHud;
import net.james.hud.HudManager;
import net.james.module.Category;
import net.james.module.Module;

public class ArrayListModule extends Module {

    private final ArrayListHud arrayListHud = new ArrayListHud();

    public ArrayListModule() {
        super("ArrayList", Category.HUD);
    }

    @Override
    protected void onEnable() {
        HudManager.getInstance().register(arrayListHud);
    }

    @Override
    protected void onDisable() {
        HudManager.getInstance().remove(arrayListHud);
    }
}
