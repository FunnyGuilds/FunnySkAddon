package pl.funnyskaddon.events.guilds

import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.event.FunnyEvent
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class CustomGuildCreateEvent(
    val player: Player,
    val guild: Guild,
    val eventCause: FunnyEvent.EventCause
) : Event() {

    val handlerList: HandlerList = HandlerList()

    override fun getHandlers(): HandlerList {
        return handlerList
    }

}