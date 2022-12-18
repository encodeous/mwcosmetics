package ca.encodeous.mwcosmetics.mixin;

import ca.encodeous.mwcosmetics.MwCosmeticsMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.light.LightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightingProvider.class)
public class LightingProviderMixin {
    @Inject(at = @At("HEAD"), method = "getLight(Lnet/minecraft/util/math/BlockPos;I)I", cancellable = true)
    public void getLight(BlockPos pos, int ambientDarkness, CallbackInfoReturnable<Integer> cir){
        if(!MwCosmeticsMod.isInMw) return;
        if(MwCosmeticsMod.disableLightEngine && !MinecraftClient.getInstance().isInSingleplayer()) {
            cir.setReturnValue(15);
            cir.cancel();
        }
    }
}
