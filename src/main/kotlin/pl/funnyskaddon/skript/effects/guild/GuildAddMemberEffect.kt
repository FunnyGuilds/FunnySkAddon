package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.basic.user.User
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberJoinEvent
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect


@FunnyDoc
@Name("Add Guild Member")
@Description(
    "Dodaje gracza do gildii<br>",
    "Alternatywa: <b>set %offlineplayer%(|'s) guild to %object%</b>"
)
@Examples(
    "add player to \"AC4U\" guild members",
)
class GuildAddMemberEffect : GuildValueEffect<OfflinePlayer>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildAddMemberEffect::class.java,
                "add %offlineplayer% to %object%(|'s) guild members"
            )
        }
    }

    override fun execute(event: Event?) {
        val user = User.get(getValue(event))
        val guild = getGuild(event)

        if (!SimpleEventHandler.handle(GuildMemberJoinEvent(FunnyEvent.EventCause.CONSOLE, null, guild, user))) {
            return
        }

        guild?.addMember(user)
        user?.guild = guild
    }

}