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
import net.dzikoysk.funnyguilds.event.guild.GuildPreRenameEvent
import net.dzikoysk.funnyguilds.event.guild.GuildRenameEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
@Name("Name")
@Description(
    "Zwraca nazwę gildii przed lub po jej zmianie"
)
@Events(
    "guild rename"
)
class EventNameExpression : SimpleExpression<String>() {

    companion object {
        init {
            Skript.registerExpression(
                EventNameExpression::class.java,
                String::class.javaObjectType,
                ExpressionType.SIMPLE,
                *EventType.patterns.toTypedArray()
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        RENAME_NEW("new( |-)name", GuildPreRenameEvent::class.java) {
            override fun get(event: Event): String? {
                if (event is GuildRenameEvent) {
                    return event.newName
                }
                return null
            }
        },

        RENAME_OLD("old( |-)name", GuildPreRenameEvent::class.java) {
            override fun get(event: Event): String? {
                if (event is GuildRenameEvent) {
                    return event.oldName
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

        abstract operator fun get(event: Event): String?
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
                "The '" + type.pattern + "' can only be used in a " + type.name + " event",
                ErrorQuality.SEMANTIC_ERROR
            )
            return false
        }
        return true
    }

    override fun get(event: Event): Array<String?>? {
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
        return "the " + type.name + " new name"
    }

    override fun getReturnType(): Class<out String> {
        return String::class.javaObjectType
    }

}