package me.dzze.gameframework.listeners;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.commands.SetCrystalBlue;
import me.dzze.gameframework.commands.SetCrystalPurple;
import me.dzze.gameframework.commands.SetCrystalRed;
import me.dzze.gameframework.commands.SetCrystalWhite;
import me.dzze.gameframework.managers.GameManager;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.concurrent.TimeUnit;

public class CrystalListener implements Listener {
    Main main;

    public CrystalListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void on(EntityDamageByEntityEvent e) {
        final Entity entity = e.getEntity();
        if (entity instanceof EnderCrystal) {
            final long elapsedMillis = System.currentTimeMillis() - GameManager.getStartTime();
            final Player player = (Player) e.getDamager();
            if (elapsedMillis < TimeUnit.MINUTES.toMillis(3L)) {
                MessageUtils.message(player, "&cYou cannot break upgrade crystals yet! Time remaining: " + TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MINUTES.toMillis(3L) - elapsedMillis) + " seconds");
                e.setCancelled(true);
            }
            if (SetCrystalBlue.isSetting.contains(player)) {
                main.getConfig().set("CrystalBlue", e.getEntity().getLocation());
                e.setCancelled(true);
                SetCrystalBlue.isSetting.remove(player);
                MessageUtils.message(player, "&bSet the location of the crystal.");
            }
            if (SetCrystalRed.isSetting.contains(player)) {
                main.getConfig().set("CrystalRed", e.getEntity().getLocation());
                e.setCancelled(true);
                SetCrystalRed.isSetting.remove(player);
                MessageUtils.message(player, "&bSet the location of the crystal.");
            }
            if (SetCrystalWhite.isSetting.contains(player)) {
                main.getConfig().set("CrystalWhite", e.getEntity().getLocation());
                e.setCancelled(true);
                SetCrystalWhite.isSetting.remove(player);
                MessageUtils.message(player, "&bSet the location of the crystal.");
            }
            if (SetCrystalPurple.isSetting.contains(player)) {
                main.getConfig().set("CrystalPurple", e.getEntity().getLocation());
                e.setCancelled(true);
                SetCrystalPurple.isSetting.remove(player);
                MessageUtils.message(player, "&bSet the location of the crystal.");
            }
        }
    }
}
