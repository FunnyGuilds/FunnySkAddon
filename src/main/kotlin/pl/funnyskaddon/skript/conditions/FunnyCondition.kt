package pl.funnyskaddon.skript.conditions

import ch.njol.skript.lang.Condition
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.basic.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.util.GuildUtil

abstract class FunnyCondition : Condition() {

    override fun toString(event: Event?, debug: Boolean): String? {
        return null
    }

}