package ca.encodeous.mwcosmetics;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MwCosmeticsMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("mwcosmetics");
	public static int time = -1;
	public static boolean hideBedrock = true;
	public static boolean disableLightEngine = true;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			dispatcher.register(ClientCommandManager.literal("chelp")
							.executes(context -> {
								context.getSource().sendFeedback(Text.of("Missile Wars Cosmetics - by encodeous\n" +
										"/chelp - show help\n" +
										"/ctime - set the client time\n" +
										"/cbr - toggle the bedrock visibility (requires reloading chunks)\n" +
										"/cdl - toggle the lighting engine (requires reloading chunks) - works differently in single player"));
								return 1;
							}));
		});
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(ClientCommandManager.literal("ctime")
					.then(argument("newTime", IntegerArgumentType.integer())
							.executes(context -> {
								time = context.getArgument("newTime", Integer.class);
								context.getSource().sendFeedback(Text.of("Time has been set. Set to -1 to unset the value."));
								return 1;
							})));
		});
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			dispatcher.register(ClientCommandManager.literal("cbr")
					.executes(context -> {
						hideBedrock = !hideBedrock;
						context.getSource().sendFeedback(Text.of("The bedrock is now " + (hideBedrock ? "hidden." : "shown.")));
						return 1;
					}));
		});
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			dispatcher.register(ClientCommandManager.literal("cdl")
					.executes(context -> {
						disableLightEngine = !disableLightEngine;
						context.getSource().sendFeedback(Text.of("The lighting engine is now " + (disableLightEngine ? "disabled." : "enabled.")));
						return 1;
					}));
		});
	}
}
