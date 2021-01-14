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
import net.dzikoysk.funnyguilds.event.FunnyEvent
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

class EventCauseExpression : SimpleExpression<FunnyEvent.EventCause>() {

    companion object {
        init {
            Skript.registerExpression(
                EventCauseExpression::class.java,
                FunnyEvent.EventCause::class.java,
                ExpressionType.SIMPLE,
                *EventType.patterns
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        CREATE("cause", CustomGuildCreateEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is CustomGuildCreateEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        DELETE("cause", GuildDeleteEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildDeleteEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        BAN("cause", GuildBanEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildBanEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        UNBAN("cause", GuildUnbanEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildUnbanEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MOVE("cause", GuildMoveEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildMoveEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        BASE_CHANGE("cause", GuildMoveEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildMoveEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        RENAME("cause", GuildRenameEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildRenameEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        /*
            In future, maybe
         */
        /*RETAG("[retaged( |-)]guild", GuildRenameEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildRenameEvent) {
                    return event.guild
                }
                return null
            }
        },*/


        ENLARGE("cause", GuildEnlargeEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildEnlargeEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        EXTEND_VALIDITY("cause", GuildExtendValidityEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildExtendValidityEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        LIVES_CHANGE("cause", GuildLivesChangeEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildLivesChangeEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MEMBER_INVITE("cause", GuildMemberInviteEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildMemberInviteEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MEMBER_ACCEPT_INVITE("cause", GuildMemberAcceptInviteEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildMemberAcceptInviteEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MEMBER_REVOKE_INVITE("cause", GuildMemberRevokeInviteEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildMemberRevokeInviteEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MEMBER_JOIN("cause", GuildMemberJoinEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildMemberJoinEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MEMBER_LEAVE("cause", GuildMemberLeaveEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildMemberLeaveEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MEMBER_KICK("cause", GuildMemberKickEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildMemberKickEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        LEADER_CHANGE("cause", GuildMemberLeaderEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildMemberLeaderEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        DEPUTY_CHANGE("cause", GuildMemberDeputyEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildMemberDeputyEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        SEND_ALLY_INVITATION("cause", GuildSendAllyInvitationEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildSendAllyInvitationEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        ACCEPT_ALLY_INVITATION("cause", GuildAcceptAllyInvitationEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildAcceptAllyInvitationEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        REVOKE_ALLY_INVITATION("cause", GuildRevokeAllyInvitationEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildRevokeAllyInvitationEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        BREAK_ALLY("cause", GuildBreakAllyEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if(event is GuildBreakAllyEvent) {
                    return event.eventCause
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

        abstract operator fun get(event: Event): FunnyEvent.EventCause?
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

    override fun get(event: Event): Array<FunnyEvent.EventCause?>? {
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

    override fun getReturnType(): Class<out FunnyEvent.EventCause> {
        return FunnyEvent.EventCause::class.java
    }

}