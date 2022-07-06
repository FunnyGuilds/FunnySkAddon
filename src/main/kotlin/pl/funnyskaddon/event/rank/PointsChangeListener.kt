package pl.funnyskaddon.event.rank

import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.rank.PointsChangeEvent
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.extension.getPlayerOption

class PointsChangeListener(private val plugin: FunnySkAddon) : Listener {

    @EventHandler
    fun onChange(event: PointsChangeEvent) {
        if (event.eventCause != FunnyEvent.EventCause.USER) {
            return
        }

        val change = event.pointsChange
        val attacker = event.doer.flatMap(User::getPlayerOption)

        if (event.doer == event.affected) {
            return
        }
        val victim = event.affected.getPlayerOption()

        if (attacker.isEmpty || victim.isEmpty) {
            return
        }
        plugin.server.pluginManager.callEvent(
            CustomKillPointsChangeEvent(
                event.eventCause,
                attacker.get(),
                victim.get(),
                change,
                event.affected.rank
            )
        )
    }

}