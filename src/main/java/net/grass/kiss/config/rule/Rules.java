package net.grass.kiss.config.rule;

import net.grass.kiss.config.GrassKissConfigManager;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;

public class Rules {
    public static final Rule SWORD = new Rule(0, item -> item instanceof SwordItem);
    public static final Rule AXE = new Rule(1, item -> item instanceof AxeItem);
    public static final Rule PICKAXE = new Rule(1, item -> item instanceof PickaxeItem);
    public static final Rule HOE = new Rule(1, item -> item instanceof HoeItem);
    public static final Rule SHOVEL = new Rule(1, item -> item instanceof ShovelItem);
    public static final Rule TRIDENT = new Rule(1, item -> item instanceof TridentItem);
    public static final Rule CUSTOM_ITEMS = new Rule(2, item -> GrassKissConfigManager.getConfig().getApplyItems().contains(Registry.ITEM.getId(item).toString()));
}
