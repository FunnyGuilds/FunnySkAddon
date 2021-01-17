package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.ValueExpression

@FunnyDoc
@Name("Guild From Tag")
@Description("Zwraca gildie po tagu")
@Examples("set {_guild} to guild with tag \"AC4U\"")
class GuildFromTagExpression : ValueExpression<String>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildFromTagExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "guild from tag %string%",
                "guild with tag %string%"
            )
        }
    }

    override fun get(event: Event): Array<Guild> {
        return arrayOf(GuildUtils.getByTag(getValue(event)))
    }

}