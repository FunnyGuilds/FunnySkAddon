package pl.funnyskaddon.event.rank

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

        val change = event.pointsChange
        val attacker = event.doer.player

        if (event.doer == event.affected) {
            return
        }
        val victim = event.affected.player

        if (attacker == null || victim == null) {
            return
        }
        plugin.server.pluginManager.callEvent(
            CustomKillPointsChangeEvent(
                event.eventCause,
                attacker,
                victim,
                change,
                event.affected.rank
            )
        )
    }

}