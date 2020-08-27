package pl.funnyskaddon.events.rank

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class CustomKillPointsChangeEvent(
    val attacker: Player,
    val victim: Player,
    val change: Int
) : Event() {
    val handlerList: HandlerList = HandlerList()

    override fun getHandlers(): HandlerList {
        return handlerList
    }

}