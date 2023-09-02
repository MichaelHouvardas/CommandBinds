package net.autocommandmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.server.command.ExecuteCommand;
import net.minecraft.server.network.ServerPlayerEntity;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandBinds implements ModInitializer {
	public static final String MOD_ID = "commandmod";
	public static final Logger LOGGER = LoggerFactory.getLogger("auto-command-mod");


	private static final KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(
			new KeyBinding(
					"key.commandmod.command",
					InputUtil.Type.KEYSYM,
					GLFW.GLFW_KEY_0, // Change this to the desired key code
					"key.categories.commandmod"
			)
	);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		// Register a client tick event listener
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
			while (keyBinding.wasPressed()) {
				sendChatCommand("/gamemode creative"); // Replace with your desired command
				LOGGER.info("Executeing command!");

			}
		});
		// Register the player enter world event listener
		PlayerEnterWorldEventListener.register();
	}

	public static void sendChatCommand(String command) {
		MinecraftClient minecraft = MinecraftClient.getInstance();
		ClientPlayerEntity player = minecraft.player;

		if (command.startsWith("/")) {
			command = command.substring(1);
			player.networkHandler.sendChatCommand(command);
		} else {
			player.networkHandler.sendChatMessage(command);
		}
	}
}
