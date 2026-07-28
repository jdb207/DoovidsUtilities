package net.james.module;

import net.james.setting.Setting;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    private final String name;
    private final Category category;
    protected boolean enabled;
    private final List<Setting <?>> settings = new ArrayList<>();

    protected Module(String name, Category category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
    }


    protected abstract void onEnable();

    protected abstract void onDisable();

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        if(this.enabled == enabled) {
            return;
        }
        this.enabled = enabled;
        if(enabled) {
            onEnable();
        }
        else {
            onDisable();
        }
    }

    protected <T extends Setting<?>> T register(T setting) {
        settings.add(setting);
        return setting;
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public List<? extends Setting> getSettings() {
        return settings;
    }


}
