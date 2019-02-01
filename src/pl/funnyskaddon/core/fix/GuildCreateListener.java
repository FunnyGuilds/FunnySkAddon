package pl.funnyskaddon.core.fix;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.dzikoysk.funnyguilds.event.guild.GuildCreateEvent;
import pl.funnyskaddon.core.FunnySkAddon;

public class GuildCreateListener implements Listener{
	
	@EventHandler
	public void onGuildCreate(final GuildCreateEvent e) {
		Bukkit.getServer().getScheduler().runTaskLater(FunnySkAddon.getInst(), new Runnable() {
			@Override
			public void run() {
				final GuildCreateEventFix event = new GuildCreateEventFix(e.getDoer().getPlayer(), e.getTag());
				Bukkit.getServer().getPluginManager().callEvent(event);
			}
		}, 2);
	}
	
}
