package pl.funnyskaddon.core.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import pl.funnyskaddon.core.FunnySkAddon;

public abstract class UpdateCheckScheduler {
	public static void start() {
		PluginDescriptionFile d = FunnySkAddon.getInst().getDescription();
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FunnySkAddon.getInst(), new Runnable() {
			@Override
			public void run() {
				if(FunnySkAddon.getInst().getConfig().getBoolean("update.check")) {
		            if(!d.getVersion().equalsIgnoreCase(FunnySkAddon.getLatestVersion("https://raw.githubusercontent.com/MLGroupMC/FunnySkAddon/master/VERSION"))) {
		                Bukkit.getLogger().info("[FSA] Wersja pluginu: "+d.getVersion());
		                Bukkit.getLogger().info("[FSA] Najnowsza wersja pluginu: "+FunnySkAddon.getLatestVersion("https://raw.githubusercontent.com/MLGroupMC/FunnySkAddon/master/VERSION"));
		                Bukkit.getLogger().info("[FSA] Wszystkie wersje: https://github.com/MLGroupMC/FunnySkAddon/releases/");
		            }
		        }
			}
		}, 0, 216000);
	}
}
