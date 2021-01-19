package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.GuildExpression
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
                "%object%(|'s) guild members"
            )
        }
    }

    override fun get(event: Event): Array<OfflinePlayer>? {
        val guild = getGuild(event)

        if (guild != null) {
            return guild.members.stream().map(User::getOfflinePlayer).collect(Collectors.toList()).toTypedArray()
        }

        return null
    }

    override fun getReturnType(): Class<OfflinePlayer> {
        return OfflinePlayer::class.java
    }

}