package pl.funnyskaddon.skript.expressions.events

import ch.njol.skript.ScriptLoader
import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Events
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.skript.log.ErrorQuality
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.basic.rank.Rank
import net.dzikoysk.funnyguilds.event.rank.DeathsChangeEvent
import net.dzikoysk.funnyguilds.event.rank.KillsChangeEvent
import net.dzikoysk.funnyguilds.event.rank.PointsChangeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.events.rank.CustomKillPointsChangeEvent

@FunnyDoc
@Name("Rank")
@Description(
    "Zwraca obiekt Rank z FunnyGuilds.",
    "Aktualnie niezbyt przydatne."
)
@Events(
    "kills change",
    "deaths change",
    "points change",
    "kills points change"
)
class EventRankExpression : SimpleExpression<Rank>() {

    companion object {
        init {
            Skript.registerExpression(
                EventRankExpression::class.java,
                Rank::class.javaObjectType,
                ExpressionType.SIMPLE,
                *EventType.patterns.toTypedArray()
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        KILLS_CHANGE("[kills( |-)]rank", KillsChangeEvent::class.java) {
            override fun get(event: Event): Rank? {
                if (event is KillsChangeEvent) {
                    return event.rank
                }
                return null
            }
        },

        DEATHS_CHANGE("[deaths( |-)]rank", DeathsChangeEvent::class.java) {
            override fun get(event: Event): Rank? {
                if (event is DeathsChangeEvent) {
                    return event.rank
                }
                return null
            }
        },

        POINTS_CHANGE("[points( |-)]rank", PointsChangeEvent::class.java) {
            override fun get(event: Event): Rank? {
                if (event is PointsChangeEvent) {
                    return event.rank
                }
                return null
            }
        },

        KILL_POINTS_CHANGE("kill( |-)[points( |-)]rank", CustomKillPointsChangeEvent::class.java) {
            override fun get(event: Event): Rank? {
                if (event is CustomKillPointsChangeEvent) {
                    return event.rank
                }
                return null
            }
        };

        init {
            this.pattern = "[the] $pattern"
        }

        companion object {
            val patterns = mutableSetOf<String>()

            init {
                for (value in values()) {
                    patterns.add(value.pattern)
                }
            }
        }

        abstract operator fun get(event: Event): Rank?
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

    override fun get(event: Event): Array<Rank?>? {
        for (classEvent in type.events) {
            if (classEvent.isInstance(event)) {
                return arrayOf(type[event])
            }
        }
        return null
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "the " + type.name + " rank"
    }

    override fun getReturnType(): Class<out Rank> {
        return Rank::class.javaObjectType
    }

}