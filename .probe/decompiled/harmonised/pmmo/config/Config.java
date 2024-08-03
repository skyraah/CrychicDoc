package harmonised.pmmo.config;

import com.mojang.serialization.Codec;
import harmonised.pmmo.api.enums.EventType;
import harmonised.pmmo.api.enums.ModifierDataType;
import harmonised.pmmo.api.enums.ReqType;
import harmonised.pmmo.config.codecs.CodecTypes;
import harmonised.pmmo.config.readers.ConfigHelper;
import harmonised.pmmo.config.readers.TomlConfigHelper;
import harmonised.pmmo.util.MsLoggy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec SERVER_CONFIG;

    public static ForgeConfigSpec.ConfigValue<Double> SKILL_LIST_OFFSET_X;

    public static ForgeConfigSpec.ConfigValue<Double> SKILL_LIST_OFFSET_Y;

    public static ForgeConfigSpec.ConfigValue<Double> VEIN_GAUGE_OFFSET_X;

    public static ForgeConfigSpec.ConfigValue<Double> VEIN_GAUGE_OFFSET_Y;

    public static ForgeConfigSpec.ConfigValue<Double> GAIN_LIST_OFFSET_X;

    public static ForgeConfigSpec.ConfigValue<Double> GAIN_LIST_OFFSET_Y;

    public static ForgeConfigSpec.ConfigValue<Boolean> SKILL_LIST_DISPLAY;

    public static ForgeConfigSpec.ConfigValue<Boolean> GAIN_LIST_DISPLAY;

    public static ForgeConfigSpec.ConfigValue<Boolean> VEIN_GAUGE_DISPLAY;

    public static ForgeConfigSpec.ConfigValue<Integer> SECTION_HEADER_COLOR;

    public static ForgeConfigSpec.ConfigValue<Integer> SALVAGE_ITEM_COLOR;

    public static ForgeConfigSpec.ConfigValue<Integer> GAIN_LIST_LINGER_DURATION;

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> GAIN_BLACKLIST;

    public static ForgeConfigSpec.BooleanValue HIDE_SKILL_BUTTON;

    public static ForgeConfigSpec.ConfigValue<Integer> SKILL_BUTTON_X;

    public static ForgeConfigSpec.ConfigValue<Integer> SKILL_BUTTON_Y;

    public static ForgeConfigSpec.BooleanValue SKILLUP_UNLOCKS;

    public static ForgeConfigSpec.BooleanValue HIDE_MET_REQS;

    private static ForgeConfigSpec.BooleanValue[] TOOLTIP_REQ_ENABLED;

    private static ForgeConfigSpec.BooleanValue[] TOOLTIP_XP_ENABLED;

    private static ForgeConfigSpec.BooleanValue[] TOOLTIP_BONUS_ENABLED;

    private static final String TOOLTIP_SUFFIX = " tooltip enabled";

    public static ForgeConfigSpec.IntValue VEIN_LIMIT;

    public static ForgeConfigSpec.BooleanValue BREAK_NERF_HIGHLIGHTS;

    public static ForgeConfigSpec.BooleanValue BLOCK_OWNER_HIGHLIGHTS;

    public static ForgeConfigSpec.BooleanValue SALVAGE_HIGHLIGHTS;

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> INFO_LOGGING;

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> DEBUG_LOGGING;

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> WARN_LOGGING;

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ERROR_LOGGING;

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FATAL_LOGGING;

    public static ForgeConfigSpec.ConfigValue<Double> CREATIVE_REACH;

    public static ForgeConfigSpec.ConfigValue<String> SALVAGE_BLOCK;

    public static ForgeConfigSpec.BooleanValue TREASURE_ENABLED;

    public static ForgeConfigSpec.BooleanValue BREWING_TRACKED;

    public static ForgeConfigSpec.ConfigValue<Integer> MAX_LEVEL;

    public static ForgeConfigSpec.ConfigValue<Double> LOSS_ON_DEATH;

    public static ForgeConfigSpec.ConfigValue<Boolean> LOSE_LEVELS_ON_DEATH;

    public static ForgeConfigSpec.ConfigValue<Boolean> LOSE_ONLY_EXCESS;

    public static ForgeConfigSpec.ConfigValue<Boolean> USE_EXPONENTIAL_FORMULA;

    public static ForgeConfigSpec.ConfigValue<Double> GLOBAL_MODIFIER;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> SKILL_MODIFIERS;

    public static ForgeConfigSpec.ConfigValue<Long> LINEAR_BASE_XP;

    public static ForgeConfigSpec.ConfigValue<Double> LINEAR_PER_LEVEL;

    public static ForgeConfigSpec.ConfigValue<Integer> EXPONENTIAL_BASE_XP;

    public static ForgeConfigSpec.ConfigValue<Double> EXPONENTIAL_POWER_BASE;

    public static ForgeConfigSpec.ConfigValue<Double> EXPONENTIAL_LEVEL_MOD;

    public static ConfigHelper.ConfigObject<List<Long>> STATIC_LEVELS;

    private static ForgeConfigSpec.BooleanValue[] REQ_ENABLED;

    private static final String REQ_ENABLED_SUFFIX = " Req Enabled";

    public static ForgeConfigSpec.ConfigValue<Double> REUSE_PENALTY;

    public static ForgeConfigSpec.ConfigValue<Boolean> SUMMATED_MAPS;

    public static TomlConfigHelper.ConfigObject<Map<String, Map<String, Long>>> RECEIVE_DAMAGE_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Map<String, Long>>> DEAL_DAMAGE_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> JUMP_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> SPRINT_JUMP_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> CROUCH_JUMP_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> BREATH_CHANGE_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> HEALTH_CHANGE_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> HEALTH_INCREASE_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> HEALTH_DECREASE_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> SPRINTING_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> SUBMERGED_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> SWIMMING_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> DIVING_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> SURFACING_XP;

    public static TomlConfigHelper.ConfigObject<Map<String, Double>> SWIM_SPRINTING_XP;

    public static ForgeConfigSpec.IntValue PARTY_RANGE;

    public static ForgeConfigSpec.DoubleValue PARTY_BONUS;

    public static ForgeConfigSpec.BooleanValue MOB_SCALING_ENABLED;

    public static ForgeConfigSpec.ConfigValue<Boolean> MOB_USE_EXPONENTIAL_FORMULA;

    public static ForgeConfigSpec.ConfigValue<Integer> MOB_SCALING_AOE;

    public static ForgeConfigSpec.ConfigValue<Integer> MOB_SCALING_BASE_LEVEL;

    public static ForgeConfigSpec.ConfigValue<Double> MOB_LINEAR_PER_LEVEL;

    public static ForgeConfigSpec.ConfigValue<Double> MOB_EXPONENTIAL_POWER_BASE;

    public static ForgeConfigSpec.ConfigValue<Double> MOB_EXPONENTIAL_LEVEL_MOD;

    public static ForgeConfigSpec.ConfigValue<Double> BOSS_SCALING_RATIO;

    public static TomlConfigHelper.ConfigObject<Map<String, Map<String, Double>>> MOB_SCALING;

    public static ForgeConfigSpec.ConfigValue<Boolean> VEIN_ENABLED;

    public static ForgeConfigSpec.ConfigValue<Boolean> REQUIRE_SETTING;

    public static ForgeConfigSpec.ConfigValue<Integer> DEFAULT_CONSUME;

    public static ForgeConfigSpec.DoubleValue VEIN_CHARGE_MODIFIER;

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> VEIN_BLACKLIST;

    public static ForgeConfigSpec.DoubleValue BASE_CHARGE_RATE;

    public static ForgeConfigSpec.IntValue BASE_CHARGE_CAP;

    private static void setupClient(ForgeConfigSpec.Builder builder) {
        builder.comment("PMMO Client Configuration").push("Client");
        buildGUI(builder);
        buildTooltips(builder);
        buildVein(builder);
        buildTutorial(builder);
        builder.pop();
    }

    private static void buildGUI(ForgeConfigSpec.Builder builder) {
        builder.comment("Configuration settings for the guis").push("GUI");
        SKILL_LIST_OFFSET_X = builder.comment("how far right from the top left corner the skill list should be").defineInRange("Skill List Xoffset", 0.01, 0.0, 1.0);
        SKILL_LIST_OFFSET_Y = builder.comment("how far down from the top left corner the skill list should be").defineInRange("Skill List Yoffset", 5.0E-4, 0.0, 1.0);
        SKILL_LIST_DISPLAY = builder.comment("Should the skill list be displayed").define("Display Skill List", true);
        VEIN_GAUGE_OFFSET_X = builder.comment("how far right from the bottom left corner the vein guage sholud be").defineInRange("Vein Gauge Xoffset", 0.01, 0.0, 1.0);
        VEIN_GAUGE_OFFSET_Y = builder.comment("how far up from the bottm left corner the vein guage should be").defineInRange("Vein Gauge Yoffset", 0.95, 0.0, 1.0);
        VEIN_GAUGE_DISPLAY = builder.comment("Should the vein charge data be displayed").define("Display Vein Gauge", true);
        GAIN_LIST_OFFSET_X = builder.comment("how far offset from center the gain list should be").defineInRange("Gain List Xoffset", 0.45, 0.0, 1.0);
        GAIN_LIST_OFFSET_Y = builder.comment("how far down from the top left corner the gain list should be").defineInRange("Gain List Yoffset", 5.0E-4, 0.0, 1.0);
        GAIN_LIST_DISPLAY = builder.comment("Should the Gain list be displayed").define("Display Gain List", true);
        SECTION_HEADER_COLOR = builder.comment("what color should the background be for the section header lines in the glossary").define("Section Header Color", 352630048);
        SALVAGE_ITEM_COLOR = builder.comment("What color should the background be for the salvage item lines in the glossary").define("Salvage Item Color", 366125849);
        GAIN_LIST_LINGER_DURATION = builder.comment("How long, in ticks, items on the gain list should stay on screen before disappearing").define("Gain List Linger Duration", 100);
        GAIN_BLACKLIST = builder.comment("skills that should now show their gains in the gain list.  this can be used to limit spammy skills").defineList("Gain Blacklist", new ArrayList(), s -> s instanceof String);
        HIDE_SKILL_BUTTON = builder.comment("if true, removes the skills button from the inventory screen").define("hide skill button", false);
        SKILL_BUTTON_X = builder.comment("the horizontal location of the skill button in the inventory.", "Default = 126.  For removing overlaps, 150 is a good setting").define("skill_button_x", 126);
        SKILL_BUTTON_Y = builder.comment("the vertical location (from center) of the skill button in the inventory.", "Default = -22").define("skill_button_y", -22);
        SKILLUP_UNLOCKS = builder.comment("If enabled, lists in chat all features unlocked when a skill levels up.").define("skillup_unlocks", true);
        builder.pop();
    }

    public static ForgeConfigSpec.BooleanValue tooltipReqEnabled(ReqType type) {
        return TOOLTIP_REQ_ENABLED[type.ordinal()];
    }

    public static ForgeConfigSpec.BooleanValue tooltipXpEnabled(EventType type) {
        return TOOLTIP_XP_ENABLED[type.ordinal()];
    }

    public static ForgeConfigSpec.BooleanValue tooltipBonusEnabled(ModifierDataType type) {
        return TOOLTIP_BONUS_ENABLED[type.ordinal()];
    }

    private static void buildTooltips(ForgeConfigSpec.Builder builder) {
        builder.comment("Generic Tooltip Settings").push("ToolTip_Settings");
        HIDE_MET_REQS = builder.comment("Should met reqs be hidden on the tooltip.").define("Hide Met Req Tooltips", true);
        builder.pop();
        builder.comment("This section covers the various tooltip elements and whether they should be enabled").push("Tooltip_Visibility");
        List<ReqType> rawReqList = new ArrayList(Arrays.asList(ReqType.values()));
        builder.push("Requirement_Tooltips");
        TOOLTIP_REQ_ENABLED = (ForgeConfigSpec.BooleanValue[]) rawReqList.stream().map(t -> builder.define(t.toString() + " Req  tooltip enabled", true)).toArray(ForgeConfigSpec.BooleanValue[]::new);
        builder.pop();
        List<EventType> rawEventList = new ArrayList(Arrays.asList(EventType.values()));
        builder.push("Xp_Gain_Tooltips");
        TOOLTIP_XP_ENABLED = (ForgeConfigSpec.BooleanValue[]) rawEventList.stream().map(t -> builder.define(t.toString() + " XP Gain  tooltip enabled", true)).toArray(ForgeConfigSpec.BooleanValue[]::new);
        builder.pop();
        List<ModifierDataType> rawBonusList = new ArrayList(Arrays.asList(ModifierDataType.values()));
        builder.push("Bonus_Tooltips");
        TOOLTIP_BONUS_ENABLED = (ForgeConfigSpec.BooleanValue[]) rawBonusList.stream().map(t -> builder.define(t.toString() + " Bonus  tooltip enabled", true)).toArray(ForgeConfigSpec.BooleanValue[]::new);
        builder.pop();
        builder.pop();
    }

    private static void buildVein(ForgeConfigSpec.Builder builder) {
        builder.comment("Client Settings Related to the Vein Mining Ability").push("Vein_Miner");
        VEIN_LIMIT = builder.comment("The max blocks a vein activation should consume regardless of charge").defineInRange("Vein_Limit", 64, 0, Integer.MAX_VALUE);
        builder.pop();
    }

    private static void buildTutorial(ForgeConfigSpec.Builder builder) {
        builder.comment("Toggles for helpful features related to mechanics").push("Tutorial");
        BREAK_NERF_HIGHLIGHTS = builder.comment("Should blocks affected by Reuse Penalty show a red outline?").define("Enable Reuse Penalty Highlights", true);
        BLOCK_OWNER_HIGHLIGHTS = builder.comment("Should the owner of a block show when hovering?").define("Enable Owner Highlights", true);
        SALVAGE_HIGHLIGHTS = builder.comment("Should hovering over a salvage block show helpful tips?").define("Enable Salvage Tips", true);
        builder.pop();
    }

    private static void setupCommon(ForgeConfigSpec.Builder builder) {
        builder.comment("===============================================", "", "", "Most Configurations are found in the server config", "You can find that in worldname/serverconfig/", "", "", "===============================================").push("Common");
        buildMsLoggy(builder);
        builder.pop();
    }

    private static void buildMsLoggy(ForgeConfigSpec.Builder builder) {
        builder.comment("PMMO Error Logging Configuration", "", "===================================================", "To enable Logging with MsLoggy, enter a logging", "keyword into the logging level list that you want.", "the list of keywords are (lowercase only):", "'api', 'autovalues', 'chunk', 'data', 'event', ", "'feature', 'gui', 'loading', 'network', and 'xp'", "===================================================").push("Ms Loggy");
        INFO_LOGGING = builder.comment("Which MsLoggy info logging should be enabled?  This will flood your log with data, but provides essential details", " when trying to find data errors and bug fixing.  ").defineList("Info Logging", new ArrayList(List.of(MsLoggy.LOG_CODE.LOADING.code, MsLoggy.LOG_CODE.NETWORK.code, MsLoggy.LOG_CODE.API.code)), s -> s instanceof String);
        DEBUG_LOGGING = builder.comment("Which MsLoggy debug logging should be enabled?  This will flood your log with data, but provides essential details", " when trying to find bugs. DEVELOPER SETTING (mostly).  ").defineList("Debug Logging", new ArrayList(List.of(MsLoggy.LOG_CODE.AUTO_VALUES.code)), s -> s instanceof String);
        WARN_LOGGING = builder.comment("Which MsLoggy warn logging should be enabled?  This log type is helpful for catching important but non-fatal issues").defineList("Warn Logging", new ArrayList(List.of(MsLoggy.LOG_CODE.API.code)), s -> s instanceof String);
        ERROR_LOGGING = builder.comment("Which Error Logging should be enabled.  it is highly recommended this stay true.  however, you can", "disable it to remove pmmo errors from the log.").defineList("Error Logging", new ArrayList(List.of(MsLoggy.LOG_CODE.DATA.code, MsLoggy.LOG_CODE.API.code)), s -> s instanceof String);
        FATAL_LOGGING = builder.comment("Which MsLoggy fatal logging should be enabled?  I can't imagine a situation where you'd want this off, but here you go.").defineList("Fatal Logging", new ArrayList(List.of(MsLoggy.LOG_CODE.API.code)), s -> s instanceof String);
        builder.pop();
    }

    private static void setupServer(ForgeConfigSpec.Builder builder) {
        buildBasics(builder);
        buildLevels(builder);
        buildRequirements(builder);
        buildXpGains(builder);
        buildPartySettings(builder);
        buildMobScalingSettings(builder);
        buildVeinMinerSettings(builder);
    }

    private static void buildBasics(ForgeConfigSpec.Builder builder) {
        builder.comment("General settings on the server").push("General");
        CREATIVE_REACH = builder.comment("how much extra reach should a player get in creative mode").defineInRange("Creative Reach", 50.0, 4.0, Double.MAX_VALUE);
        SALVAGE_BLOCK = builder.comment("Which block should be used for salvaging").define("Salvage Block", "minecraft:smithing_table");
        TREASURE_ENABLED = builder.comment("if false, all pmmo loot conditions will be turned off").define("Treasure Enabled", true);
        BREWING_TRACKED = builder.comment("If false, pmmo will not track if a potion was previously brewed.", "this helps with stacking potions from other mods, but ", "does not prevent users from pick-placing potions in the", "brewing stand for free XP. Toggle at your discretion.").define("brewing_tracked", true);
        builder.pop();
    }

    private static void buildLevels(ForgeConfigSpec.Builder builder) {
        builder.comment("Settings related level gain").push("Levels");
        MAX_LEVEL = builder.comment("The highest level a player can achieve in any skill.", "NOTE: if this is changing on you to a lower value, that's intentional", "If your formula makes the required xp to get max level greater than", "pmmo can store, pmmo will replace your value with the actual max.").defineInRange("Max Level", 1523, 1, Integer.MAX_VALUE);
        USE_EXPONENTIAL_FORMULA = builder.comment("should levels be determined using an exponential formula?").define("Use Exponential Formula", true);
        STATIC_LEVELS = ConfigHelper.defineObject(builder.comment("=====LEAVE -1 VALUE UNLESS YOU WANT STATIC LEVELS=====", "Replacing the -1 and adding values to this list will set the xp required to advance for each", "level manually.  Note that the number of level settings you enter into this list", "will set your max level.  If you only add 10 entries, your max level will be 10.", "This setting is intended for players/ops who want fine-tune control over their", "level growth.  use with caution.  ", "", "As a technical note, if you enter values that are not greater than their previous", "value, the entire list will be ignored and revert back to the selected exponential", "or linear formulaic calculation"), "Static_Levels", Codec.LONG.listOf(), new ArrayList(List.of(-1L)));
        LOSS_ON_DEATH = builder.comment("How much experience should players lose when they die?", "zero is no loss, one is lose everything").defineInRange("Loss on death", 0.05, 0.0, 1.0);
        LOSE_LEVELS_ON_DEATH = builder.comment("should loss of experience cross levels?", "for example, if true, a player with 1 xp above their current level would lose the", "[Loss on death] percentage of xp and fall below their current level.  However,", "if false, the player would lose only 1 xp as that would put them at the base xp of their current level").define("Lose Levels On Death", false);
        LOSE_ONLY_EXCESS = builder.comment("This setting only matters if [Lose Level On Death] is set to false.", "If this is true the [Loss On Death] applies only to the experience above the current level", "for example if level 3 is 1000k xp and the player has 1020 and dies.  the player will only lose", "the [Loss On Death] of the 20 xp above the level's base.").define("Lose Only Excess", true);
        GLOBAL_MODIFIER = builder.comment("Modifies how much xp is earned.  This is multiplicative to the XP.", "(Mutually Exclusive to [Skill Modifiers])").define("Global Modifier", 1.0);
        SKILL_MODIFIERS = TomlConfigHelper.defineObject(builder.comment("Modifies xp gains for specific skills.  This is multiplicative to the XP.", "(Mutually Exclusive to [Global Modifier])"), "Skill Modifiers", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("example_skill", 1.0));
        builder.comment("Settings for Linear XP configuration").push("LINEAR LEVELS");
        LINEAR_BASE_XP = builder.comment("what is the base xp to reach level 2 (this + level * xpPerLevel)").defineInRange("Base XP", 250L, 0L, Long.MAX_VALUE);
        LINEAR_PER_LEVEL = builder.comment("What is the xp increase per level (baseXp + level * this)").defineInRange("Per Level", 500.0, 0.0, Double.MAX_VALUE);
        builder.pop();
        builder.comment("Settings for Exponential XP configuration").push("EXPONENTIAL LEVELS");
        EXPONENTIAL_BASE_XP = builder.comment("What is the x in: x * ([Power Base]^([Per Level] * level))").defineInRange("Base XP", 250, 1, Integer.MAX_VALUE);
        EXPONENTIAL_POWER_BASE = builder.comment("What is the x in: [Base XP] * (x^([Per Level] * level))").defineInRange("Power Base", 1.104088404342588, 0.0, Double.MAX_VALUE);
        EXPONENTIAL_LEVEL_MOD = builder.comment("What is the x in: [Base XP] * ([Power Base]^(x * level))").defineInRange("Per Level", 1.1, 0.0, Double.MAX_VALUE);
        builder.pop();
        builder.pop();
    }

    public static ForgeConfigSpec.BooleanValue reqEnabled(ReqType type) {
        return REQ_ENABLED[type.ordinal()];
    }

    private static void buildRequirements(ForgeConfigSpec.Builder builder) {
        List<ReqType> rawReqList = new ArrayList(Arrays.asList(ReqType.values()));
        builder.comment("Should requirements apply for the applicable action type").push("Requirements");
        REQ_ENABLED = (ForgeConfigSpec.BooleanValue[]) rawReqList.stream().map(t -> builder.define(t.toString() + " Req Enabled", true)).toArray(ForgeConfigSpec.BooleanValue[]::new);
        builder.pop();
    }

    private static void buildXpGains(ForgeConfigSpec.Builder builder) {
        builder.comment("All settings related to the gain of experience").push("XP_Gains");
        REUSE_PENALTY = builder.comment("how much of the original XP should be awarded when a player breaks a block they placed").defineInRange("Reuse Penalty", 0.0, 0.0, Double.MAX_VALUE);
        SUMMATED_MAPS = builder.comment("Should xp Gains from perks be added onto by configured xp values").define("Perks Plus Configs", false);
        buildEventBasedXPSettings(builder);
        builder.pop();
    }

    private static void buildEventBasedXPSettings(ForgeConfigSpec.Builder builder) {
        builder.comment("Settings related to certain default event XP awards.").push("Event_XP_Specifics");
        builder.push("Damage").comment("damage dealt and received is defined by the damage type", "or damage type tag preceding it.  xp is awarded based on", "the value below multiplied by the damage applied.");
        DEAL_DAMAGE_XP = TomlConfigHelper.defineObject(builder, "DEAL_DAMAGE", CodecTypes.DAMAGE_XP_CODEC, Map.of("minecraft:generic_kill", Map.of("combat", 1L), "minecraft:player_attack", Map.of("combat", 1L), "#minecraft:is_projectile", Map.of("archery", 1L)));
        RECEIVE_DAMAGE_XP = TomlConfigHelper.defineObject(builder, "RECEIVE_DAMAGE", CodecTypes.DAMAGE_XP_CODEC, Map.of("minecraft:generic_kill", Map.of("endurance", 1L), "#pmmo:environment", Map.of("endurance", 10L), "#pmmo:impact", Map.of("endurance", 15L), "#pmmo:magic", Map.of("magic", 15L), "#minecraft:is_projectile", Map.of("endurance", 15L)));
        builder.pop();
        builder.push("Jumps");
        JUMP_XP = TomlConfigHelper.defineObject(builder, "JUMP Skills and Ratios", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("agility", 2.5));
        SPRINT_JUMP_XP = TomlConfigHelper.defineObject(builder, "SPRINT_JUMP Skills and Ratios", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("agility", 2.5));
        CROUCH_JUMP_XP = TomlConfigHelper.defineObject(builder, "CROUCH_JUMP Skills and Ratios", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("agility", 2.5));
        builder.pop();
        builder.push("Player_Actions");
        BREATH_CHANGE_XP = TomlConfigHelper.defineObject(builder, "BREATH_CHANGE Skills and Ratios", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("swimming", 1.0));
        HEALTH_CHANGE_XP = TomlConfigHelper.defineObject(builder, "HEALTH_CHANGE Skills and Ratios", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("endurance", 0.0));
        HEALTH_INCREASE_XP = TomlConfigHelper.defineObject(builder, "HEALTH_INCREASE Skills and Ratios", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("endurance", 1.0));
        HEALTH_DECREASE_XP = TomlConfigHelper.defineObject(builder, "HEALTH_DECREASE Skills and Ratios", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("endurance", 1.0));
        SPRINTING_XP = TomlConfigHelper.defineObject(builder, "SPRINTING Skills and Ratios", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("agility", 100.0));
        SUBMERGED_XP = TomlConfigHelper.defineObject(builder, "SUBMERGED Skills and Ratios", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("swimming", 1.0));
        SWIMMING_XP = TomlConfigHelper.defineObject(builder, "SWIMMING Skills and Ratios", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("swimming", 100.0));
        DIVING_XP = TomlConfigHelper.defineObject(builder, "DIVING Skills and Ratios", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("swimming", 150.0));
        SURFACING_XP = TomlConfigHelper.defineObject(builder, "SURFACING Skills and Ratios", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("swimming", 50.0));
        SWIM_SPRINTING_XP = TomlConfigHelper.defineObject(builder, "SWIM_SPRINTING Skills and Ratios", CodecTypes.DOUBLE_CODEC, Collections.singletonMap("swimming", 200.0));
        builder.pop();
        builder.pop();
    }

    private static void buildPartySettings(ForgeConfigSpec.Builder builder) {
        builder.comment("All settings governing party behavior").push("Party");
        PARTY_RANGE = builder.comment("How close do party members have to be to share experience.").defineInRange("Party Range", 50, 0, Integer.MAX_VALUE);
        PARTY_BONUS = builder.comment("How much bonus xp should parties earn.", "This value is multiplied by the party size.").defineInRange("Party Bonus", 1.05, 1.0, Double.MAX_VALUE);
        builder.pop();
    }

    private static void buildMobScalingSettings(ForgeConfigSpec.Builder builder) {
        builder.comment("settings related to how strong mobs get based on player level.").push("Mob_Scaling");
        MOB_SCALING_ENABLED = builder.comment("Should mob scaling be turned on.").define("Enable Mob Scaling", true);
        MOB_SCALING_AOE = builder.comment("How far should players be from spawning mobs to affect scaling?").defineInRange("Scaling AOE", 150, 0, Integer.MAX_VALUE);
        MOB_SCALING_BASE_LEVEL = builder.comment("what is the minimum level for scaling to kick in").defineInRange("Base Level", 0, 0, Integer.MAX_VALUE);
        BOSS_SCALING_RATIO = builder.comment("a multiplier on top of final scaling values that", "applies only to entities in the forge:bosses tag.").define("boss_scaling", 1.1);
        builder.comment("How should mob attributes be calculated with respect to the player's level.").push("Formula");
        MOB_USE_EXPONENTIAL_FORMULA = builder.comment("should levels be determined using an exponential formula?").define("Use Exponential Formula", true);
        builder.comment("Settings for Linear scaling configuration").push("LINEAR_LEVELS");
        MOB_LINEAR_PER_LEVEL = builder.comment("What is the xp increase per level ((level - base_level) * this)").defineInRange("Per Level", 1.0, 0.0, Double.MAX_VALUE);
        builder.pop();
        builder.comment("Settings for Exponential scaling configuration").push("EXPONENTIAL_LEVELS");
        MOB_EXPONENTIAL_POWER_BASE = builder.comment("What is the x in: (x^([Per Level] * level))").defineInRange("Power Base", 1.104088404342588, 0.0, Double.MAX_VALUE);
        MOB_EXPONENTIAL_LEVEL_MOD = builder.comment("What is the x in: ([Power Base]^(x * level))").defineInRange("Per Level", 1.0, 0.0, Double.MAX_VALUE);
        builder.pop();
        builder.pop();
        builder.comment("These settings control which skills affect scaling and the ratio for each skill", "minecraft:generic.max_health: 1 = half a heart, or 1 hitpoint", "minecraft:generic.movement_speed: 0.7 is base for most mobs.  this is added to that. so 0.7 from scaling is double speed", "minecraft:generic.attack_damage: is a multiplier of their base damage.  1 = no change, 2 = double damage", "negative values are possible and you can use this to create counterbalance skills", "", "NOTE: TOML WILL MOVE THE QUOTATIONS OF YOUR ATTRIBUTE ID AND BREAK YOUR CONFIG.", "ENSURE YOU HAVE FORCIBLY PUT YOUR QUOTES AROUND YOUR ATTRIBUTE ID BEFORE SAVING.").push("Scaling_Settings");
        MOB_SCALING = TomlConfigHelper.defineObject(builder, "Mob Scaling IDs and Ratios", Codec.unboundedMap(Codec.STRING, CodecTypes.DOUBLE_CODEC), Map.of("minecraft:generic.max_health", Map.of("combat", 0.001), "minecraft:generic.movement_speed", Map.of("combat", 1.0E-6), "minecraft:generic.attack_damage", Map.of("combat", 1.0E-4)));
        builder.pop();
        builder.pop();
    }

    private static void buildVeinMinerSettings(ForgeConfigSpec.Builder builder) {
        builder.comment("Settings related to the Vein Miner").push("Vein_Miner");
        VEIN_ENABLED = builder.comment("setting to false disables all vein features").define("vein enabled", true);
        REQUIRE_SETTING = builder.comment("If true, default consume will be ignored in favor of only allowing", "veining blocks with declared values.").define("Require Settings", false);
        DEFAULT_CONSUME = builder.comment("how much a block should consume if no setting is defined.").define("Vein Mine Default Consume", 1);
        VEIN_CHARGE_MODIFIER = builder.comment("a multiplier to all vein charge rates.").defineInRange("Vein Charge Modifier", 1.0, 0.0, Double.MAX_VALUE);
        VEIN_BLACKLIST = builder.comment("Tools in this list do not cause the vein miner to trigger").defineList("Vein_Blacklist", new ArrayList(List.of("silentgear:saw")), s -> s instanceof String);
        BASE_CHARGE_RATE = builder.comment("A constant charge rate given to all players regardless of equipment.", "Items worn will add to this amount, not replace it.").defineInRange("base charge rate", 0.01, 0.0, Double.MAX_VALUE);
        BASE_CHARGE_CAP = builder.comment("A minimum capacity given to all players regardless of equipment.", "Items worn will add to this amount, not replace it.").defineInRange("base vein capacity", 0, 0, Integer.MAX_VALUE);
        builder.pop();
    }

    static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        setupClient(CLIENT_BUILDER);
        setupCommon(COMMON_BUILDER);
        setupServer(SERVER_BUILDER);
        CLIENT_CONFIG = CLIENT_BUILDER.build();
        COMMON_CONFIG = COMMON_BUILDER.build();
        SERVER_CONFIG = SERVER_BUILDER.build();
    }
}