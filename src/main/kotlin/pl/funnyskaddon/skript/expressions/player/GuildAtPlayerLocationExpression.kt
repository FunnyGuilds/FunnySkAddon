package pl.funnyskaddon.skript.expressions.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.guild.Region
import org.bukkit.entity.Player
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.PlayerExpression
import pl.funnyskaddon.skript.getPlayerOption

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
        return event.getPlayerOption(playerExpression)
            .map(Player::getLocation)
            .flatMap(FunnyGuilds.getInstance().regionManager::findRegionAtLocation)
            .map(Region::getGuild)
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun getReturnType(): Class<Guild> {
        return Guild::class.java
    }

}