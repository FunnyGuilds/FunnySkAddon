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
import net.dzikoysk.funnyguilds.event.guild.*
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
@Name("Guild")
@Description("Zwraca gildie, której dotyczy wydarzenie")
@Events(
    "guild delete",
    "guild delete",
    "guild ban",
    "guild unban",
    "guild move",
    "guild base change",
    "guild rename",
    "guild tag change",
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
    "guild region leave"
)
class EventGuildExpression : SimpleExpression<Guild>() {

    companion object {
        init {
            Skript.registerExpression(
                EventGuildExpression::class.java,
                Guild::class.java,
                ExpressionType.SIMPLE,
                *EventType.patterns.toTypedArray()
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        GUILD("guild", GuildEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildEvent) {
                    return event.guild
                }
                return null
            }
        },

        CREATE("[created( |-)]guild", GuildCreateEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildCreateEvent) {
                    return event.guild
                }
                return null
            }
        },

        DELETE("[deleted( |-)]guild", GuildDeleteEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildDeleteEvent) {
                    return event.guild
                }
                return null
            }
        },

        BAN("[banned( |-)]guild", GuildBanEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildBanEvent) {
                    return event.guild
                }
                return null
            }
        },

        UNBAN("[unbanned( |-)]guild", GuildUnbanEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildUnbanEvent) {
                    return event.guild
                }
                return null
            }
        },

        MOVE("[moved( |-)]guild", GuildMoveEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildMoveEvent) {
                    return event.guild
                }
                return null
            }
        },

        RENAME("[renamed( |-)]guild", GuildPreRenameEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildPreRenameEvent) {
                    return event.guild
                }
                return null
            }
        },

        TAG_CHANGE("(([retaged( |-)]guild)|guild with changed tag)", GuildPreTagChangeEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildPreTagChangeEvent) {
                    return event.guild
                }
                return null
            }
        },

        ENLARGE("[enlarged( |-)]guild", GuildEnlargeEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildEnlargeEvent) {
                    return event.guild
                }
                return null
            }
        },

        REGION_ENTER("[entered( |-)]guild", GuildRegionEnterEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildRegionEnterEvent) {
                    return event.guild
                }
                return null
            }
        },

        REGION_LEAVE("[leaved( |-)]guild", GuildRegionLeaveEvent::class.java) {
            override fun get(event: Event): Guild? {
                if (event is GuildRegionLeaveEvent) {
                    return event.guild
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
        if (!EventExpressionUtil.isCurrentEvent(*type.events)) {
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

    override fun getReturnType(): Class<out Guild> {
        return Guild::class.java
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "the " + type.name + " guild"
    }

}