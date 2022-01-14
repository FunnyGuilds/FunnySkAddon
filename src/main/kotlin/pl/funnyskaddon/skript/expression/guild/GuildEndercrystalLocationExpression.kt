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
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild Endercrystal Location")
@Description("Zwraca lokalizację gildi (jej kryształu/serca)")
@Examples(
    "send \"%\"\"AC4U\"\" guild endercrystal location%\"",
    "set {_enderCrystal} to \"AC4U\" guild endercrystal location"
)
class GuildEndercrystalLocationExpression : GuildExpression<Location>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildEndercrystalLocationExpression::class.java,
                Location::class.java,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild [endercrystal|core] location"
            )
        }
    }

    override fun get(event: Event): Array<Location>? {
        return event.getGuildOption(guildExpression)
            .flatMap(Guild::getEnderCrystal)
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun getReturnType(): Class<Location> {
        return Location::class.java
    }

}