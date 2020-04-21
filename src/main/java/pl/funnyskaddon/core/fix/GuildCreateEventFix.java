package pl.funnyskaddon.core.fix;

import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GuildCreateEventFix extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Guild guild;

    public GuildCreateEventFix(Player p, String tag) {
        this.player = p;
        this.guild = GuildUtils.getByTag(tag);
    }

    public Player getPlayer() {
        return player;
    }

    public Guild getGuild() {
        return guild;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}