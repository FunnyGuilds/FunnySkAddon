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
@Name("Guild Can Build")
@Description("Sprawdza, czy gildia może budować")
@Examples(
    "if guild \"AC4U\" can build:",
    "if guild \"AC4U\" can't be build:"
)
class GuildCanBuildCondition : GuildCondition(7) {

    companion object {
        init {
            Skript.registerCondition(
                GuildCanBuildCondition::class.java,
                "%guild% can build",
                "%string% [guild] can build",
                "%offlineplayer%['s] guild can build",
                "guild at %location% can build",
                "guild at %block% can build",
                "%object%['s] guild can build",
                "%guild% (can't|cannot|can not) build",
                "%string% [guild] (can't|cannot|can not) build",
                "%offlineplayer%['s] guild (can't|cannot|can not) build",
                "guild at %location% (can't|cannot|can not) build",
                "guild at %block% (can't|cannot|can not) build",
                "%object%['s] guild (can't|cannot|can not) build",
            )
        }
    }

    override fun check(event: Event): Boolean {
        return event.getGuild(guildExpression)
            .map(Guild::canBuild)
            .orElseGet(false)
            .xor(isNegated)
    }

}