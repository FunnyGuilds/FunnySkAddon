package pl.funnyskaddon.update

import org.bukkit.Bukkit
import pl.funnyskaddon.FunnySkAddon
import java.util.regex.Pattern

class UpdateCheckScheduler(private val plugin: FunnySkAddon) {

    companion object {
        private val PATTERN: Pattern = Pattern.compile("^([0-9]|\\.)+$")
    }

    fun start() {
        if (!plugin.configuration.update.updateCheck) {
            return
        }

        plugin.server.scheduler.runTaskTimerAsynchronously(plugin, {
           VersionChecker.checkUpdate(plugin, Bukkit.getConsoleSender())
        }, 0, plugin.configuration.update.checkTime * 60L * 20L)
    }

}