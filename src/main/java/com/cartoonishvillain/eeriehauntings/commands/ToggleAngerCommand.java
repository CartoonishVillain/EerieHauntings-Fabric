package com.cartoonishvillain.eeriehauntings.commands;

import com.cartoonishvillain.eeriehauntings.components.PlayerComponent;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.GameProfileArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;

import static com.cartoonishvillain.eeriehauntings.components.ComponentStarter.PLAYERCOMPONENTINSTANCE;

public class ToggleAngerCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("eeriehauntings").then(Commands.literal("toggleanger")
                .requires(cs -> {return cs.hasPermission(2);})
                .then(Commands.argument("target", GameProfileArgument.gameProfile()).executes(context -> {
                            return toggleAnger(context.getSource(), GameProfileArgument.getGameProfiles(context, "target"));
                        })
                )));
    }


    private static int toggleAnger(CommandSourceStack source, Collection<GameProfile> profiles){
        for(GameProfile gameProfile : profiles){
            ServerPlayer serverPlayerEntity = source.getServer().getPlayerList().getPlayer(gameProfile.getId());
            PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(serverPlayerEntity);
                h.setAnger(!h.getAnger());
        }
        source.sendSuccess(Component.translatable("anger.eeriehauntings.toggled"), false);
        return 0;
    }
}
