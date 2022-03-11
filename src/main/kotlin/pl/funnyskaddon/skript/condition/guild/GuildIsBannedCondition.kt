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
@Name("Guild Is Banned")
@Description("Sprawdza, czy gildia jest zbanowana")
@Examples(
    "if guild \"AC4U\" is banned:",
    "if guild \"AC4U\" is not banned:"
)
class GuildIsBannedCondition : GuildCondition(7) {

    companion object {
        init {
            Skript.registerCondition(
                GuildIsBannedCondition::class.java,
                "%guild% is banned",
                "%string% [guild] is banned",
                "%offlineplayer%['s] guild is banned",
                "guild at %location% is banned",
                "guild at %block% is banned",
                "%object%['s] guild is banned",
                "%guild% is(n't| not) banned",
                "%string% [guild] is(n't| not) banned",
                "%offlineplayer%['s] guild is(n't| not) banned",
                "guild at %location% is(n't| not) banned",
                "guild at %block% is(n't| not) banned",
                "%object%['s] guild is(n't| not) banned",
            )
        }
    }

    override fun check(event: Event): Boolean {
        return event.getGuild(guildExpression)
            .map(Guild::isBanned)
            .orElseGet(false)
            .xor(isNegated)
    }

}