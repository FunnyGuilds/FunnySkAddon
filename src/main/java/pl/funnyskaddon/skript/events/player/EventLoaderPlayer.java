package pl.funnyskaddon.skript.events.player;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.event.guild.GuildRegionEnterEvent;
import net.dzikoysk.funnyguilds.event.guild.GuildRegionLeaveEvent;
import org.bukkit.entity.Player;
import pl.funnyskaddon.events.rank.CustomKillPointsChangeEvent;

public class EventLoaderPlayer {

    public EventLoaderPlayer() {
        Skript.registerEvent("kill points change", SimpleEvent.class, CustomKillPointsChangeEvent.class, "kill points change");
   /*     Skript.registerExpression(KillPointsChangeKiller.class, Player.class, ExpressionType.SIMPLE,
                "killer");
        Skript.registerExpression(KillPointsChangeVictim.class, Player.class, ExpressionType.SIMPLE,
                "victim");
        Skript.registerExpression(KillPointsChangeChange.class, Integer.class, ExpressionType.SIMPLE,
                "change");*/
        Skript.registerEvent("enter guild region", SimpleEvent.class, GuildRegionEnterEvent.class, "enter guild region");
       /* Skript.registerExpression(EnterGuildRegionDoer.class, Player.class, ExpressionType.SIMPLE,
                "player");
        Skript.registerExpression(EnterGuildRegionGuild.class, Guild.class, ExpressionType.SIMPLE,
                "guild");*/
        Skript.registerEvent("leave guild region", SimpleEvent.class, GuildRegionLeaveEvent.class, "leave guild region");
     /*   Skript.registerExpression(LeaveGuildRegionDoer.class, Player.class, ExpressionType.SIMPLE,
                "player");
        Skript.registerExpression(LeaveGuildRegionGuild.class, Guild.class, ExpressionType.SIMPLE,
                "guild");*/
    }

}
