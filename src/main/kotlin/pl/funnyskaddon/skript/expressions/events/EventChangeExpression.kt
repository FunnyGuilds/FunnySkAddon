package pl.funnyskaddon.skript.expressions.events

import ch.njol.skript.ScriptLoader
import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.skript.log.ErrorQuality
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.event.rank.DeathsChangeEvent
import net.dzikoysk.funnyguilds.event.rank.KillsChangeEvent
import net.dzikoysk.funnyguilds.event.rank.PointsChangeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.events.rank.CustomKillPointsChangeEvent

class EventChangeExpression : SimpleExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                EventChangeExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.SIMPLE,
                *EventType.patterns
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        KILLS_CHANGE("[kills( |-)]change", KillsChangeEvent::class.java) {
            override fun get(event: Event): Int? {
                if(event is KillsChangeEvent) {
                    return event.change
                }
                return null
            }
        },

        DEATHS_CHANGE("[deaths( |-)]change", DeathsChangeEvent::class.java) {
            override fun get(event: Event): Int? {
                if(event is DeathsChangeEvent) {
                    return event.change
                }
                return null
            }
        },

        POINTS_CHANGE("[points( |-)]change", PointsChangeEvent::class.java) {
            override fun get(event: Event): Int? {
                if(event is PointsChangeEvent) {
                    return event.change
                }
                return null
            }
        },

        KILL_POINTS_CHANGE("kill( |-)[points( |-)]change", CustomKillPointsChangeEvent::class.java) {
            override fun get(event: Event): Int? {
                if(event is CustomKillPointsChangeEvent) {
                    return event.change
                }
                return null
            }
        };

        init {
            this.pattern = "[the] $pattern"
        }

        companion object {
            val patterns: Array<String?> = arrayOfNulls(values().size)

            init {
                for (i in patterns.indices) patterns[i] =
                    values()[i].pattern
            }
        }

        abstract operator fun get(event: Event): Int?
    }

    private lateinit var type: EventType

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        type = EventType.values()[matchedPattern]
        if (!ScriptLoader.isCurrentEvent(*type.events)) {
            Skript.error(
                "The " + type.name + " message can only be used in a " + type.name + " event",
                ErrorQuality.SEMANTIC_ERROR
            )
            return false
        }
        return true
    }

    override fun get(event: Event): Array<Int?>? {
        for (classEvent in type.events) {
            if(classEvent.isInstance(event)) {
                return arrayOf(type[event])
            }
        }
        return null
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "the " + type.name + " change"
    }

    override fun getReturnType(): Class<out Int> {
        return Int::class.javaObjectType
    }

}