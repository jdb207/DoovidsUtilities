package net.james.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.james.UtilityMod;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeybindManager {
    private final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath(UtilityMod.MOD_ID, "Utility Mod")
    );

    public void register() {

    }
    KeyMapping clickGUIKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
            "key.doovids.clickgui", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, this.CATEGORY
    ));


}
