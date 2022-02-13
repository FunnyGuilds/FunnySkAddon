package pl.funnyskaddon.skript.effect.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.GuildValueEffect
import pl.funnyskaddon.skript.getGuild
import pl.funnyskaddon.skript.getValue

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
                "set size of %object% %number%",
                "set size of %string% to %number%",
                "set size of %offlineplayer%['s] [guild] to %number%",
                "set size of %location%['s] [guild] to %number%",
                "set size of %block%['s] [guild] to %number%",
                "set size of %object%['s] [guild] to %number%",
            )
        }
    }

    override fun execute(event: Event) {
        event.getGuild(guildExpression)
            .flatMap(Guild::getRegion)
            .peek { region ->
                event.getValue(valueExpression)
                    .map(Number::toInt)
                    .peek { value -> region.size = value }
            }
    }

}