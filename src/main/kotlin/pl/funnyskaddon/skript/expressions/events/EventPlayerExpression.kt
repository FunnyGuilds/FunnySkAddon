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
import net.dzikoysk.funnyguilds.event.guild.*
import net.dzikoysk.funnyguilds.event.guild.ally.GuildAcceptAllyInvitationEvent
import net.dzikoysk.funnyguilds.event.guild.ally.GuildBreakAllyEvent
import net.dzikoysk.funnyguilds.event.guild.ally.GuildRevokeAllyInvitationEvent
import net.dzikoysk.funnyguilds.event.guild.ally.GuildSendAllyInvitationEvent
import net.dzikoysk.funnyguilds.event.guild.member.*
import net.dzikoysk.funnyguilds.event.rank.DeathsChangeEvent
import net.dzikoysk.funnyguilds.event.rank.KillsChangeEvent
import net.dzikoysk.funnyguilds.event.rank.PointsChangeEvent
import org.bukkit.entity.Player
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.events.guilds.CustomGuildCreateEvent

@FunnyDoc
@Name("Player")
@Description("Zwraca gracza który uczestniczył w wydarzeniu")
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
    "kill change",
    "deaths change",
    "points change",
    "kills points change"
)
class EventPlayerExpression : SimpleExpression<Player>() {

    companion object {
        init {
            Skript.registerExpression(
                EventPlayerExpression::class.java,
                Player::class.java,
                ExpressionType.SIMPLE,
                *EventType.patterns.toTypedArray()
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        CREATE("(player|doer)", CustomGuildCreateEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is CustomGuildCreateEvent) {
                    return event.player
                }
                return null
            }
        },

        DELETE("(player|doer)", GuildDeleteEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildDeleteEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        BAN("(player|doer)", GuildBanEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildBanEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        UNBAN("(player|doer)", GuildUnbanEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildUnbanEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        MOVE("(player|doer)", GuildMoveEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildMoveEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        BASE_CHANGED("(player|doer)", GuildBaseChangeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildBaseChangeEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        RENAME("(player|doer)", GuildRenameEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildRenameEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        /*
            In future, maybe
         */
        /*RETAG("player|doer", GuildRenameEvent::class.java) {
            override fun get(event: Event): Player? {
                if(event is GuildRenameEvent) {
                    return event.doer.player
                }
                return null
            }
        },*/

        ENLARGE("(player|doer)", GuildEnlargeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildEnlargeEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        EXTEND_VALIDITY("(player|doer)", GuildExtendValidityEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildExtendValidityEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        LIVES_CHANGE("(player|doer)", GuildLivesChangeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildLivesChangeEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        MEMBER_INVITE("(player|doer)", GuildMemberInviteEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildMemberInviteEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        MEMBER_ACCEPT_INVITE("(player|doer)", GuildMemberAcceptInviteEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildMemberAcceptInviteEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        MEMBER_REVOKE_INVITE("(player|doer)", GuildMemberRevokeInviteEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildMemberRevokeInviteEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        MEMBER_JOIN("(player|doer)", GuildMemberJoinEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildMemberJoinEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        MEMBER_LEAVE("(player|doer)", GuildMemberLeaveEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildMemberLeaveEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        MEMBER_KICK("(player|doer)", GuildMemberKickEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildMemberKickEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        LEADER_CHANGE("(player|doer)", GuildMemberLeaderEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildMemberLeaderEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        DEPUTY_CHANGE("(player|doer)", GuildMemberDeputyEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildMemberDeputyEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        SEND_ALLY_INVITATION("(player|doer)", GuildSendAllyInvitationEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildSendAllyInvitationEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        ACCEPT_ALLY_INVITATION("(player|doer)", GuildAcceptAllyInvitationEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildAcceptAllyInvitationEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        REVOKE_ALLY_INVITATION("(player|doer)", GuildRevokeAllyInvitationEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildRevokeAllyInvitationEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        BREAK_ALLY("(player|doer)", GuildBreakAllyEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildBreakAllyEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        REGION_ENTER("(player|doer)", GuildRegionEnterEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildRegionEnterEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        REGION_LEAVE("(player|doer)", GuildRegionLeaveEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is GuildRegionLeaveEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        KILLS_CHANGE("[kills( |-)change( |-)](player|doer)", KillsChangeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is KillsChangeEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        DEATHS_CHANGE("[deaths( |-)change( |-)](player|doer)", DeathsChangeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is DeathsChangeEvent) {
                    return event.doer.player
                }
                return null
            }
        },

        POINTS_CHANGE("[points( |-)change( |-)](player|doer)", PointsChangeEvent::class.java) {
            override fun get(event: Event): Player? {
                if (event is PointsChangeEvent) {
                    return event.doer.player
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
        return "the " + type.name + " doer"
    }

    override fun getReturnType(): Class<out Player> {
        return Player::class.java
    }

}