package net.james.module;

import net.james.hud.OrdinatesHud;
import net.james.module.modules.hud.CoordinateModule;
import net.james.module.modules.hud.FpsModule;
import net.minecraft.world.entity.animal.feline.Cat;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private static final ModuleManager INSTANCE = new ModuleManager();
    private final List<Module> modules = new ArrayList<>();
    private final FpsModule fpsModule = new FpsModule();
    private final CoordinateModule coordinateModule = new CoordinateModule();

    public ModuleManager() {

    }


    public static ModuleManager getInstance() {
        return INSTANCE;
    }

    public void register(Module module) {
        modules.add(module);
    }

    public void init() {
        register(fpsModule);
        register(coordinateModule);

        fpsModule.toggle();
        coordinateModule.toggle();
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getModules(Category category) {
        return modules.stream().filter(
                module -> module.getCategory() == category
        ).toList();
    }

    public <T extends Module> T getModule(Class<T> clazz) {
        for(Module module : modules) {
            if(clazz.isInstance(module)) {
                return clazz.cast(module);
            }
        }
        return null;
    }
}
