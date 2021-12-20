package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberJoinEvent
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberLeaveEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.PlayerEffect
import pl.funnyskaddon.skript.getUserOption
import pl.funnyskaddon.skript.getValueOption
import pl.funnyskaddon.util.getGuild

@FunnyDoc
@Name("Set Guild")
@Description(
    "Ustawia gildie gracza (dołącza go do niej)",
    "Alternatywa: add %offlineplayer% to %object%(|'s) guild members"
)
@Examples(
    "set player's guild to \"AC4U\"",
)
class PlayerSetGuildEffect : PlayerEffect<Any>(false) {

    companion object {
        init {
            Skript.registerEffect(PlayerSetGuildEffect::class.java, "set %offlineplayer%(|'s) guild to %object%")
        }
    }

    override fun execute(event: Event) {
        event.getUserOption(playerExpression)
            .peek { user ->
                val guild = event.getValueOption(valueExpression)
                    .map(Any::getGuild)
                    .orNull

                if (user.hasGuild() && guild != null) {
                    if (!SimpleEventHandler.handle(
                            GuildMemberLeaveEvent(FunnyEvent.EventCause.CONSOLE, null, user.guild, user)
                        )
                    ) {
                        return@peek
                    }
                    user.guild.removeMember(user)
                    user.removeGuild()
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
                }
            }
    }

}