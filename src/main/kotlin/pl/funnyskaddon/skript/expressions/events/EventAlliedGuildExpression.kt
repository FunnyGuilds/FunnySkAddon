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
import net.dzikoysk.funnyguilds.event.guild.ally.GuildAcceptAllyInvitationEvent
import net.dzikoysk.funnyguilds.event.guild.ally.GuildBreakAllyEvent
import net.dzikoysk.funnyguilds.event.guild.ally.GuildRevokeAllyInvitationEvent
import net.dzikoysk.funnyguilds.event.guild.ally.GuildSendAllyInvitationEvent
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
@Name("Ally")
@Description("Zwraca gildię sojuszniczą która uczestniczyła w wydarzeniu")
@Events(
    "guild send ally invitation",
    "guild accept ally invitation",
    "guild revoke ally invitation",
    "guild break ally",
)
class EventAlliedGuildExpression : SimpleExpression<Guild>() {

    companion object {
        init {
            Skript.registerExpression(
                EventAlliedGuildExpression::class.java,
                Guild::class.java,
                ExpressionType.SIMPLE,
                *EventType.patterns.toTypedArray()
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        SEND_ALLY_INVITATION("allied-guild|ally", GuildSendAllyInvitationEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildSendAllyInvitationEvent) {
                    return event.alliedGuild
                }
                return null
            }
        },

        ACCEPT_ALLY_INVITATION("allied-guild|ally", GuildAcceptAllyInvitationEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildAcceptAllyInvitationEvent) {
                    return event.alliedGuild
                }
                return null
            }
        },

        REVOKE_ALLY_INVITATION("allied-guild|ally", GuildRevokeAllyInvitationEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildRevokeAllyInvitationEvent) {
                    return event.alliedGuild
                }
                return null
            }
        },

        BREAK_ALLY("allied-guild|ally", GuildBreakAllyEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildBreakAllyEvent) {
                    return event.alliedGuild
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

        abstract operator fun get(event: Event): Guild?
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

    override fun get(event: Event): Array<Guild?>? {
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
        return "the " + type.name + " allied guild"
    }

    override fun getReturnType(): Class<out Guild> {
        return Guild::class.java
    }

}