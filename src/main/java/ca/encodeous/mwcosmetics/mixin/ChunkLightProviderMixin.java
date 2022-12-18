package ca.encodeous.mwcosmetics.mixin;

import ca.encodeous.mwcosmetics.MwCosmeticsMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkLightProvider.class)
public class ChunkLightProviderMixin {
    @Inject(at = @At("HEAD"), method = "getLightLevel(Lnet/minecraft/util/math/BlockPos;)I", cancellable = true)
    public void getLightLevel(BlockPos pos, CallbackInfoReturnable<Integer> cir){
        if(!MwCosmeticsMod.isInMw) return;
        if(!MwCosmeticsMod.disableLightEngine && !MinecraftClient.getInstance().isInSingleplayer()) return;
        cir.setReturnValue(15);
        cir.cancel();
    }

//    @Inject(at = @At("HEAD"), method = "getLevel(J)I", cancellable = true)
//    public void getLevel(long id, CallbackInfoReturnable<Integer> cir){
//        cir.setReturnValue(15);
//        cir.cancel();
//    }

//    @ModifyVariable(at = @At("HEAD"), method = "setLevel(JI)V", ordinal = 0, argsOnly = true)
//    public int setLevel(int level){
//        return 0;
//    }
}
