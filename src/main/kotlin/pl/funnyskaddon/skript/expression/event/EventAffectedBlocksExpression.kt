package pl.funnyskaddon.skript.expression.event

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Events
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.skript.log.ErrorQuality
import pl.funnyskaddon.skript.registration.Registration.registerExpression
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.event.guild.GuildEntityExplodeEvent
import org.bukkit.block.Block
import org.bukkit.event.Event

@Name("Blocks")
@Description("Returns the blocks that exploded")
@Since("2.0.0")
@Events(
    "guild entity explode"
)
class EventAffectedBlocksExpression : SimpleExpression<Block>() {

    companion object {
        init {
            registerExpression<EventAffectedBlocksExpression, Block>(ExpressionType.SIMPLE, *EventType.patterns.toTypedArray())
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        ENTITY_EXPLODE("[affected( |-)]blocks", GuildEntityExplodeEvent::class.java) {
            override fun get(event: Event): List<Block>? {
                if (event is GuildEntityExplodeEvent) {
                    return event.affectedBlocks
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

        abstract operator fun get(event: Event): List<Block>?
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

    override fun get(event: Event): Array<Block?>? {
        for (classEvent in type.events) {
            if (classEvent.isInstance(event)) {
                return type[event]?.toTypedArray()
            }
        }
        return null
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun getReturnType(): Class<out Block> {
        return Block::class.java
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "the " + type.name + " affected blocks"
    }

}