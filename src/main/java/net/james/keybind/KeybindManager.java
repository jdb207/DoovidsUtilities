package net.james.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.james.UtilityMod;
import net.james.gui.ClickGuiScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeybindManager {
    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath(UtilityMod.MOD_ID, "doovids")
    );

    public static final KeyMapping clickGUIKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
            "key.doovids.clickgui", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, CATEGORY
    ));

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(clickGUIKey.consumeClick()) {
                if(client.player != null) {
                    Minecraft.getInstance().setScreenAndShow(new ClickGuiScreen(Component.empty()));
                }
            }
        });
    }
}
