package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild Owner")
@Description("Zwraca właściciela gildii")
@Examples(
    "send \"%player's guild owner%\"",
    "set {_owner} to player's guild owner"
)
class GuildOwnerExpression : GuildExpression<OfflinePlayer>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildOwnerExpression::class.java,
                OfflinePlayer::class.java,
                ExpressionType.PROPERTY,
                "%guild%['s] (owner|leader)",
                "%string%['s] (owner|leader)",
                "%offlineplayer%['s] guild['s] (owner|leader)",
                "%location%['s] guild['s] (owner|leader)",
                "%block%['s] guild['s] (owner|leader)",
                "%object%['s] guild['s] (owner|leader)",
            )
        }
    }

    override fun get(event: Event): Array<OfflinePlayer>? {
        return event.getGuildOption(guildExpression)
            .map(Guild::getOwner)
            .map(User::getOfflinePlayer)
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun getReturnType(): Class<OfflinePlayer> {
        return OfflinePlayer::class.java
    }

}