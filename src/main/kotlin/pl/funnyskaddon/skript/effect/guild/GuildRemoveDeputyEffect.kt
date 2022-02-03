package pl.funnyskaddon.skript.effect.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.GuildValueEffect
import pl.funnyskaddon.skript.getGuildOption
import pl.funnyskaddon.skript.getUserOption

@FunnyDoc
@Name("Remove Guild Deputy")
@Description("Usuwa zastępce z gildii")
@Examples(
    "remove player from \"AC4U\" guild deputies",
)
class GuildRemoveDeputyEffect : GuildValueEffect<OfflinePlayer>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildRemoveDeputyEffect::class.java,
                "remove %offlineplayer% from %guild%['s] deputies",
                "remove %offlineplayer% from %string%['s] deputies",
                "remove %offlineplayer% from %offlineplayer%['s] [guild] deputies",
                "remove %offlineplayer% from %location%['s] [guild] deputies",
                "remove %offlineplayer% from %block%['s] [guild] deputies",
                "remove %offlineplayer% from %object%['s] [guild] deputies"
            )
        }
    }

    override fun execute(event: Event) {
        event.getGuildOption(guildExpression)
            .peek { guild ->
                event.getUserOption(valueExpression)
                    .peek userPeek@{ user ->
                        guild.removeDeputy(user)
                    }
            }
    }

}