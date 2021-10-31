package com.cartoonishvillain.eeriehauntings.mixin;

import com.cartoonishvillain.eeriehauntings.components.HauntedWorker;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class PlayerTickMixin {
    @Inject(at = @At("TAIL"), method = "tick()V")
    private void tick(CallbackInfo info) {
        HauntedWorker.tickHaunting((ServerPlayer) (Object) this);
    }
}

