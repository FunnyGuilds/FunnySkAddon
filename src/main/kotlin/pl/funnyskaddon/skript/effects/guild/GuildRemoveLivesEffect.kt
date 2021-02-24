package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.GuildLivesChangeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect


@FunnyDoc
@Name("Remove Guild Lives")
@Description("Usuwa życia z gildii")
@Examples(
    "remove 4 lives from \"AC4U\" guild",
)
class GuildRemoveLivesEffect : GuildValueEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildRemoveLivesEffect::class.java,
                "remove %number% live[s] from %object%(|'s) guild"
            )
        }
    }

    override fun execute(event: Event?) {
        val guild = getGuild(event)

        var change = 0
        val value = getValue(event)
        if (value != null) {
            change = value.toInt()
        }

        val newLives = guild?.lives?.minus(change)!!

        if (!SimpleEventHandler.handle(GuildLivesChangeEvent(FunnyEvent.EventCause.CONSOLE, null, guild, newLives))) {
            return
        }

        guild.lives = newLives
    }

}