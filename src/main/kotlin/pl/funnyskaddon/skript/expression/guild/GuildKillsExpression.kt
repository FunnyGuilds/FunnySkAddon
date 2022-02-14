package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild

@FunnyDoc
@Name("Guild Kills")
@Description("Zwraca liczbę zabójstw gildii (sumę wszystkich zabójstw jej członków)")
@Examples(
    "send \"%\"\"FajnaGildia\"\" guild kills%\"",
    "set {_kills} to \"FajnaGildia\" guild kills"
)
class GuildKillsExpression : GuildExpression<Int>("kills of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildKillsExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%guild%['s] [(rank|ranking)] kills [(amount|count)]",
                "%string%['s] [(rank|ranking)] kills [(amount|count)]",
                "%offlineplayer%['s] guild['s] [(rank|ranking)] kills [(amount|count)]",
                "%location%['s] guild['s] [(rank|ranking)] kills [(amount|count)]",
                "%block%['s] guild['s] [(rank|ranking)] kills [(amount|count)]",
                "%object%['s] guild['s] [(rank|ranking)] kills [(amount|count)]"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        return event.getGuild(guildExpression)
            .map { guild -> guild.rank.averageKills }
            .orElse(0)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}