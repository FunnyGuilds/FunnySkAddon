package pl.funnyskaddon.event.guild

import net.dzikoysk.funnyguilds.event.guild.GuildPreCreateEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import pl.funnyskaddon.FunnySkAddon

class GuildCreateListener(private val plugin: FunnySkAddon) : Listener {

    @EventHandler
    fun onCreate(event: GuildPreCreateEvent) {
        plugin.server.scheduler.runTaskLater(plugin, {
            plugin.server.pluginManager.callEvent(
                CustomGuildCreateEvent(
                    event.doer.player,
                    event.guild,
                    event.eventCause
                )
            )
        }, 2)
    }

}