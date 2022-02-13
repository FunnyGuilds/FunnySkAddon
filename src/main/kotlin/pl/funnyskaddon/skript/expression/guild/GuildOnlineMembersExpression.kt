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
import pl.funnyskaddon.skript.getGuild
import java.util.stream.Collectors

@FunnyDoc
@Name("Guild Online Members")
@Description("Zwraca członków gildii, którzy są online")
@Examples(
    "loop player's guild online members:",
    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%loop-value%\""
)
class GuildOnlineMembersExpression : GuildExpression<Player>("online members of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildOnlineMembersExpression::class.java,
                Player::class.java,
                ExpressionType.PROPERTY,
                "%guild%['s] online members",
                "%string%['s] online members",
                "%offlineplayer%['s] guild['s] online members",
                "%location%['s] guild['s] online members",
                "%block%['s] guild['s] online members",
                "%object%['s] guild['s] online members"
            )
        }
    }

    override fun get(event: Event): Array<Player> {
        return event.getGuild(guildExpression).toStream()
            .flatMap(Guild::getOnlineMembers)
            .flatMap(User::getPlayer)
            .collect(Collectors.toSet())
            .toTypedArray()
    }

    override fun isSingle(): Boolean {
        return false
    }

    override fun getReturnType(): Class<Player> {
        return Player::class.java
    }

}