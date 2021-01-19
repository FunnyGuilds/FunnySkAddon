package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.entity.Player
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.GuildExpression
import pl.funnyskaddon.util.getPlayersInGuildRegion

@FunnyDoc
@Name("Players In Guild Region")
@Description("Zwraca liste graczy znajdujących się na terenie gildii")
@Examples(
    "loop players in (location at (100, 100, 100) in world \"world\") guild region:",
    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%loop-value%\""
)
class GuildPlayersInRegionExpression : GuildExpression<Player>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildPlayersInRegionExpression::class.java,
                Player::class.java,
                ExpressionType.PROPERTY,
                "players in %object%(|'s) guild region"
            )
        }
    }

    override fun get(event: Event): Array<Player>? {
        val guild = getGuild(event)

        if (guild != null) {
            return guild.getPlayersInGuildRegion()
        }

        return null
    }

    override fun getReturnType(): Class<Player> {
        return Player::class.java
    }

}