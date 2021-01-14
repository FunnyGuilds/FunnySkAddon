package pl.funnyskaddon.skript.expressions.events

import ch.njol.skript.ScriptLoader
import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.skript.log.ErrorQuality
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.event.guild.member.*
import org.bukkit.entity.Player
import org.bukkit.event.Event

class EventMemberExpression : SimpleExpression<Player>() {

    companion object {
        init {
            Skript.registerExpression(
                EventMemberExpression::class.java,
                Player::class.java,
                ExpressionType.SIMPLE,
                *EventType.patterns
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        MEMBER_INVITE("doer", GuildMemberInviteEvent::class.java) {
            override fun get(event: Event): Player? {
                if(event is GuildMemberInviteEvent) {
                    return event.member.player
                }
                return null
            }
        },

        MEMBER_ACCEPT_INVITE("doer", GuildMemberAcceptInviteEvent::class.java) {
            override fun get(event: Event): Player? {
                if(event is GuildMemberAcceptInviteEvent) {
                    return event.member.player
                }
                return null
            }
        },

        MEMBER_REVOKE_INVITE("doer", GuildMemberRevokeInviteEvent::class.java) {
            override fun get(event: Event): Player? {
                if(event is GuildMemberRevokeInviteEvent) {
                    return event.member.player
                }
                return null
            }
        },

        MEMBER_JOIN("doer", GuildMemberJoinEvent::class.java) {
            override fun get(event: Event): Player? {
                if(event is GuildMemberJoinEvent) {
                    return event.member.player
                }
                return null
            }
        },

        MEMBER_LEAVE("doer", GuildMemberLeaveEvent::class.java) {
            override fun get(event: Event): Player? {
                if(event is GuildMemberLeaveEvent) {
                    return event.member.player
                }
                return null
            }
        },

        MEMBER_KICK("doer", GuildMemberKickEvent::class.java) {
            override fun get(event: Event): Player? {
                if(event is GuildMemberKickEvent) {
                    return event.member.player
                }
                return null
            }
        },

        LEADER_CHANGE("doer", GuildMemberLeaderEvent::class.java) {
            override fun get(event: Event): Player? {
                if(event is GuildMemberLeaderEvent) {
                    return event.member.player
                }
                return null
            }
        },

        DEPUTY_CHANGE("doer", GuildMemberDeputyEvent::class.java) {
            override fun get(event: Event): Player? {
                if(event is GuildMemberDeputyEvent) {
                    return event.member.player
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
        if (!ScriptLoader.isCurrentEvent(*type.events)) {
            Skript.error(
                "The " + type.name + " message can only be used in a " + type.name + " event",
                ErrorQuality.SEMANTIC_ERROR
            )
            return false
        }
        return true
    }

    override fun get(event: Event): Array<Player?>? {
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

    override fun toString(event: Event?, debug: Boolean): String {
        return "the " + type.name + " member"
    }

    override fun getReturnType(): Class<out Player> {
        return Player::class.java
    }

}