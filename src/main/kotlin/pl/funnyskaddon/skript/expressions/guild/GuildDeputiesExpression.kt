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
@Name("Guild Deputies")
@Description("Zwraca zastępców gildii")
@Examples(
    "player's guild deputies:",
    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%loop-value%\""
)
class GuildDeputiesExpression : GuildExpression<OfflinePlayer>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildDeputiesExpression::class.java,
                OfflinePlayer::class.java,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild deputies"
            )
        }
    }

    override fun get(event: Event): Array<OfflinePlayer>? {
        val guild = getGuild(event)

        if (guild != null) {
            return guild.deputies.stream().map(User::getOfflinePlayer).collect(Collectors.toList()).toTypedArray()
        }

        return null
    }

    override fun getReturnType(): Class<OfflinePlayer> {
        return OfflinePlayer::class.java
    }

}