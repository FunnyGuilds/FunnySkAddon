package pl.funnyskaddon.events.rank

import net.dzikoysk.funnyguilds.basic.rank.Rank
import net.dzikoysk.funnyguilds.event.FunnyEvent
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class CustomKillPointsChangeEvent(
    val eventCause: FunnyEvent.EventCause,
    val attacker: Player,
    val victim: Player,
    val change: Int,
    val rank: Rank
) : Event() {
    val handlerList: HandlerList = HandlerList()

    override fun getHandlers(): HandlerList {
        return handlerList
    }

}