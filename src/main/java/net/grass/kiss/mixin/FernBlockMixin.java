package net.grass.kiss.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.FernBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({FernBlock.class})
@SuppressWarnings("unused")
public abstract class FernBlockMixin extends PlantBlockMixin {
    @Environment(EnvType.CLIENT)
    @Inject(method = "getOutlineShape", at = @At("RETURN"), cancellable = true)
    @Override
    void grasskiss$onGetOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos, CallbackInfoReturnable<VoxelShape> cir) {
        super.grasskiss$onGetOutlineShape(state, view, pos, ePos, cir);
    }
}
