package net.grass.kiss.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.grass.kiss.util.EntityContextDuck;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public abstract class PlantBlockMixin extends BlockMixin {
    @Environment(EnvType.CLIENT)
    void grasskiss$onGetOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos, CallbackInfoReturnable<VoxelShape> cir) {
        if (ePos instanceof EntityContextDuck) {
            Item item = ((EntityContextDuck)ePos).grasskiss$getItem();
            if (item instanceof SwordItem || item instanceof AxeItem || item instanceof TridentItem) {
                cir.setReturnValue(VoxelShapes.empty());
            }
        }
    }

}
