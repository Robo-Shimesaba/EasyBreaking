package easybreaking.easybreaking;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static easybreaking.easybreaking.settings.logMaterials;

public class CutDownTree {

    public static void cutDownTree(Location location, ItemStack itemStack, Material material, Player player) {
        //      _______________________  _______________________
        //      |        up           |  |          up         |
        // from |left  center  right  |to|  left  center  right|
        //      |       down          |  |         down        |
        //      -----------------------  -----------------------

        if (!logMaterials.contains(material)) {
            return;
        }

        String string = itemStack.getType().toString();
        Pattern pattern = Pattern.compile("_AXE?");
        Matcher matcher = pattern.matcher(string);

        if (matcher.find()) {
            if (location.getBlock().getType() == material) {

                LinkedList<Block> blocks = new LinkedList<>();
                location.getBlock().breakNaturally(itemStack);
                Damageable damageable = (Damageable) itemStack.getItemMeta();
                damageable.setDamage(damageable.getDamage() + 1);
                itemStack.setItemMeta(damageable);
                if (itemStack.getType().getMaxDurability() - damageable.getDamage() <= 0) {
                    player.getInventory().getItemInMainHand().setAmount(0);
                    player.playSound(location, Sound.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 2, 1);
                }

                location.add(0, 1.0, 0);

                for (int i = 0; i < 13; i++) {

                    switch (i) {
                        case 0, 10 -> {
                            Location sl = location.set(location.getBlockX() + 1, location.getBlockY(), location.getBlockZ() + 1);
                            if (location.getBlock().getType() == material) {
                                blocks.add(sl.getBlock());
                            }
                        }
                        case 1, 2, 8, 7 -> {
                            Location sl = location.set(location.getBlockX() - 1, location.getBlockY(), location.getBlockZ());
                            if (location.getBlock().getType() == material) {
                                blocks.add(sl.getBlock());
                            }
                        }
                        case 3, 6 -> {
                            Location sl = location.set(location.getBlockX(), location.getBlockY(), location.getBlockZ() - 1);
                            if (location.getBlock().getType() == material) {
                                blocks.add(sl.getBlock());
                            }
                        }
                        case 4, 5 -> {
                            Location sl = location.set(location.getBlockX() + 1, location.getBlockY(), location.getBlockZ());
                            if (location.getBlock().getType() == material) {
                                blocks.add(sl.getBlock());
                            }
                        }
                        case 9 -> {
                            Location sl = location.set(location.getBlockX() + 1, location.getBlockY() - 1, location.getBlockZ());
                            if (location.getBlock().getType() == material) {
                                blocks.add(sl.getBlock());
                            }
                        }
                        case 11 -> {
                            Location sl = location.set(location.getBlockX() - 1, location.getBlockY(), location.getBlockZ() + 1);
                            if (location.getBlock().getType() == material) {
                                blocks.add(sl.getBlock());
                            }
                        }
                        case 12 -> {
                            Location sl = location.set(location.getBlockX() + 1, location.getBlockY(), location.getBlockZ() - 1);
                            if (location.getBlock().getType() == material) {
                                blocks.add(sl.getBlock());
                            }
                        }
                    }
                }

                if (!blocks.isEmpty()) {
                    for (Block block : blocks) {
                        CutDownTree.cutDownTree(block.getLocation(), itemStack, block.getType(), player);
                    }
                }
            }
        }
    }
}
