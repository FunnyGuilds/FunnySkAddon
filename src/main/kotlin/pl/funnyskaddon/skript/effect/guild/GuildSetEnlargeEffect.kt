package pl.funnyskaddon.skript.effect.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.GuildEnlargeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.GuildValueEffect
import pl.funnyskaddon.skript.getGuildOption
import pl.funnyskaddon.skript.getValueOption

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
                "set enlarge [(level|lvl)] of %guild% to %number%",
                "set enlarge [(level|lvl)] of %string% to %number%",
                "set enlarge [(level|lvl)] of %offlineplayer%['s] [guild] to %number%",
                "set enlarge [(level|lvl)] of %location%['s] [guild] to %number%",
                "set enlarge [(level|lvl)] of %block%['s] [guild] to %number%",
                "set enlarge [(level|lvl)] of %object%['s] [guild] to %number%"
            )
        }
    }

    override fun execute(event: Event) {
        event.getGuildOption(guildExpression)
            .peek { guild ->
                event.getValueOption(valueExpression)
                    .map(Number::toInt)
                    .peek valuePeek@{ value ->
                        if (!SimpleEventHandler.handle(GuildEnlargeEvent(FunnyEvent.EventCause.CONSOLE, null, guild))) {
                            return@valuePeek
                        }

                        guild.region.enlarge = value
                        guild.region.size = value * FunnySkAddon.fgConfiguration.enlargeSize
                    }
            }
    }

}