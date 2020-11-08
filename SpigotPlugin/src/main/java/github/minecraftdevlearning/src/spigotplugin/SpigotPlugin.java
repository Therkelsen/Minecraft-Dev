package github.minecraftdevlearning.src.spigotplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class SpigotPlugin extends JavaPlugin {

    String playerName;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin started");
        // Register our command "kit" (set an instance of your command class as executor)
        this.getCommand("kit").setExecutor(new CommandKit());
        getServer().getPluginManager().registerEvents(new MyListener(playerName), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Plugin stopped");
    }
}
