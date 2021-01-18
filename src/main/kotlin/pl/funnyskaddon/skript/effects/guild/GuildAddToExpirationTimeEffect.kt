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
@Name("Add To Expiration Time")
@Description("Dodaje czas do daty wygaśnięcia gildii")
@Examples(
    "add 1 day to \"FajnaNazwa\" guild expiration time",
)
class GuildAddToExpirationTimeEffect : GuildValueEffect<Timespan>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildAddToExpirationTimeEffect::class.java,
                "add %timespan% to %object%(|'s) guild (expiration|validity) [date|time]"
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

        guild?.validity = guild?.validity?.plus(change)!!
    }

}