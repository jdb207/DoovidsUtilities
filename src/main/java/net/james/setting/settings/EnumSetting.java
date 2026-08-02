package net.james.setting.settings;

import net.james.setting.Setting;

public class EnumSetting<E extends Enum<E>> extends Setting<E> {
    private E[] values;

    public EnumSetting(String name, E defaultValue) {
        super(name, defaultValue);
        this.values = defaultValue.getDeclaringClass().getEnumConstants();
    }

    public E getNextValue() {
        E current = getValue();
        return values[(current.ordinal() + 1) % values.length];
    }

    public void next() {
        setValue(getNextValue());
    }





}
