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
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.guild.*
import net.dzikoysk.funnyguilds.event.guild.ally.GuildAcceptAllyInvitationEvent
import net.dzikoysk.funnyguilds.event.guild.ally.GuildBreakAllyEvent
import net.dzikoysk.funnyguilds.event.guild.ally.GuildRevokeAllyInvitationEvent
import net.dzikoysk.funnyguilds.event.guild.ally.GuildSendAllyInvitationEvent
import net.dzikoysk.funnyguilds.event.guild.member.*
import net.dzikoysk.funnyguilds.event.rank.DeathsChangeEvent
import net.dzikoysk.funnyguilds.event.rank.KillsChangeEvent
import net.dzikoysk.funnyguilds.event.rank.PointsChangeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.events.guilds.CustomGuildCreateEvent
import pl.funnyskaddon.events.rank.CustomKillPointsChangeEvent

@FunnyDoc
@Name("Cause")
@Description("Zwraca przyczynę wydarzenia")
@Events(
    "guild delete",
    "guild delete",
    "guild ban",
    "guild unban",
    "guild move",
    "guild base change",
    "guild rename",
    /*"guild retag", */
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
    "kills change",
    "deaths change",
    "points change",
    "kill points change"
)
class EventCauseExpression : SimpleExpression<FunnyEvent.EventCause>() {

    companion object {
        init {
            Skript.registerExpression(
                EventCauseExpression::class.java,
                FunnyEvent.EventCause::class.java,
                ExpressionType.SIMPLE,
                *EventType.patterns.toTypedArray()
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        CREATE("cause", CustomGuildCreateEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is CustomGuildCreateEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        DELETE("cause", GuildDeleteEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildDeleteEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        BAN("cause", GuildBanEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildBanEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        UNBAN("cause", GuildUnbanEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildUnbanEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MOVE("cause", GuildMoveEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildMoveEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        BASE_CHANGE("cause", GuildMoveEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildMoveEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        RENAME("cause", GuildRenameEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildRenameEvent) {
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
                if (event is GuildEnlargeEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        EXTEND_VALIDITY("cause", GuildExtendValidityEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildExtendValidityEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        LIVES_CHANGE("cause", GuildLivesChangeEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildLivesChangeEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MEMBER_INVITE("cause", GuildMemberInviteEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildMemberInviteEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MEMBER_ACCEPT_INVITE("cause", GuildMemberAcceptInviteEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildMemberAcceptInviteEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MEMBER_REVOKE_INVITE("cause", GuildMemberRevokeInviteEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildMemberRevokeInviteEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MEMBER_JOIN("cause", GuildMemberJoinEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildMemberJoinEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MEMBER_LEAVE("cause", GuildMemberLeaveEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildMemberLeaveEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        MEMBER_KICK("cause", GuildMemberKickEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildMemberKickEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        LEADER_CHANGE("cause", GuildMemberLeaderEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildMemberLeaderEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        DEPUTY_CHANGE("cause", GuildMemberDeputyEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildMemberDeputyEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        SEND_ALLY_INVITATION("cause", GuildSendAllyInvitationEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildSendAllyInvitationEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        ACCEPT_ALLY_INVITATION("cause", GuildAcceptAllyInvitationEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildAcceptAllyInvitationEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        REVOKE_ALLY_INVITATION("cause", GuildRevokeAllyInvitationEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildRevokeAllyInvitationEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        BREAK_ALLY("cause", GuildBreakAllyEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildBreakAllyEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        REGION_ENTER("cause", GuildRegionEnterEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildRegionEnterEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        REGION_LEAVE("cause", GuildRegionLeaveEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is GuildRegionLeaveEvent) {
                    return event.eventCause
                }
                return null
            }
        },


        KILLS_CHANGE("cause", KillsChangeEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is KillsChangeEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        DEATHS_CHANGE("cause", DeathsChangeEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is DeathsChangeEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        POINTS_CHANGE("cause", PointsChangeEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is PointsChangeEvent) {
                    return event.eventCause
                }
                return null
            }
        },

        KILL_POINTS_CHANGE("cause", CustomKillPointsChangeEvent::class.java) {
            override fun get(event: Event): FunnyEvent.EventCause? {
                if (event is CustomKillPointsChangeEvent) {
                    return event.eventCause
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
        return "the " + type.name + " cause"
    }

    override fun getReturnType(): Class<out FunnyEvent.EventCause> {
        return FunnyEvent.EventCause::class.java
    }

}