package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberDeputyEvent
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import panda.std.Option
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect


@FunnyDoc
@Name("Add Guild Deputy")
@Description("Dodaje zastępce gildii")
@Examples(
    "add player to \"AC4U\" guild deputies",
)
class GuildAddDeputyEffect : GuildValueEffect<OfflinePlayer>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildAddDeputyEffect::class.java,
                "add %offlineplayer% to %object%(|'s) guild deputies"
            )
        }
    }

    override fun execute(event: Event?) {
        val userOption: Option<User> = FunnyGuilds.getInstance().userManager.findByPlayer(getValue(event))
        if (userOption.isEmpty) {
            return
        }
        val user = userOption.get()

        val guild = getGuild(event)

        if (!SimpleEventHandler.handle(GuildMemberDeputyEvent(FunnyEvent.EventCause.CONSOLE, null, guild, user))) {
            return
        }

        guild?.addDeputy(user)
    }

}