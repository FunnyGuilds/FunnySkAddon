package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.ValueExpression
import pl.funnyskaddon.skript.getValueOption

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

    override fun get(event: Event): Array<Guild>? {
        return event.getValueOption(valueExpression)
            .flatMap(FunnyGuilds.getInstance().guildManager::findByTag)
            .map { value -> arrayOf(value) }
            .orNull
    }

}