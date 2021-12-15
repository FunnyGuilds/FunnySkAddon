package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberJoinEvent
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.PlayerEffect
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

    override fun execute(event: Event?) {
        val user = getUser(event)
        val guild = getGuild(event)

        if (!SimpleEventHandler.handle(GuildMemberJoinEvent(FunnyEvent.EventCause.CONSOLE, null, guild, user))) {
            return
        }

        if (guild == null && user?.guild != null) {
            user.guild.removeMember(user)
        } else {
            if (user?.guild != null) {
                user.guild.removeMember(user)
            }
            guild?.addMember(user)
            user?.guild = guild
        }
    }

    fun getGuild(event: Event?): Guild? {
        return try {
            getValue(event)?.getGuild()
        } catch (ex: Exception) {
            null
        }
    }

}