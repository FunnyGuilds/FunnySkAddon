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
@Name("Guild Online Deputies")
@Description("Zwraca zastępców gildii którzy są online")
@Examples(
    "loop player's guild online deputies:",
    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%loop-value%\""
)
class GuildOnlineDeputiesExpression : GuildExpression<Player>("online deputies of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildOnlineDeputiesExpression::class.java,
                Player::class.java,
                ExpressionType.PROPERTY,
                "%object%['s] [guild] online deputies",
                "%guild%['s] online deputies",
                "%string%['s] [guild] online deputies",
                "%offlineplayer%['s] guild['s] online deputies",
                "%location%['s] guild['s] online deputies",
                "%block%['s] guild['s] online deputies",
                "%object%['s] guild['s] online deputies"
            )
        }
    }

    override fun get(event: Event): Array<Player> {
        return event.getGuild(guildExpression).toStream()
            .flatMap(Guild::getOnlineMembers)
            .filter(User::isDeputy)
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