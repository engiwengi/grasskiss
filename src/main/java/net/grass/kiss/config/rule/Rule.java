package net.grass.kiss.config.rule;

import net.minecraft.item.Item;

import java.util.function.Function;

public class Rule {
    final int priority;
    final Function<Item, Boolean> rule;

    public Rule(int priority, Function<Item, Boolean> rule) {
        this.priority = priority;
        this.rule = rule;
    }

    public int getPriority() {
        return priority;
    }

    public Function<Item, Boolean> getRule() {
        return rule;
    }
}
