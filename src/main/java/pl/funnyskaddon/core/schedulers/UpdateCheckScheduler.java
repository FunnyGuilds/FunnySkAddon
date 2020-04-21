package pl.funnyskaddon.core.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import pl.funnyskaddon.core.FunnySkAddon;

public class UpdateCheckScheduler {

    private final FunnySkAddon plugin;

    public UpdateCheckScheduler(FunnySkAddon plugin) {
        this.plugin = plugin;
    }

    public void start() {
        PluginDescriptionFile d = plugin.getDescription();
        Bukkit.getServer().getScheduler().runTaskTimer(plugin, () -> {
            if (plugin.getConfig().getBoolean("update.check")) {
                if (!d.getVersion().equalsIgnoreCase(FunnySkAddon.getLatestVersion("https://raw.githubusercontent.com/MLGroupMC/FunnySkAddon/master/VERSION"))) {
                    Bukkit.getLogger().warning("[FSA] Wersja pluginu: " + d.getVersion());
                    Bukkit.getLogger().warning("[FSA] Najnowsza wersja pluginu: " + FunnySkAddon.getLatestVersion("https://raw.githubusercontent.com/MLGroupMC/FunnySkAddon/master/VERSION"));
                    Bukkit.getLogger().warning("[FSA] Wszystkie wersje: https://github.com/MLGroupMC/FunnySkAddon/releases/");
                }
            }
        }, 0, 216000);
    }
}
