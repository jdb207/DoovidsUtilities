package net.james;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.james.gui.ClickGuiScreen;
import net.james.hud.HudManager;
import net.james.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilityMod implements ClientModInitializer {
	public static final String MOD_ID = "utility-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private HudManager hudManager;
	private ModuleManager moduleManager;

	@Override
	public void onInitializeClient() {
		hudManager = HudManager.getInstance();
		moduleManager = ModuleManager.getInstance();
		moduleManager.init();
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
