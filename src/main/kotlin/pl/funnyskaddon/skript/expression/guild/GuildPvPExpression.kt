package pl.funnyskaddon.skript.expression.guild

import pl.funnyskaddon.skript.registration.Registration.registerExpression
import pl.funnyskaddon.extension.orNull

import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild

@Name("Guild PvP")
@Since("2.0.0")
@Description("Returns whether PvP in a guild is enabled or disabled")
@Examples(
    "send \"%\"AC4U\" guild pvp%\"",
    "set {_pvpStatus} to \"AC4U\" guild pvp"
)
class GuildPvPExpression : GuildExpression<Boolean>("pvp of") {

    companion object {
        init {
            registerExpression<GuildPvPExpression, Boolean>(
                ExpressionType.PROPERTY,
                "%guild%['s] pvp [[is] enabled]",
                "%string%['s] [guild] pvp [[is] enabled]",
                "%offlineplayer%['s] guild['s] pvp [[is] enabled]",
                "%location%['s] guild['s] pvp [[is] enabled]",
                "%block%['s] guild['s] pvp [[is] enabled]",
                "%object%['s] guild['s] pvp [[is] enabled]"
            )
        }
    }

    override fun get(event: Event): Array<Boolean>? {
        return event.getGuild(guildExpression)
            .map(Guild::hasPvPEnabled)
            .map { value -> arrayOf(value) }
            .orNull()
    }

    override fun getReturnType(): Class<Boolean> {
        return Boolean::class.javaObjectType
    }

}