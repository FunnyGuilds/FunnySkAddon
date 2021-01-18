package pl.funnyskaddon.skript.expressions.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.PlayerExpression
import pl.funnyskaddon.util.getGuildAtLocation

@FunnyDoc
@Name("Guild At Player Location")
@Description("Zwraca gildie znajdującą się w lokalizacji gracza (Gildia na której terenie znajduje sie gracz)")
@Examples(
    "send \"%guild at player's location%\"",
    "set {_guild} to guild at player's location"
)
class GuildAtPlayerLocationExpression : PlayerExpression<Guild>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildAtPlayerLocationExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "guild at %offlineplayer%(|'s) location"
            )
        }
    }

    override fun get(event: Event): Array<Guild>? {
        val player = getPlayer(event)

        if (player != null) {
            return arrayOf(player.location.getGuildAtLocation()!!)
        }

        return null
    }

    override fun getReturnType(): Class<Guild> {
        return Guild::class.java
    }

}