package me.poilet66.expmodifier;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

public class EventListener implements Listener {

    private final Plugin main;
    private final double incrementPerLevelLooting;
    private final double incrementPerLevelFortune;

    public EventListener(Plugin main) {
        this.main = main;
        this.incrementPerLevelLooting = main.getConfig().getDouble("incrementLooting");
        this.incrementPerLevelFortune = main.getConfig().getDouble("incrementFortune");
    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player player = event.getEntity().getKiller();
            if(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) != 0) {
                int lootingLevel = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);
                double droppedExp = event.getDroppedExp();
                droppedExp *= (1 + (incrementPerLevelLooting * lootingLevel));
                event.setDroppedExp((int) droppedExp);
            }
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if(event.getPlayer() != null) {
            Player player = event.getPlayer();
            int fortuneLevel = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
            double droppedExp = event.getExpToDrop();
            droppedExp *= (1 + (incrementPerLevelFortune * fortuneLevel));
            event.setExpToDrop((int) droppedExp);
        }
    }

}
