package pl.funnyskaddon.skript.condition.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.condition.GuildCondition
import pl.funnyskaddon.skript.getGuild

@FunnyDoc
@Name("Guild Does Exist")
@Description("Sprawdza, czy gildia istnieje")
@Examples(
    "if guild \"AC4U\" exists:",
    "if guild \"AC4U\" doesn't exists:"
)
class GuildDoesExistCondition : GuildCondition(7) {

    companion object {
        init {
            Skript.registerCondition(
                GuildDoesExistCondition::class.java,
                "%guild% [does] exist[s]",
                "%string% [guild] [does] exist[s]",
                "guild at %location% [does] exist[s]",
                "guild at %block% [does] exist[s]",
                "%offlineplayer%['s] guild [does] exist[s]",
                "%object%['s] guild [does] exist[s]",
                "%guild% does(n't|not) exist[s]",
                "%string% [guild] does(n't|not) exist[s]",
                "guild at %location% does(n't|not) exist[s]",
                "guild at %block% does(n't|not) exist[s]",
                "%offlineplayer%['s] guild does(n't|not)exist[s]",
                "%object%['s] guild does(n't|not) exist[s]",
            )
        }
    }

    override fun check(event: Event): Boolean {
        return event.getGuild(guildExpression)
            .isPresent
            .xor(isNegated)
    }

}