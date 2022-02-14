package pl.funnyskaddon.skript.condition.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.condition.GuildCondition
import pl.funnyskaddon.skript.getGuild

@FunnyDoc
@Name("Guild PvP Is Enabled")
@Description("Sprawdza, czy gildia ma włączone pvp")
@Examples(
    "if guild \"AC4U\" pvp is enabled:",
    "if guild \"AC4U\" pvp is disabled:"
)
class GuildPvPIsEnabledCondition : GuildCondition() {

    companion object {
        init {
            Skript.registerCondition(
                GuildPvPIsEnabledCondition::class.java, "guild %object% pvp is (enabled|on)",
                "guild %object% pvp is (disabled|off)"
            )
        }
    }

    override fun check(event: Event): Boolean {
        return event.getGuild(guildExpression)
            .map(Guild::getPvP)
            .orElseGet(false)
            .xor(isNegated)
    }

}