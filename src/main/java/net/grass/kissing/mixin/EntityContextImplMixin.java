package net.grass.kissing.mixin;

import net.grass.kissing.util.EntityContextDuck;
import net.minecraft.entity.EntityContextImpl;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityContextImpl.class)
public class EntityContextImplMixin implements EntityContextDuck {
    @Shadow
    @Final
    private Item heldItem;

    @Unique
    public Item grasskissing$getItem() {
        return this.heldItem;
    }

}
