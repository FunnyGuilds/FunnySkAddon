package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.GuildEnlargeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect

@FunnyDoc
@Name("Set Guild Enlarge")
@Description("Ustawie poziom powiększenia gildii")
@Examples(
    "set enlarge level of \"FajnaNazwa\" guild to 40",
)
class GuildSetEnlargeEffect : GuildValueEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetEnlargeEffect::class.java,
                "set enlarge [level|lvl] of %object%(|'s) guild to %number%"
            )
        }
    }

    override fun execute(event: Event?) {
        val guild = getGuild(event)

        if (!SimpleEventHandler.handle(GuildEnlargeEvent(FunnyEvent.EventCause.CONSOLE, null, guild))) {
            return
        }

        guild?.region?.enlarge = getValue(event)?.toInt()!!
    }

}