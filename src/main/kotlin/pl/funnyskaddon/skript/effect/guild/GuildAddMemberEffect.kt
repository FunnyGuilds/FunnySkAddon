package pl.funnyskaddon.skript.effect.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Expression
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.concurrency.requests.prefix.PrefixGlobalAddPlayerRequest
import net.dzikoysk.funnyguilds.concurrency.requests.prefix.PrefixGlobalRemovePlayerRequest
import net.dzikoysk.funnyguilds.concurrency.requests.prefix.PrefixGlobalUpdatePlayer
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberJoinEvent
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberLeaveEvent
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.GuildValueEffect
import pl.funnyskaddon.skript.getUserOption
import pl.funnyskaddon.skript.getValueOption
import pl.funnyskaddon.util.getGuild


@FunnyDoc
@Name("Add Guild Member")
@Description(
    "Dodaje gracza do gildii<br>",
    "Alternatywa: <b>set %offlineplayer%['s] guild to %object%</b>"
)
@Examples(
    "add player to \"AC4U\" guild members",
)
class GuildAddMemberEffect : GuildValueEffect<OfflinePlayer>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildAddMemberEffect::class.java,
                "add %offlineplayer% to %object%['s] [guild] members"
            )
        }

        fun execute(event: Event, guildExpression: Expression<Any>, playerExpression: Expression<OfflinePlayer>) {
            event.getUserOption(playerExpression)
                .peek { user ->
                    val guild = event.getValueOption(guildExpression)
                        .map(Any::getGuild)
                        .orNull

                    if (user.hasGuild()) {
                        if (user.isOwner) {
                            return@peek
                        }

                        if (guild == null) {
                            removerFromGuild(user)
                        } else {
                            if (user.guild == guild) {
                                return@peek
                            }

                            if (removerFromGuild(user)) {
                                addMemberToGuild(user, guild)
                            }
                        }
                    } else {
                        if (guild == null) {
                            return@peek
                        }

                        addMemberToGuild(user, guild)
                    }
                }
        }

        private fun addMemberToGuild(member: User, guild: Guild): Boolean {
            if (!SimpleEventHandler.handle(
                    GuildMemberJoinEvent(FunnyEvent.EventCause.CONSOLE, null, guild, member)
                )
            ) {
                return false
            }

            guild.addMember(member)
            member.guild = guild
            FunnyGuilds.getInstance().concurrencyManager.postRequests(PrefixGlobalAddPlayerRequest(member.name))

            return true
        }

        private fun removerFromGuild(member: User): Boolean {
            if (!SimpleEventHandler.handle(
                    GuildMemberLeaveEvent(FunnyEvent.EventCause.CONSOLE, null, member.guild, member)
                )
            ) {
                return false
            }
            member.guild.removeMember(member)
            member.removeGuild()
            FunnyGuilds.getInstance().concurrencyManager.postRequests(
                PrefixGlobalRemovePlayerRequest(member.name),
                PrefixGlobalUpdatePlayer(member.player)
            )

            return true
        }
    }

    public override fun execute(event: Event) {
        execute(event, guildExpression, valueExpression)
    }

}