package net.james.module;

import net.james.module.modules.movement.SpeedModule;
import net.james.module.modules.movement.SprintModule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModuleManager {
    private static final ModuleManager INSTANCE = new ModuleManager();
    private final List<Module> modules = new ArrayList<>();


    private final SprintModule sprintModule = new SprintModule();
    private final SpeedModule speedModule = new SpeedModule();

    private ModuleManager() {

    }


    public static ModuleManager getInstance() {
        return INSTANCE;
    }

    public void register(Module module) {
        modules.add(module);
    }

    public void init() {
        register(sprintModule);
        register(speedModule);
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getModules(Category category) {
        return modules.stream().filter(
                module -> module.getCategory() == category
        ).toList();
    }

    public List<Module> getEnabledModules() {
        return modules.stream().filter(
                Module::isEnabled
        ).toList();
    }

    public List<Module> getSortedEnabledModules() {
        return modules.stream().filter(Module::isEnabled).sorted(Comparator.comparing(Module::getName)).toList();
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
