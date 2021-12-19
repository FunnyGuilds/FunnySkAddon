package pl.funnyskaddon.skript.conditions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.conditions.GuildCondition
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild Can Build")
@Description("Sprawdza czy gildia może budować")
@Examples(
    "if guild \"AC4U\" can build:",
    "if guild \"AC4U\" can't be build:"
)
class GuildCanBuildCondition : GuildCondition() {

    companion object {
        init {
            Skript.registerCondition(
                GuildCanBuildCondition::class.java, "guild %object% can build",
                "guild %object% (cannot|can't) build"
            )
        }
    }

    override fun check(event: Event): Boolean {
        return try {
            event.getGuildOption(guildExpression)
                .map(Guild::canBuild)
                .isPresent.xor(isNegated)
        } catch (ex: Exception) {
            !isNegated
        }
    }

}