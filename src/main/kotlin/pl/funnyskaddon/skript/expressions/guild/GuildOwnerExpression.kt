package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.GuildExpression

@FunnyDoc
@Name("Guild Owner")
@Description("Zwraca właściciela gildii")
@Examples("send \"Właściciel gildii gracza %player%: %player's guild guild%\"")
class GuildOwnerExpression : GuildExpression<OfflinePlayer>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildOwnerExpression::class.java,
                OfflinePlayer::class.java,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild (owner|leader)"
            )
        }
    }

    override fun get(event: Event): Array<OfflinePlayer>? {
        val guild = getGuild(event)

        if (guild != null) {
            return arrayOf(guild.owner.offlinePlayer)
        }

        return null
    }

    override fun getReturnType(): Class<OfflinePlayer> {
        return OfflinePlayer::class.java
    }

}