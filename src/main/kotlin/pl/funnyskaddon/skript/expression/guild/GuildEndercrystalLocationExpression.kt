package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild

@FunnyDoc
@Name("Guild Endercrystal Location")
@Description("Zwraca lokalizację gildii (jej kryształu/serca)")
@Examples(
    "send \"%\"\"AC4U\"\" guild endercrystal location%\"",
    "set {_enderCrystal} to \"AC4U\" guild endercrystal location"
)
class GuildEndercrystalLocationExpression : GuildExpression<Location>("endercrystal location of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildEndercrystalLocationExpression::class.java,
                Location::class.java,
                ExpressionType.PROPERTY,
                "%guild%['s] (endercrystal|core) [location]",
                "%string%['s] [guild] (endercrystal|core) [location]",
                "%offlineplayer%['s] guild['s] (endercrystal|core) [location]",
                "%location%['s] guild['s] (endercrystal|core) [location]",
                "%block%['s] guild['s] (endercrystal|core) [location]",
                "%object%['s] guild['s] (endercrystal|core) [location]"
            )
        }
    }

    override fun get(event: Event): Array<Location>? {
        return event.getGuild(guildExpression)
            .flatMap(Guild::getEnderCrystal)
            .map { value -> arrayOf(value) }
            .orNull()
    }

    override fun getReturnType(): Class<Location> {
        return Location::class.java
    }

}