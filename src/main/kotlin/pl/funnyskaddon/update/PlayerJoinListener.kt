package pl.funnyskaddon.update

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import pl.funnyskaddon.FunnySkAddon

class PlayerJoinListener(private val plugin: FunnySkAddon) : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        if (!plugin.configuration.update.updateCheck) {
            return
        }

        if (!event.player.hasPermission("funnyskaddon.update.check")) {
            return
        }

        plugin.server.scheduler.runTaskAsynchronously(plugin) {
            VersionChecker.checkUpdate(plugin, event.player)
        }
    }

}