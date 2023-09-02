package net.autocommandmod; // Adjust the package as needed

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerEnterWorldEventListener {


    public static final Logger LOGGER = LoggerFactory.getLogger("auto-command-mod");
    public static void register() {
        // Your listener code here
        // Register the event listener for player entering world
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof ServerPlayerEntity) {
                // This code will run when a player enters the world
                ServerPlayerEntity player = (ServerPlayerEntity) entity;
                // You can perform actions here when a player enters the world
                // For example, sending a welcome message:
                LOGGER.info("Executeing welcome to the world");
                player.sendMessage(Text.of("Welcome to the world!"), false);
                //CommandBinds.sendChatCommand("/gamemode creative"); //causes a invalid issue
            }
        });
    }

}
