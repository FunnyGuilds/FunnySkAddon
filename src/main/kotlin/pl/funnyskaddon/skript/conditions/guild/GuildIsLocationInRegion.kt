package pl.funnyskaddon.skript.conditions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.conditions.GuildValueCondition
import pl.funnyskaddon.util.isLocationInGuildRegion

@FunnyDoc
@Name("Is Location In Guild Region")
@Description("Sprawdza czy dana lokacja jest na terenie danej gildii")
@Examples(
    "if location (location at (100, 100, 100) in world \"world\") is in guild \"AC4U\" region:",
    "if location (location at (100, 100, 100) in world \"world\") is not in guild \"AC4U\" region:"
)
class GuildIsLocationInRegion : GuildValueCondition<Location>(true) {

    companion object {
        init {
            Skript.registerCondition(
                GuildIsLocationInRegion::class.java, "(location |)%location% is in guild %object% region",
                "(location |)%location% is(n't| not) in guild %object% region"
            )
        }
    }

    override fun check(event: Event?): Boolean {
        return try {
            getGuild(event)?.isLocationInGuildRegion(getValue(event))?.xor(isNegated)!!
        } catch (ex: Exception) {
            !isNegated
        }
    }

}