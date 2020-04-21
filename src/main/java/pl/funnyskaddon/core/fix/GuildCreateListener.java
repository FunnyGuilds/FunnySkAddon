package pl.funnyskaddon.core.fix;

import net.dzikoysk.funnyguilds.event.guild.GuildCreateEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.funnyskaddon.core.FunnySkAddon;

public class GuildCreateListener implements Listener {

    private final FunnySkAddon plugin;

    public GuildCreateListener(FunnySkAddon plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onGuildCreate(final GuildCreateEvent e) {
        Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {
            final GuildCreateEventFix event = new GuildCreateEventFix(e.getDoer().getPlayer(), e.getTag());
            Bukkit.getServer().getPluginManager().callEvent(event);
        }, 2);
    }

}
