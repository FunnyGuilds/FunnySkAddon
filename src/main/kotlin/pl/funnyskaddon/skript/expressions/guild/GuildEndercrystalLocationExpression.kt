package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.GuildExpression

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
        val guild = getGuild(event)

        if (guild != null) {
            return arrayOf(guild.enderCrystal)
        }

        return null
    }

    override fun getReturnType(): Class<Location> {
        return Location::class.java
    }

}