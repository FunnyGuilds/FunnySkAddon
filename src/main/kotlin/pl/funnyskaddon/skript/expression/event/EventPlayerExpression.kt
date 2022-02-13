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
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.rank.DeathsChangeEvent
import net.dzikoysk.funnyguilds.event.rank.KillsChangeEvent
import net.dzikoysk.funnyguilds.event.rank.PointsChangeEvent
import org.bukkit.entity.Player
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
@Name("Player")
@Description("Zwraca gracza, który uczestniczył w wydarzeniu")
@Events(
    "guild delete",
    "guild delete",
    "guild ban",
    "guild unban",
    "guild move",
    "guild base change",
    "guild rename",
    "guild tag change",
    "guild enlarge",
    "guild extend validity",
    "guild lives change",
    "guild member invite",
    "guild member accept invite",
    "guild member revoke invite",
    "guild member join",
    "guild member leave",
    "guild member kick",
    "guild member leader",
    "guild member deputy",
    "guild send ally invitation",
    "guild accept ally invitation",
    "guild revoke ally invitation",
    "guild break ally",
    "guild region enter",
    "guild region leave",
    "kill change",
    "deaths change",
    "points change",
    "kills points change"
)
class EventPlayerExpression : SimpleExpression<Player>() {

    companion object {
        init {
            Skript.registerExpression(
                EventPlayerExpression::class.java,
                Player::class.java,
                ExpressionType.SIMPLE,
                *EventType.patterns.toTypedArray()
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        FUNNY("(player|doer)", FunnyEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is FunnyEvent) {
                    return event.doer.player.orNull()
                }
                return null
            }
        },

        KILLS_CHANGE("[kills( |-)change( |-)](player|doer)", KillsChangeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is KillsChangeEvent) {
                    return event.doer.player.orNull()
                }
                return null
            }
        },

        DEATHS_CHANGE("[deaths( |-)change( |-)](player|doer)", DeathsChangeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is DeathsChangeEvent) {
                    return event.doer.player.orNull()
                }
                return null
            }
        },

        POINTS_CHANGE("[points( |-)change( |-)](player|doer)", PointsChangeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is PointsChangeEvent) {
                    return event.doer.player.orNull()
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