package net.james.module;

public abstract class Module {
    private final String name;
    private final Category category;
    protected boolean enabled;

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

    public void toggle() {
        setEnabled(!enabled);
    }
}
