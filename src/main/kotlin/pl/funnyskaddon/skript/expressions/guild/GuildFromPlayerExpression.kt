package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import panda.std.Option
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.ValueExpression

@FunnyDoc
@Name("Guild From Player")
@Description(
    "Zwraca gildie po graczu<br>",
    "Alternatywa: <b>%offlineplayer%(|'s) guild</b>"
)
@Examples("set {_guild} to guild from player")
class GuildFromPlayerExpression : ValueExpression<OfflinePlayer>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildFromPlayerExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "guild from player %offlineplayer%",
                "guild of player %offlineplayer%"
            )
        }
    }

    override fun get(event: Event): Array<Guild> {
        val userOption: Option<User> = FunnyGuilds.getInstance().userManager.findByPlayer(getValue(event))
        if (userOption.isEmpty) {
            return arrayOf()
        }
        return arrayOf(userOption.get().guild)
    }

}