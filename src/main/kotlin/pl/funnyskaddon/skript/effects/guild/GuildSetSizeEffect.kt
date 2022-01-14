package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect
import pl.funnyskaddon.skript.getGuildOption
import pl.funnyskaddon.skript.getValueOption

@FunnyDoc
@Name("Set Guild Size")
@Description("Ustawie wielkość gildii")
@Examples(
    "set size of \"FajnaNazwa\" guild to 40",
)
class GuildSetSizeEffect : GuildValueEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetSizeEffect::class.java,
                "set size of %object%(|'s) guild to %number%"
            )
        }
    }

    override fun execute(event: Event) {
        event.getGuildOption(guildExpression)
            .peek { guild ->
                event.getValueOption(valueExpression)
                    .map(Number::toInt)
                    .peek { value -> guild.region.size = value }
            }
    }

}