package net.grass.kiss.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.grass.kiss.config.GrassKissConfigManager;
import net.grass.kiss.util.EntityContextDuck;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public abstract class PlantBlockMixin extends AbstractBlockMixin {
    @Environment(EnvType.CLIENT)
    @Override
    void grasskiss$onGetOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ePos, CallbackInfoReturnable<VoxelShape> cir) {
        if (ePos instanceof EntityContextDuck) {
            Item item = ((EntityContextDuck) ePos).grasskiss$getItem();
            if (GrassKissConfigManager.getConfig().check(item)) {
                cir.setReturnValue(VoxelShapes.empty());
            }
        }
    }
}
