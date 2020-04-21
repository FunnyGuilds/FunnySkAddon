package pl.funnyskaddon.core.data;

import org.bukkit.configuration.ConfigurationSection;
import pl.funnyskaddon.core.FunnySkAddon;

public class Config {

    private final FunnySkAddon plugin;

    public boolean updateCheck;

    public String noPerm;

    public Config(FunnySkAddon plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        ConfigurationSection config = plugin.getConfig();

        updateCheck = config.getBoolean("update.check");
        noPerm = config.getString("message.noperm");
    }

}
