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
import net.dzikoysk.funnyguilds.event.guild.GuildChatEvent
import net.dzikoysk.funnyguilds.event.guild.GuildPreChatEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
@Name("Chat Type")
@Description(
    "Zwraca typ wiadomości na chacie gildyjnym: PRIVATE, ALLY, ALL"
)
@Events(
    "guild pre chat event",
    "guild chat event"
)
class EventChatTypeExpression : SimpleExpression<GuildChatEvent.Type>() {

    companion object {
        init {
            Skript.registerExpression(
                EventChatTypeExpression::class.java,
                GuildChatEvent.Type::class.java,
                ExpressionType.SIMPLE,
                *EventType.patterns.toTypedArray()
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        PRE_CHAT("[chat( |-)]type", GuildPreChatEvent::class.java) {
            override fun get(event: Event): GuildChatEvent.Type? {
                if (event is GuildPreChatEvent) {
                    return event.type
                }
                return null
            }
        },

        CHAT("[chat( |-)]type", GuildChatEvent::class.java) {
            override fun get(event: Event): GuildChatEvent.Type? {
                if (event is GuildChatEvent) {
                    return event.type
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

        abstract operator fun get(event: Event): GuildChatEvent.Type?
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

    override fun get(event: Event): Array<GuildChatEvent.Type?>? {
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

    override fun getReturnType(): Class<out GuildChatEvent.Type> {
        return GuildChatEvent.Type::class.java
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "the " + type.name + " type"
    }

}