package pl.funnyskaddon.events.guilds

import net.dzikoysk.funnyguilds.basic.guild.Guild
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class CustomGuildCreateEvent(
    val player: Player,
    val guild: Guild
) : Event() {

    val handlerList: HandlerList = HandlerList()

    override fun getHandlers(): HandlerList {
        return handlerList
    }

}