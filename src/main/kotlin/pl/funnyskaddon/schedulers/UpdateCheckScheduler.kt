package pl.funnyskaddon.schedulers

import org.bukkit.Bukkit
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.util.VersionUtil
import pl.funnyskaddon.util.color

class UpdateCheckScheduler(private val plugin: FunnySkAddon) {

    fun start() {
        val description = plugin.description
        plugin.server.scheduler.runTaskTimer(plugin, {
            if (plugin.configuration.updateCheck) {
                val latestVersion: String? =
                    VersionUtil.getLatestVersion("https://raw.githubusercontent.com/MLGroupMC/FunnySkAddon/master/VERSION")
                if (description.version.equals(latestVersion, true).not()) {
                    if (plugin.configuration.simpleUpdateCheck) {
                        plugin.logger.warning("[FSA] Wersja pluginu: " + description.version)
                        plugin.logger.warning("[FSA] Najnowsza wersja pluginu: " + latestVersion.toString())
                        plugin.logger.warning("[FSA] Wszystkie wersje: https://github.com/MLGroupMC/FunnySkAddon/releases/")
                    } else {
                        Bukkit.getConsoleSender().sendMessage(("&8[&bFSA&8] &7Wersja pluginu: &f" + description.version).color())
                        Bukkit.getConsoleSender().sendMessage(("&8[&bFSA&8] &7Najnowsza wersja pluginu: &f" + latestVersion.toString()).color())
                        Bukkit.getConsoleSender().sendMessage(("&8[&bFSA&8] &7Wszystkie wersje: &fhttps://github.com/MLGroupMC/FunnySkAddon/releases/").color())
                    }
                }
            }
        }, 0, 216000)
    }

}