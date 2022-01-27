package pl.funnyskaddon.skript.effect.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.PlayerEffect
import pl.funnyskaddon.skript.effect.guild.GuildAddMemberEffect

@FunnyDoc
@Name("Set Guild")
@Description(
    "Ustawia gildie gracza (dołącza go do niej)",
    "Alternatywa: add %offlineplayer% to %object%(|'s) guild members"
)
@Examples(
    "set player's guild to \"AC4U\"",
)
class PlayerSetGuildEffect : PlayerEffect<Any>(false) {

    companion object {

        private val GUILD_ADD_MEMBER = GuildAddMemberEffect()

        init {
            Skript.registerEffect(PlayerSetGuildEffect::class.java, "set %offlineplayer%['s] guild to %object%")
        }
    }

    override fun execute(event: Event) {
        GUILD_ADD_MEMBER.execute(event)
    }

}