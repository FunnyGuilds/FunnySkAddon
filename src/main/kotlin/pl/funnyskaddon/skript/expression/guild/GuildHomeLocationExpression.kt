package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild Home Location")
@Description("Zwraca lokalizację bazy gildii")
@Examples(
    "send \"%\"AC4U\" guild home location%\"",
    "set {_home} to \"AC4U\" guild home location"
)
class GuildHomeLocationExpression : GuildExpression<Location>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildHomeLocationExpression::class.java,
                Location::class.java,
                ExpressionType.PROPERTY,
                "%object%['s] guild (home|base) location"
            )
        }
    }

    override fun get(event: Event): Array<Location>? {
        return event.getGuildOption(guildExpression)
            .map(Guild::getHome)
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun getReturnType(): Class<Location> {
        return Location::class.java
    }

}