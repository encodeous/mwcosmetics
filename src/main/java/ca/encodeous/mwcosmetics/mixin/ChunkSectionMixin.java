package ca.encodeous.mwcosmetics.mixin;

import ca.encodeous.mwcosmetics.MwCosmeticsMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.world.chunk.ChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkSection.class)
public class ChunkSectionMixin {

	@Inject(at = @At("HEAD"), method = "setBlockState(IIILnet/minecraft/block/BlockState;Z)Lnet/minecraft/block/BlockState;", cancellable = true)
	public void setBlockState(int x, int y, int z, BlockState state, boolean lock, CallbackInfoReturnable<BlockState> info) {
		if(!MwCosmeticsMod.isInMw) return;
		if(!MwCosmeticsMod.hideBedrock) return;
		int realY = ((ChunkSection)(Object)this).getYOffset();
		if(realY >= 288){
			info.setReturnValue(Blocks.AIR.getDefaultState());
			info.cancel();
		}
	}

	@Inject(at = @At("HEAD"), method = "getBlockState(III)Lnet/minecraft/block/BlockState;", cancellable = true)
	public void getBlockState(int x, int y, int z, CallbackInfoReturnable<BlockState> cir) {
		if(!MwCosmeticsMod.isInMw) return;
		if(!MwCosmeticsMod.hideBedrock) return;
		int realY = ((ChunkSection)(Object)this).getYOffset();
		if(realY >= 288){
			cir.setReturnValue(Blocks.AIR.getDefaultState());
			cir.cancel();
		}
	}

	@Inject(at = @At("HEAD"), method = "fromPacket(Lnet/minecraft/network/PacketByteBuf;)V", cancellable = true)
	public void fromPacket(PacketByteBuf buf, CallbackInfo ci){
		if(!MwCosmeticsMod.isInMw) return;
		if(!MwCosmeticsMod.hideBedrock) return;
		int realY = ((ChunkSection)(Object)this).getYOffset();
		if(realY >= 288){
			ci.cancel();
		}
	}
}
