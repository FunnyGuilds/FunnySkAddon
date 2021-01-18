package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.Guild
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.ValueExpression
import pl.funnyskaddon.util.getGuildAtLocation

@FunnyDoc
@Name("Guild At Location")
@Description("Zwraca gildie w danej lokalizacji")
@Examples("set {_guild} to guild at player's location")
class GuildAtLocationExpression : ValueExpression<Location>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildAtLocationExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "guild at [location] %location%"
            )
        }
    }

    override fun get(event: Event): Array<out Guild>? {
        val location: Location? = getValue(event)

        if (location != null) {
            return arrayOf(location.getGuildAtLocation()!!)
        }

        return null
    }

}