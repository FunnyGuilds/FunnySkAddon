package pl.funnyskaddon.skript.effect.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.concurrency.requests.prefix.PrefixGlobalAddPlayerRequest
import net.dzikoysk.funnyguilds.concurrency.requests.prefix.PrefixGlobalRemovePlayerRequest
import net.dzikoysk.funnyguilds.concurrency.requests.prefix.PrefixGlobalUpdatePlayer
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberJoinEvent
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberLeaveEvent
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
                "add %offlineplayer% to %object%['s] guild members"
            )
        }
    }

    override fun execute(event: Event) {
        event.getUserOption(valueExpression)
            .peek { user ->
                val guild = event.getValueOption(valueExpression)
                    .map(Any::getGuild)
                    .orNull

                val concurrencyManager = FunnyGuilds.getInstance().concurrencyManager

                if (user.hasGuild() && guild != null) {
                    if (user.isOwner) {
                        return@peek
                    }

                    if (!SimpleEventHandler.handle(
                            GuildMemberLeaveEvent(FunnyEvent.EventCause.CONSOLE, null, user.guild, user)
                        )
                    ) {
                        return@peek
                    }
                    user.guild.removeMember(user)
                    user.removeGuild()
                    concurrencyManager.postRequests(
                        PrefixGlobalRemovePlayerRequest(user.name),
                        PrefixGlobalUpdatePlayer(user.player)
                    )
                } else {
                    if (guild == null) {
                        return@peek
                    }

                    if (!SimpleEventHandler.handle(
                            GuildMemberJoinEvent(FunnyEvent.EventCause.CONSOLE, null, guild, user)
                        )
                    ) {
                        return@peek
                    }

                    guild.addMember(user)
                    user.guild = guild
                    concurrencyManager.postRequests(PrefixGlobalAddPlayerRequest(user.name))
                }
            }
    }

}