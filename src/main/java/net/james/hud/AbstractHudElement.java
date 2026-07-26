package net.james.hud;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;

public abstract class AbstractHudElement implements IHudElement {


    protected Anchor anchor;

    protected int width;
    protected int height;

    private boolean dragging = false;
    private int anchorOffsetX;
    private int anchorOffsetY;

    private int dragOffsetX;
    private int dragOffsetY;

    private boolean enabled;


    private static final int ENABLED_COLOR = 0xff434343;
    private static final int DISABLED_COLOR = ARGB.multiplyAlpha(0xffff6347, 0.5f);

    public abstract void render(GuiGraphicsExtractor guiGraphicsExtractor);

    public AbstractHudElement(Anchor anchor, int offsetX, int offsetY) {
        this.anchor = anchor;
        this.anchorOffsetX = offsetX;
        this.anchorOffsetY = offsetY;
    }


    public void renderEditor(GuiGraphicsExtractor graphicsExtractor, int mouseX, int mouseY) {
        drawBox(graphicsExtractor);
        render(graphicsExtractor);
        if (isMouseOver(mouseX, mouseY)) {
            drawHoverOutline(graphicsExtractor);
        }
    }

    public abstract void calculateDimensions();

    protected void drawBox(GuiGraphicsExtractor guiGraphicsExtractor) {
        int color = isEnabled() ? ENABLED_COLOR : DISABLED_COLOR;
        guiGraphicsExtractor.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), color);
    }

    protected void drawHoverOutline(GuiGraphicsExtractor guiGraphicsExtractor) {
        int color = isEnabled() ? ENABLED_COLOR : DISABLED_COLOR;
        guiGraphicsExtractor.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), ARGB.multiplyAlpha(color, 0.5f));
    }

    public boolean mouseClicked(double mouseX, double mouseY, int buttonPressed) {
        if (!isMouseOver(mouseX, mouseY) || buttonPressed == 2) {
            return false;
        }
        if(buttonPressed == 0) {
            dragging = true;
            dragOffsetX = (int) mouseX - this.getX();
            dragOffsetY = (int) mouseY - this.getY();
        }
        else if(buttonPressed == 1) {
            this.toggle();
        }
        return true;
    }

    public boolean mouseReleased(double mouseX, double mouseY, int buttonPressed) {
        if (!dragging) return false;
        dragging = false;
        Anchor newAnchor = Anchor.fromPosition(
                getHudContext().getScreenWidth(),
                getHudContext().getScreenHeight(),
                getX() + width / 2,
                getY() + height / 2
        );
        setAnchor(newAnchor);

        return true;
    }

    public boolean isMouseOver(double mouseX, double mouseY) {
        return (mouseX >= getX() &&
                mouseX <= getX() + getWidth() &&
                mouseY >= getY() &&
                mouseY <= getY() + getHeight());
    }


    public boolean mouseDragged(double mouseX, double mouseY, int buttonPressed, double dragX, double dragY) {
        if (!dragging) return false;
        setPosition((int) mouseX - dragOffsetX, (int) mouseY - dragOffsetY);
        return true;
    }

    public int getX() {
        return anchor.calculateX(getHudContext().getScreenWidth(),
                width, anchorOffsetX);
    }

    public int getY() {
        return anchor.calculateY(getHudContext().getScreenHeight(),
                height, anchorOffsetY);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setPosition(int x, int y) {
        x = clampX(x);
        y = clampY(y);
        anchorOffsetX = anchor.calculateOffsetX(getHudContext().getScreenWidth(),
                width, x);
        anchorOffsetY = anchor.calculateOffsetY(getHudContext().getScreenHeight(),
                height, y);
    }

    public void setAnchor(Anchor anchor) {
        int currentX = getX();
        int currentY = getY();


        this.anchor = anchor;
        anchorOffsetX = anchor.calculateOffsetX(
                getHudContext().getScreenWidth(),
                width,
                currentX
        );

        anchorOffsetY = anchor.calculateOffsetY(
                getHudContext().getScreenHeight(),
                height,
                currentY
        );
    }

    protected HudContext getHudContext() {
        return HudManager.getInstance().getHudContext();
    }
    private int clampX(int x) {
        return Math.max(1, Math.min(x, getHudContext().getScreenWidth() - width));
    }

    private int clampY(int y) {
        return Math.max(1, Math.min(y, getHudContext().getScreenHeight() - height));
    }

    protected void onEnable() {
        HudManager.getInstance().register(this);
    }
    protected void onDisable() {
        HudManager.getInstance().remove(this);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if(this.enabled == enabled) {
            return;
        }
        this.enabled = enabled;

        if(enabled) {
            onEnable();
        }
        if(!enabled) {
            onDisable();
        }
    }

    public void toggle() {
        setEnabled(!enabled);
    }
}
