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
@Name("Guild KDR")
@Description("Zwraca KDR gildii")
@Examples(
    "send \"%\"\"FajnaGildia\"\" guild kdr%\"",
    "set {_kdr} to \"FajnGildia\" guild kdr"
)
class GuildKDRExpression : GuildExpression<Float>("kdr of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildKDRExpression::class.java,
                Float::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%guild%['s] [(rank|ranking)] kdr",
                "%string%['s] [guild] [(rank|ranking)] kdr",
                "%offlineplayer%['s] guild['s] [(rank|ranking)] kdr",
                "%location%['s] guild['s] [(rank|ranking)] kdr",
                "%block%['s] guild['s] [(rank|ranking)] kdr",
                "%object%['s] guild['s] [(rank|ranking)] kdr"
            )
        }
    }

    override fun get(event: Event): Array<Float> {
        return event.getGuild(guildExpression)
            .map { guild -> guild.rank.kdr }
            .orElse(0F)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Float> {
        return Float::class.javaObjectType
    }

}