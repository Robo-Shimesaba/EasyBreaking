package easybreaking.easybreaking;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashSet;

public class settings {
    public static HashSet<Material> logMaterials = new HashSet<>();
    public static HashSet<String> validLogMaterials =
            new HashSet<>(Arrays.asList(
                    "LOG",
                    "LOG_2",
                    "LEGACY_LOG",
                    "LEGACY_LOG_2",
                    "ACACIA_LOG",
                    "BIRCH_LOG",
                    "DARK_OAK_LOG",
                    "JUNGLE_LOG",
                    "OAK_LOG",
                    "SPRUCE_LOG",
                    "CRIMSON_STEM",
                    "WARPED_STEM"));

    public static HashSet<Material> oreMaterial = new HashSet<>();
    public static HashSet<String> validOreMaterials =
            new HashSet<>(Arrays.asList(
                    "COAL_ORE",
                    "IRON_ORE",
                    "REDSTONE_ORE",
                    "GOLD_ORE",
                    "DIAMOND_ORE",
                    "COPPER_ORE",
                    "LAPIS_ORE",
                    "EMERALD_ORE",
                    "DEEPSLATE_COAL_ORE",
                    "DEEPSLATE_IRON_ORE",
                    "DEEPSLATE_REDSTONE_ORE",
                    "DEEPSLATE_GOLD_ORE",
                    "DEEPSLATE_DIAMOND_ORE",
                    "DEEPSLATE_COPPER_ORE",
                    "DEEPSLATE_LAPIS_ORE",
                    "DEEPSLATE_EMERALD_ORE",
                    "NETHER_GOLD_ORE",
                    "NETHER_QUARTZ_ORE",
                    "ANCIENT_DEBRIS"));

    public static void initializeHashSets() {
        for (Material material : Material.values()) {
            if (validLogMaterials.contains(material.name())) {
                logMaterials.add(material);
            }
            if (validOreMaterials.contains(material.name())) {
                oreMaterial.add(material);
            }
        }
    }
}
