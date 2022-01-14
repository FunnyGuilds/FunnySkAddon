package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild Kills")
@Description("Zwraca liczbe zabójstw gildii (sume wszystkich zabójstw jej członków)")
@Examples(
    "send \"%\"\"FajnaGildia\"\" guild kills%\"",
    "set {_kills} to \"FajnaGildia\" guild kills"
)
class GuildKillsExpression : GuildExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildKillsExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild [(rank|ranking)] kills [amount|count]"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        return event.getGuildOption(guildExpression)
            .map { guild -> guild.rank.averageKills }
            .orElse(0)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}