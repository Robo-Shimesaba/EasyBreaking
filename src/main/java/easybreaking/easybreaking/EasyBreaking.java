package easybreaking.easybreaking;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import static easybreaking.easybreaking.settings.*;

public final class EasyBreaking extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        validLogMaterials = new HashSet<>(getConfig().getStringList("cutLogMaterials"));
        validOreMaterials = new HashSet<>(getConfig().getStringList("oreMaterial"));
        initializeHashSets();

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
        PermissionAttachment attachment = player.addAttachment(this);
        perms.put(player.getUniqueId(), attachment);
        PermissionAttachment pperms = perms.get(player.getUniqueId());

        if (command.getName().equalsIgnoreCase("CutAll")) {
            pperms.setPermission("CutAll", !player.hasPermission("CutAll"));
            player.sendMessage("CutAll is " + player.hasPermission("CutAll"));
        }

        if (command.getName().equalsIgnoreCase("MineAll")) {
            pperms.setPermission("MineAll", !player.hasPermission("MineAll"));
            player.sendMessage("MineAll is " + player.hasPermission("MineAll"));
        }

        return true;
    }

    @EventHandler
    public void BlockBreaking(BlockBreakEvent e) {

        if (e.getPlayer().hasPermission("MineAll")) {
            oreMiner.OreMiner(e.getBlock().getLocation(), e.getPlayer().getInventory().getItemInMainHand(), e.getBlock().getType(), e.getPlayer());
        }
        if (e.getPlayer().hasPermission("CutAll")) {
            CutDownTree.cutDownTree(e.getBlock().getLocation(), e.getPlayer().getInventory().getItemInMainHand(), e.getBlock().getType(), e.getPlayer());
        }
    }
}
