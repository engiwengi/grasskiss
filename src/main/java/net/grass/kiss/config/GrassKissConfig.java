package net.grass.kiss.config;

import com.google.gson.annotations.Expose;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.StringListListEntry;
import net.grass.kiss.GrassKiss;
import net.grass.kiss.config.rule.Rule;
import net.grass.kiss.config.rule.Rules;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;

import java.util.*;

public class GrassKissConfig {
    /**
     * Include sword rule in rule engine
     */
    @Expose
    private boolean applySwords = true;
    /**
     * Include axe rule in rule engine
     */
    @Expose
    private boolean applyAxes = true;
    /**
     * Include shovel rule in rule engine
     */
    @Expose
    private boolean applyShovels = false;
    /**
     * Include pickaxe rule in rule engine
     */
    @Expose
    private boolean applyPickaxes = false;
    /**
     * Include trident rule in rule engine
     */
    @Expose
    private boolean applyTridents = true;
    /**
     * Ignore grass when holding a hoe
     */
    @Expose
    private boolean applyHoes = false;
    /**
     * List of specific items to check for custom items rule
     */
    @Expose
    private List<String> applyItems = new ArrayList<>();
    /**
     * Rules to be checked
     */
    private SortedSet<Rule> rules = new TreeSet<>(Comparator.comparingInt(Rule::getPriority).thenComparing(Rule::hashCode));

    static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(String.format("config.%s.title", GrassKiss.MOD_ID));
        GrassKissConfig config = GrassKissConfigManager.getConfig();
        builder.getOrCreateCategory("general")
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle("Apply to Swords", config.applySwords).setDefaultValue(true).setSaveConsumer(b -> config.applySwords = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle("Apply to Axes", config.applyAxes).setDefaultValue(true).setSaveConsumer(b -> config.applyAxes = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle("Apply to Pickaxes", config.applyPickaxes).setDefaultValue(false).setSaveConsumer(b -> config.applyPickaxes = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle("Apply to Hoes", config.applyHoes).setDefaultValue(false).setSaveConsumer(b -> config.applyHoes = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle("Apply to Shovels", config.applyShovels).setDefaultValue(false).setSaveConsumer(b -> config.applyShovels = b).build())
                .addEntry(ConfigEntryBuilder.create().startBooleanToggle("Apply to Tridents", config.applyTridents).setDefaultValue(true).setSaveConsumer(b -> config.applyTridents = b).build())
                .addEntry(ConfigEntryBuilder.create().startStrList("Apply to individual items", config.getApplyItems()).setTooltip("If not in use, keep empty for performance").setCreateNewInstance(baseListEntry -> new StringListListEntry.StringListCell("minecraft:stone", (StringListListEntry) baseListEntry)).setDefaultValue(new ArrayList<>()).setSaveConsumer(items -> config.applyItems = items).build());
        builder.setSavingRunnable(GrassKissConfigManager::save);
        return builder.build();
    }

    private void refreshRule(Rule rule, Boolean b) {
        if (b) {
            rules.add(rule);
        } else {
            rules.remove(rule);
        }
    }

    public List<String> getApplyItems() {
        return applyItems;
    }

    public void refreshOnSave() {
        refreshRule(Rules.SWORD, applySwords);
        refreshRule(Rules.PICKAXE, applyPickaxes);
        refreshRule(Rules.AXE, applyAxes);
        refreshRule(Rules.HOE, applyHoes);
        refreshRule(Rules.SHOVEL, applyShovels);
        refreshRule(Rules.TRIDENT, applyTridents);
        refreshRule(Rules.CUSTOM_ITEMS, !applyItems.isEmpty());
    }

    public boolean check(Item item) {
        for (Rule rule : rules) {
            if (rule.getRule().apply(item)) {
                return true;
            }
        }
        return false;
    }
}

