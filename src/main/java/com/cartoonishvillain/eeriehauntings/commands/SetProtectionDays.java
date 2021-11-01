package com.cartoonishvillain.eeriehauntings.commands;

import com.cartoonishvillain.eeriehauntings.components.PlayerComponent;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.GameProfileArgument;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;

import static com.cartoonishvillain.eeriehauntings.components.ComponentStarter.PLAYERCOMPONENTINSTANCE;

public class SetProtectionDays {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("eeriehauntings").then(Commands.literal("setprotectiondays")
                .requires(cs -> {return cs.hasPermission(2);})
                .then(Commands.argument("target", GameProfileArgument.gameProfile()).then(Commands.argument("days", IntegerArgumentType.integer(0)).executes(context -> {
                    return setHauntChance(context.getSource(), GameProfileArgument.getGameProfiles(context, "target"), IntegerArgumentType.getInteger(context, "days"));
                }))))

        );
    }


    private static int setHauntChance(CommandSourceStack source, Collection<GameProfile> profiles, int days){
        for(GameProfile gameProfile : profiles){
            ServerPlayer serverPlayerEntity = source.getServer().getPlayerList().getPlayer(gameProfile.getId());
            PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(serverPlayerEntity);
                h.setProtectedDays(days);
        }
        if(days > 0) {
            source.sendSuccess(new TranslatableComponent("protected.eeriehauntings.success", days), false);
        }else {
            source.sendSuccess(new TranslatableComponent("protected.eeriehauntings.removed"), false);
        }
        return 0;
    }
}
