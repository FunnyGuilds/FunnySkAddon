package pl.funnyskaddon.events.rank

import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.rank.PointsChangeEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import pl.funnyskaddon.FunnySkAddon

class PointsChangeListener(private val plugin: FunnySkAddon) : Listener {

    @EventHandler
    fun onChange(event: PointsChangeEvent) {
        if (event.eventCause != FunnyEvent.EventCause.USER) {
            return
        }

        val change = event.change
        val attacker = event.doer.player

        if (event.doer == event.rank.user) {
            return
        }
        val victim = event.rank.user.player

        if (attacker == null || victim == null) {
            return
        }
        plugin.server.pluginManager.callEvent(CustomKillPointsChangeEvent(event.eventCause, attacker, victim, change, event.rank))
    }

}