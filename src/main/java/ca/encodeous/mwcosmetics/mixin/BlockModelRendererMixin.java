package ca.encodeous.mwcosmetics.mixin;

import ca.encodeous.mwcosmetics.MwCosmeticsMod;
import net.minecraft.client.render.block.BlockModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BlockModelRenderer.class)
public class BlockModelRendererMixin {
    @ModifyVariable(at = @At("HEAD"), method = "renderQuads(Lnet/minecraft/client/util/math/MatrixStack$Entry;Lnet/minecraft/client/render/VertexConsumer;FFFLjava/util/List;II)V", ordinal = 0, argsOnly = true)
    private static int renderQuads(int light){
        if(!MwCosmeticsMod.disableLightEngine) return light;
        return 15;
    }
}
