package pl.funnyskaddon.skript.expression.config

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.config.sections.items.GuildItemSet
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expression.FunnyExpression

abstract class GuildItemSetExpression<T> : FunnyExpression<T>() {

    companion object {
        const val DEFAULT_SET = "default"
    }

    private var setNameExpression: Expression<String>? = null

    @Suppress("UNCHECKED_CAST")
    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        setNameExpression = expression.getOrNull(0) as Expression<String>?
        return true
    }

    protected fun resolveSet(event: Event): GuildItemSet? {
        val name = setNameExpression?.getSingle(event) ?: DEFAULT_SET
        return FunnyGuilds.getInstance().itemsConfiguration.guildItemSets[name]
    }

    protected fun setNameOrDefault(event: Event?, debug: Boolean): String {
        return setNameExpression?.toString(event, debug) ?: "\"$DEFAULT_SET\""
    }

}
