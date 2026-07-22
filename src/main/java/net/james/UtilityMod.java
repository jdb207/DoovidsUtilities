package net.james;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.james.gui.ClickGuiScreen;
import net.james.hud.HudManager;
import net.james.keybind.KeybindManager;
import net.james.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilityMod implements ClientModInitializer {
	public static final String MOD_ID = "utilitymod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private HudManager hudManager;
	private ModuleManager moduleManager;

	@Override
	public void onInitializeClient() {
		hudManager = HudManager.getInstance();
		moduleManager = ModuleManager.getInstance();
		moduleManager.init();
		KeybindManager.init();
		registerRenderer();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	private void registerRenderer() {
		HudElementRegistry.addLast(
				UtilityMod.id("hud"),
				((graphics, deltaTracker) ->
						HudManager.getInstance().render(graphics, deltaTracker))
		);
	}

}
