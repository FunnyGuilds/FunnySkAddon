package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild

@FunnyDoc
@Name("Guild PvP")
@Description("Zwraca czy pvp w gildii jest włączone czy wyłączone")
@Examples(
    "send \"%\"AC4U\" guild pvp%\"",
    "set {_pvpStatus} to \"AC4U\" guild pvp"
)
class GuildPvPExpression : GuildExpression<Boolean>("pvp of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildPvPExpression::class.java,
                Boolean::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%guild%['s] pvp [[is] enabled]",
                "%string%['s] pvp [[is] enabled]",
                "%offlineplayer%['s] guild['s] pvp [[is] enabled]",
                "%location%['s] guild['s] pvp [[is] enabled]",
                "%block%['s] guild['s] pvp [[is] enabled]",
                "%object%['s] guild['s] pvp [[is] enabled]"
            )
        }
    }

    override fun get(event: Event): Array<Boolean>? {
        return event.getGuild(guildExpression)
            .map(Guild::getPvP)
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun getReturnType(): Class<Boolean> {
        return Boolean::class.javaObjectType
    }

}