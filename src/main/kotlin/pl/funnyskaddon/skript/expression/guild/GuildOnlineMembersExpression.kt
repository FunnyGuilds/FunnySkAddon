package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.entity.Player
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuildOption
import java.util.stream.Collectors

@FunnyDoc
@Name("Guild Online Members")
@Description("Zwraca członków gildii, którzy są online")
@Examples(
    "loop player's guild online members:",
    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%loop-value%\""
)
class GuildOnlineMembersExpression : GuildExpression<Player>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildOnlineMembersExpression::class.java,
                Player::class.java,
                ExpressionType.PROPERTY,
                "%object%['s] guild online members"
            )
        }
    }

    override fun get(event: Event): Array<Player> {
        return event.getGuildOption(guildExpression).toStream()
            .flatMap(Guild::getOnlineMembers)
            .map(User::getPlayer)
            .collect(Collectors.toSet())
            .toTypedArray()
    }

    override fun getReturnType(): Class<Player> {
        return Player::class.java
    }

}