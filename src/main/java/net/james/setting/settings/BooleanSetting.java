package net.james.setting.settings;

import net.james.setting.Setting;

public class BooleanSetting extends Setting<Boolean> {


    public BooleanSetting(String name, boolean defaultValue) {
        super(name, defaultValue);
    }

    public void toggle() {
        setValue(!getValue());
    }

    public boolean isEnabled() {
        return getValue();
    }

}
