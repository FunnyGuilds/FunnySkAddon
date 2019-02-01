package pl.funnyskaddon.core.schedulers;

import org.bukkit.Bukkit;

import pl.funnyskaddon.core.FunnySkAddon;
import pl.funnyskaddon.core.utils.TopUtil;

public abstract class TopUpdateScheduler implements Runnable{
	
	private static int ref = FunnySkAddon.getInst().getConfig().getInt("top.autoupdate.time")*20;
	
	public static void start() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FunnySkAddon.getInst(), new Runnable() {
			@Override
			public void run() {
				TopUtil.update();
			}
		}, 0, ref);
	}
}
