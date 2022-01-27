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
import java.util.stream.Collectors

@FunnyDoc
@Name("Guild Members")
@Description("Zwraca członków gildii")
@Examples(
    "loop player's guild members:",
    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%loop-value%\""
)
class GuildMembersExpression : GuildExpression<OfflinePlayer>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildMembersExpression::class.java,
                OfflinePlayer::class.java,
                ExpressionType.PROPERTY,
                "%object%['s] [guild] members"
            )
        }
    }

    override fun get(event: Event): Array<OfflinePlayer> {
        return event.getGuildOption(guildExpression).toStream()
            .flatMap(Guild::getMembers)
            .map(User::getOfflinePlayer)
            .collect(Collectors.toSet())
            .toTypedArray()
    }

    override fun getReturnType(): Class<OfflinePlayer> {
        return OfflinePlayer::class.java
    }

}