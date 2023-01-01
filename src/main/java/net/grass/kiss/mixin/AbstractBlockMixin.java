package net.grass.kiss.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {
    @Environment(EnvType.CLIENT)
    @Inject(method = "getOutlineShape", at = @At("RETURN"), cancellable = true)
    void grasskiss$onGetOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ePos, CallbackInfoReturnable<VoxelShape> cir) {
    }
}
