package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.GuildExpression
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild KDR")
@Description("Zwraca KDR gildii")
@Examples(
    "send \"%\"\"FajnaGildia\"\" guild kdr%\"",
    "set {_kdr} to \"FajnGildia\" guild kdr"
)
class GuildKDRExpression : GuildExpression<Float>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildKDRExpression::class.java,
                Float::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild [(rank|ranking)] kdr"
            )
        }
    }

    override fun get(event: Event): Array<Float> {
        return event.getGuildOption(guildExpression)
            .map { guild -> guild.rank.kdr }
            .orElse(0F)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Float> {
        return Float::class.javaObjectType
    }

}