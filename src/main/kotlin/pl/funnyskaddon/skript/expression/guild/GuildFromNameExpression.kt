package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.ValueExpression
import pl.funnyskaddon.skript.getValue

@FunnyDoc
@Name("Guild From Name")
@Description("Zwraca gildie po nazwie")
@Examples("set {_guild} to guild with name \"FajnaGildia\"")
class GuildFromNameExpression : ValueExpression<String>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildFromNameExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "guild from name %string%",
                "guild with name %string%"
            )
        }
    }

    override fun get(event: Event): Array<Guild>? {
        return event.getValue(valueExpression)
            .flatMap(FunnyGuilds.getInstance().guildManager::findByName)
            .map { value -> arrayOf(value) }
            .orNull()
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return "guild from name " + valueExpression.toString(e, debug)
    }

}