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
import net.dzikoysk.funnyguilds.event.rank.*
import org.bukkit.entity.Player
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.extension.getPlayerOption

@FunnyDoc
@Name("Player")
@Description("Zwraca gracza, który uczestniczył w wydarzeniu")
@Events(
    "kills change",
    "deaths change",
    "assists change",
    "logouts change",
    "points change"
)
class EventAffectedPlayerExpression : SimpleExpression<Player>() {

    companion object {
        init {
            Skript.registerExpression(
                EventAffectedPlayerExpression::class.java,
                Player::class.java,
                ExpressionType.SIMPLE,
                *EventType.patterns.toTypedArray()
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        KILLS_CHANGE("[kills( |-)change( |-)]affected( |-)player", KillsChangeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is KillsChangeEvent) {
                    return event.affected.getPlayerOption().orNull()
                }
                return null
            }
        },

        DEATHS_CHANGE("[deaths( |-)change( |-)]affected( |-)player", DeathsChangeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is DeathsChangeEvent) {
                    return event.affected.getPlayerOption().orNull()
                }
                return null
            }
        },

        ASSISTS_CHANGE("[assists( |-)change( |-)]affected( |-)player", AssistsChangeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is AssistsChangeEvent) {
                    return event.affected.getPlayerOption().orNull()
                }
                return null
            }
        },

        LOGOUTS_CHANGE("[logouts( |-)change( |-)]affected( |-)player", LogoutsChangeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is LogoutsChangeEvent) {
                    return event.affected.getPlayerOption().orNull()
                }
                return null
            }
        },

        POINTS_CHANGE("[points( |-)change( |-)]affected( |-)player", PointsChangeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is PointsChangeEvent) {
                    return event.affected.getPlayerOption().orNull()
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

        abstract operator fun get(event: Event): Player?
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

    override fun get(event: Event): Array<Player?>? {
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

    override fun getReturnType(): Class<out Player> {
        return Player::class.java
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "the " + type.name + " doer"
    }

}