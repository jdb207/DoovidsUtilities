package net.james.gui.components.clickgui.settings;

import net.james.gui.components.AbstractGuiComponent;
import net.james.setting.Setting;

public abstract class AbstractSettingComponent<T extends Setting<?>> extends AbstractGuiComponent {

    protected T setting;


    protected AbstractSettingComponent(T setting) {
       super(0,0,100);
       this.setting = setting;
    }

    public T getSetting() {
        return setting;
    }




}
