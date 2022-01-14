package pl.funnyskaddon.skript.expression.event

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
import net.dzikoysk.funnyguilds.event.guild.GuildBaseChangeEvent
import net.dzikoysk.funnyguilds.event.guild.GuildMoveEvent
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
@Name("Location")
@Description(
    "Zwraca lokalizację"
)
@Events(
    "guild move",
    "guild base change"
)
class EventLocationExpression : SimpleExpression<Location>() {

    companion object {
        init {
            Skript.registerExpression(
                EventLocationExpression::class.java,
                Location::class.java,
                ExpressionType.SIMPLE,
                *EventType.patterns.toTypedArray()
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        MOVE("[new( |-)]location", GuildMoveEvent::class.java) {
            override fun get(event: Event): Location? {
                if (event is GuildMoveEvent) {
                    return event.newLocation
                }
                return null
            }
        },

        BASE_CHANGE("[new( |-)][base( |-)]]location", GuildBaseChangeEvent::class.java) {
            override fun get(event: Event): Location? {
                if (event is GuildBaseChangeEvent) {
                    return event.newBaseLocation
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

        abstract operator fun get(event: Event): Location?
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

    override fun get(event: Event): Array<Location?>? {
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
        return "the " + type.name + " location"
    }

    override fun getReturnType(): Class<out Location> {
        return Location::class.java
    }

}