package pl.funnyskaddon.skript.effect.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.concurrency.requests.prefix.PrefixGlobalRemovePlayerRequest
import net.dzikoysk.funnyguilds.concurrency.requests.prefix.PrefixGlobalUpdatePlayer
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberKickEvent
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.GuildValueEffect
import pl.funnyskaddon.skript.getGuildOption
import pl.funnyskaddon.skript.getUserOption


@FunnyDoc
@Name("Remove Guild Member")
@Description("Usuwa członka z gildii")
@Examples(
    "remove player from \"AC4U\" guild members",
)
class GuildRemoveMemberEffect : GuildValueEffect<OfflinePlayer>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildRemoveMemberEffect::class.java,
                "remove %offlineplayer% from %object%(|'s) guild members"
            )
        }
    }

    override fun execute(event: Event) {
        event.getGuildOption(guildExpression)
            .peek { guild ->
                event.getUserOption(valueExpression)
                    .peek userPeek@{ user ->
                        if (!SimpleEventHandler.handle(
                                GuildMemberKickEvent(
                                    FunnyEvent.EventCause.CONSOLE,
                                    null,
                                    guild,
                                    user
                                )
                            )
                        ) {
                            return@userPeek
                        }

                        FunnyGuilds.getInstance().concurrencyManager.postRequests(PrefixGlobalRemovePlayerRequest(user.name))

                        guild.removeMember(user)
                        user.removeGuild()

                        val player = user.player
                        if (player != null) {
                            FunnyGuilds.getInstance().concurrencyManager.postRequests(PrefixGlobalUpdatePlayer(player))
                        }
                    }
            }
    }

}