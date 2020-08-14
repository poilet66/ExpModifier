package me.poilet66.expmodifier;

import org.bukkit.plugin.java.JavaPlugin;

public class ExpModifier extends JavaPlugin {

    public void onEnable() {
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        getLogger().info("Enabled.");
    }

    public void onDisable() {
        getLogger().info("Disabled.");
    }

}
