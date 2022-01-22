package pl.funnyskaddon.skript.condition.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.condition.GuildCondition
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild Can Be Attacked")
@Description("Sprawdza czy gildia może być zaatakowana")
@Examples(
    "if guild \"AC4U\" can be attacked:",
    "if guild \"AC4U\" can't be attacked:"
)
class GuildCanBeAttackedCondition : GuildCondition() {

    companion object {
        init {
            Skript.registerCondition(
                GuildCanBeAttackedCondition::class.java, "guild %object% can be attacked",
                "guild %object% (cannot|can't) be attacked"
            )
        }
    }

    override fun check(event: Event): Boolean {
        return event.getGuildOption(guildExpression)
            .map(Guild::canBeAttacked)
            .isPresent.xor(isNegated)
    }

}