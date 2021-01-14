package pl.funnyskaddon.skript.expressions.events

import ch.njol.skript.ScriptLoader
import ch.njol.skript.Skript
import ch.njol.skript.classes.Changer.ChangeMode
import ch.njol.skript.events.util.PlayerChatEventHandler
import ch.njol.skript.expressions.ExprMessage
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.skript.log.ErrorQuality
import ch.njol.util.Kleenean
import ch.njol.util.coll.CollectionUtils
import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.user.User
import net.dzikoysk.funnyguilds.event.guild.*
import net.dzikoysk.funnyguilds.event.guild.ally.GuildAcceptAllyInvitationEvent
import net.dzikoysk.funnyguilds.event.guild.ally.GuildBreakAllyEvent
import net.dzikoysk.funnyguilds.event.guild.ally.GuildRevokeAllyInvitationEvent
import net.dzikoysk.funnyguilds.event.guild.ally.GuildSendAllyInvitationEvent
import net.dzikoysk.funnyguilds.event.guild.member.*
import org.bukkit.event.Event
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.*
import pl.funnyskaddon.events.guilds.CustomGuildCreateEvent
import pl.funnyskaddon.skript.events.guild.GuildBreakAllianceGuild
import pl.funnyskaddon.skript.expressions.guild.GuildFromNameExpression
import pl.funnyskaddon.util.GuildUtil

class EventAlliedGuildExpression : SimpleExpression<Guild>() {

    companion object {
        init {
            Skript.registerExpression(
                EventAlliedGuildExpression::class.java,
                Guild::class.java,
                ExpressionType.SIMPLE,
                *EventType.patterns
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        SEND_ALLY_INVITATION("allied-guild|ally", GuildSendAllyInvitationEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildSendAllyInvitationEvent) {
                    return event.alliedGuild
                }
                return null
            }
        },

        ACCEPT_ALLY_INVITATION("allied-guild|ally", GuildAcceptAllyInvitationEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildAcceptAllyInvitationEvent) {
                    return event.alliedGuild
                }
                return null
            }
        },

        REVOKE_ALLY_INVITATION("allied-guild|ally", GuildRevokeAllyInvitationEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildRevokeAllyInvitationEvent) {
                    return event.alliedGuild
                }
                return null
            }
        },

        BREAK_ALLY("allied-guild|ally", GuildBreakAllyEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildBreakAllyEvent) {
                    return event.alliedGuild
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
            if(classEvent.isInstance(event)) {
                return arrayOf(type[event])
            }
        }
        return null
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String? {
        return null
    }

    override fun getReturnType(): Class<out Guild> {
        return Guild::class.java
    }

}