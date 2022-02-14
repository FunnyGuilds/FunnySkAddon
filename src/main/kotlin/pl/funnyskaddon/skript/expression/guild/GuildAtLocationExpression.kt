package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.guild.Region
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.ValueExpression
import pl.funnyskaddon.skript.getValue

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

    override fun get(event: Event): Array<Guild>? {
        return event.getValue(valueExpression)
            .flatMap(FunnyGuilds.getInstance().regionManager::findRegionAtLocation)
            .map(Region::getGuild)
            .map { value -> arrayOf(value) }
            .orNull()
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return "guild at location" + valueExpression.toString(e, debug)
    }

}