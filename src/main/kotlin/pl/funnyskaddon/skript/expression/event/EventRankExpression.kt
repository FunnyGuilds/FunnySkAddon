package pl.funnyskaddon.skript.expression.event

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
import net.dzikoysk.funnyguilds.event.rank.DeathsChangeEvent
import net.dzikoysk.funnyguilds.event.rank.KillsChangeEvent
import net.dzikoysk.funnyguilds.event.rank.PointsChangeEvent
import net.dzikoysk.funnyguilds.user.UserRank
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.event.rank.CustomKillPointsChangeEvent

@FunnyDoc
@Name("Rank")
@Description(
    "Zwraca obiekt Rank z FunnyGuilds<br>",
    "Aktualnie niezbyt przydatne"
)
@Events(
    "kills change",
    "deaths change",
    "points change",
    "kills points change"
)
class EventRankExpression : SimpleExpression<UserRank>() {

    companion object {
        init {
            Skript.registerExpression(
                EventRankExpression::class.java,
                UserRank::class.javaObjectType,
                ExpressionType.SIMPLE,
                *EventType.patterns.toTypedArray()
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        KILLS_CHANGE("[kills( |-)]rank", KillsChangeEvent::class.java) {
            override fun get(event: Event): UserRank? {
                if (event is KillsChangeEvent) {
                    return event.affected.rank
                }
                return null
            }
        },

        DEATHS_CHANGE("[deaths( |-)]rank", DeathsChangeEvent::class.java) {
            override fun get(event: Event): UserRank? {
                if (event is DeathsChangeEvent) {
                    return event.affected.rank
                }
                return null
            }
        },

        POINTS_CHANGE("[points( |-)]rank", PointsChangeEvent::class.java) {
            override fun get(event: Event): UserRank? {
                if (event is PointsChangeEvent) {
                    return event.affected.rank
                }
                return null
            }
        },

        KILL_POINTS_CHANGE("kill( |-)[points( |-)]rank", CustomKillPointsChangeEvent::class.java) {
            override fun get(event: Event): UserRank? {
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

        abstract operator fun get(event: Event): UserRank?
    }

    private lateinit var type: EventType

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        type = EventType.values()[matchedPattern]
        if (!EventExpressionUtil.isCurrentEvent(*type.events)) {
            Skript.error(
                "The '" + type.pattern + "' can only be used in a " + type.name + " event",
                ErrorQuality.SEMANTIC_ERROR
            )
            return false
        }
        return true
    }

    override fun get(event: Event): Array<UserRank?>? {
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

    override fun getReturnType(): Class<out UserRank> {
        return UserRank::class.javaObjectType
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "the " + type.name + " rank"
    }

}