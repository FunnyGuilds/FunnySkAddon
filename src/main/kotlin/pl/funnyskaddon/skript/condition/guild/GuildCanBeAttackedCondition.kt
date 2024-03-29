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
@Name("Guild Can Be Attacked")
@Description("Sprawdza, czy gildia może zostać zaatakowana")
@Examples(
    "if guild \"AC4U\" can be attacked:",
    "if guild \"AC4U\" can't be attacked:"
)
class GuildCanBeAttackedCondition : GuildCondition(7) {

    companion object {
        init {
            Skript.registerCondition(
                GuildCanBeAttackedCondition::class.java,
                "%guild% can be attacked",
                "%string% [guild] can be attacked",
                "%offlineplayer%['s] guild can be attacked",
                "guild at %location% can be attacked",
                "guild at %block% can be attacked",
                "%object%['s] guild can be attacked",
                "%guild% (cannot|can't) be attacked",
                "%string% [guild] (cannot|can't) be attacked",
                "%offlineplayer%['s] guild (cannot|can't) be attacked",
                "guild at %location% (cannot|can't) be attacked",
                "guild at %block% (cannot|can't) be attacked",
                "%object%['s] guild (cannot|can't) be attacked",
            )
        }
    }

    override fun check(event: Event): Boolean {
        return event.getGuild(guildExpression)
            .map(Guild::canBeAttacked)
            .orElseGet(false)
            .xor(isNegated)
    }

}