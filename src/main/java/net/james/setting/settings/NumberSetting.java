package net.james.setting.settings;

import net.james.setting.Setting;

public class NumberSetting extends Setting<Double> {
    private double min;
    private double max;
    private double increment;


    public NumberSetting(String name, double defaultValue, double min, double max, double increment) {
        super(name,defaultValue);
        this.min = min;
        this.max = max;
        this.increment = increment;
    }


    @Override
    public void setValue(Double value) {
        super.setValue(Math.max(min, Math.min(max, value)));
    }


}
