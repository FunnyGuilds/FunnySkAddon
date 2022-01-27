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
import net.dzikoysk.funnyguilds.event.guild.member.*
import org.bukkit.entity.Player
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
@Name("Member")
@Description("Zwraca członka, który uczestniczył w wydarzeniu")
@Events(
    "guild member join",
    "guild member leave",
    "guild member kick",
    "guild member leader",
    "guild member deputy",
)
class EventMemberExpression : SimpleExpression<Player>() {

    companion object {
        init {
            Skript.registerExpression(
                EventMemberExpression::class.java,
                Player::class.java,
                ExpressionType.SIMPLE,
                *EventType.patterns.toTypedArray()
            )
        }
    }

    private enum class EventType(var pattern: String, vararg var events: Class<out Event>) {

        MEMBER(
            "member",
            GuildMemberInviteEvent::class.java,
            GuildMemberAcceptInviteEvent::class.java,
            GuildMemberRevokeInviteEvent::class.java,
            GuildMemberJoinEvent::class.java,
            GuildMemberLeaveEvent::class.java,
            GuildMemberKickEvent::class.java,
            GuildMemberLeaderEvent::class.java,
            GuildMemberDeputyEvent::class.java
        ) {
            override fun get(event: Event): Player? {
                return when (event) {
                    is GuildMemberInviteEvent -> {
                        event.member.player
                    }
                    is GuildMemberAcceptInviteEvent -> {
                        event.member.player
                    }
                    is GuildMemberRevokeInviteEvent -> {
                        event.member.player
                    }
                    is GuildMemberJoinEvent -> {
                        event.member.player
                    }
                    is GuildMemberLeaveEvent -> {
                        event.member.player
                    }
                    is GuildMemberKickEvent -> {
                        event.member.player
                    }
                    is GuildMemberLeaderEvent -> {
                        event.member.player
                    }
                    is GuildMemberDeputyEvent -> {
                        event.member.player
                    }
                    else -> null
                }
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
        if (!EventExpressionUtil.isCurrentEvent(*type.events)) {
            Skript.error(
                "The '" + type.pattern + "' can only be used in a " + type.name + " event",
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

    override fun getReturnType(): Class<out Player> {
        return Player::class.java
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "the " + type.name + " member"
    }

}