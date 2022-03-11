package pl.funnyskaddon.skript.condition.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.condition.GuildCondition
import pl.funnyskaddon.skript.getGuild

@FunnyDoc
@Name("Guild PvP Is Enabled")
@Description("Sprawdza, czy gildia ma włączone pvp")
@Examples(
    "if guild \"AC4U\" pvp is enabled:",
    "if guild \"AC4U\" pvp is disabled:"
)
class GuildPvPIsEnabledCondition : GuildCondition(7) {

    companion object {
        init {
            Skript.registerCondition(
                GuildPvPIsEnabledCondition::class.java,
                "%guild%['s] pvp is (enabled|on)",
                "%string%['s] pvp is (enabled|on)",
                "%offlineplayer%['s] guild['s] pvp is (enabled|on)",
                "guild at %location% pvp is (enabled|on)",
                "guild at %block% pvp is (enabled|on)",
                "%object%['s] guild['s] pvp is (enabled|on)",
                "%guild%['s] pvp is (disabled|off)",
                "%string%['s] pvp is (disabled|off)",
                "%offlineplayer%['s] guild['s] pvp is (disabled|off)",
                "guild at %location% pvp is (disabled|off)",
                "guild at %block% pvp is (disabled|off)",
                "%object%['s] guild['s] pvp is (disabled|off)",
            )
        }
    }

    override fun check(event: Event): Boolean {
        return event.getGuild(guildExpression)
            .map(Guild::getPvP)
            .orElseGet(false)
            .xor(isNegated)
    }

}