package pl.funnyskaddon.events.guilds

import net.dzikoysk.funnyguilds.event.guild.GuildCreateEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import pl.funnyskaddon.FunnySkAddon

class GuildCreateListener(val plugin: FunnySkAddon) : Listener {

    @EventHandler
    fun onCreate(event: GuildCreateEvent) {
        plugin.server.scheduler.runTaskLater(plugin, {
            plugin.server.pluginManager.callEvent(CustomGuildCreateEvent(event.doer.player, event.guild))
        }, 2)
    }

}