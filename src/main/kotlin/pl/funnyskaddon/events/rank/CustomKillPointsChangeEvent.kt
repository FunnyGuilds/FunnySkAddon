package pl.funnyskaddon.events.rank

import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.user.UserRank
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class CustomKillPointsChangeEvent(
    val eventCause: FunnyEvent.EventCause,
    val attacker: Player,
    val victim: Player,
    val change: Int,
    val rank: UserRank
) : Event(), Cancellable {

    private var cancelled = false

    companion object {
        @JvmStatic
        val handlerList: HandlerList = HandlerList()
    }

    override fun isCancelled(): Boolean {
        return cancelled
    }

    override fun setCancelled(cancelled: Boolean) {
        this.cancelled = cancelled
    }

    override fun getHandlers(): HandlerList {
        return handlerList
    }

}