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

class EventGuildExpression : SimpleExpression<Guild>() {

    companion object {
        init {
            Skript.registerExpression(
                EventGuildExpression::class.java,
                Guild::class.java,
                ExpressionType.SIMPLE,
                *EventType.patterns
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        CREATE("[created( |-)]guild", CustomGuildCreateEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is CustomGuildCreateEvent) {
                    return event.guild
                }
                return null
            }
        },

        DELETE("[deleted( |-)]guild", GuildDeleteEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildDeleteEvent) {
                    return event.guild
                }
                return null
            }
        },

        BAN("[banned( |-)]guild", GuildBanEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildBanEvent) {
                    return event.guild
                }
                return null
            }
        },

        UNBAN("[unbanned( |-)]guild", GuildUnbanEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildUnbanEvent) {
                    return event.guild
                }
                return null
            }
        },

        MOVE("[moved( |-)]guild", GuildMoveEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildMoveEvent) {
                    return event.guild
                }
                return null
            }
        },

        BASE_CHANGE("guild", GuildMoveEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildMoveEvent) {
                    return event.guild
                }
                return null
            }
        },

        RENAME("[renamed( |-)]guild", GuildRenameEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildRenameEvent) {
                    return event.guild
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


        ENLARGE("[enlarged( |-)]guild", GuildEnlargeEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildEnlargeEvent) {
                    return event.guild
                }
                return null
            }
        },

        EXTEND_VALIDITY("guild", GuildExtendValidityEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildExtendValidityEvent) {
                    return event.guild
                }
                return null
            }
        },

        LIVES_CHANGE("guild", GuildLivesChangeEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildLivesChangeEvent) {
                    return event.guild
                }
                return null
            }
        },

        MEMBER_INVITE("guild", GuildMemberInviteEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildMemberInviteEvent) {
                    return event.guild
                }
                return null
            }
        },

        MEMBER_ACCEPT_INVITE("guild", GuildMemberAcceptInviteEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildMemberAcceptInviteEvent) {
                    return event.guild
                }
                return null
            }
        },

        MEMBER_REVOKE_INVITE("guild", GuildMemberRevokeInviteEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildMemberRevokeInviteEvent) {
                    return event.guild
                }
                return null
            }
        },

        MEMBER_JOIN("guild", GuildMemberJoinEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildMemberJoinEvent) {
                    return event.guild
                }
                return null
            }
        },

        MEMBER_LEAVE("guild", GuildMemberLeaveEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildMemberLeaveEvent) {
                    return event.guild
                }
                return null
            }
        },

        MEMBER_KICK("guild", GuildMemberKickEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildMemberKickEvent) {
                    return event.guild
                }
                return null
            }
        },

        LEADER_CHANGE("guild", GuildMemberLeaderEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildMemberLeaderEvent) {
                    return event.guild
                }
                return null
            }
        },

        DEPUTY_CHANGE("guild", GuildMemberDeputyEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildMemberDeputyEvent) {
                    return event.guild
                }
                return null
            }
        },

        SEND_ALLY_INVITATION("guild", GuildSendAllyInvitationEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildSendAllyInvitationEvent) {
                    return event.guild
                }
                return null
            }
        },

        ACCEPT_ALLY_INVITATION("guild", GuildAcceptAllyInvitationEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildAcceptAllyInvitationEvent) {
                    return event.guild
                }
                return null
            }
        },

        REVOKE_ALLY_INVITATION("guild", GuildRevokeAllyInvitationEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildRevokeAllyInvitationEvent) {
                    return event.guild
                }
                return null
            }
        },

        BREAK_ALLY("guild", GuildBreakAllyEvent::class.java) {
            override fun get(event: Event): Guild? {
                if(event is GuildBreakAllyEvent) {
                    return event.guild
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