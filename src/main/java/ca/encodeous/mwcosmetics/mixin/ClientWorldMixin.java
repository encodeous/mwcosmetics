package ca.encodeous.mwcosmetics.mixin;

import ca.encodeous.mwcosmetics.MwCosmeticsMod;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    @ModifyVariable(at = @At("HEAD"), method = "setTimeOfDay(J)V", ordinal = 0, argsOnly = true)
    public long setTimeOfDay(long time){
        if(MwCosmeticsMod.time != -1){
            return MwCosmeticsMod.time;
        }
        return time;
    }
}
