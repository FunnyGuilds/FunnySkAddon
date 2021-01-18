package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.util.Timespan
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect

@FunnyDoc
@Name("Remove Expiration Time")
@Description("Usuwa czas z daty wygaśnięcia gildii")
@Examples(
    "remove 1 day from \"FajnaNazwa\" guild expiration time",
)
class GuildRemoveFromExpirationTimeEffect : GuildValueEffect<Timespan>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildRemoveFromExpirationTimeEffect::class.java,
                "remove %timespan% from %object%(|'s) guild (expiration|validity) [time]"
            )
        }
    }

    override fun execute(event: Event?) {
        val guild = getGuild(event)

        var change = 0L
        val value = getValue(event)
        if (value != null) {
            change = value.milliSeconds
        }

        guild?.validity = guild?.validity?.minus(change)!!
    }

}