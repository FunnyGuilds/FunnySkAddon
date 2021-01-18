package pl.funnyskaddon.schedulers

import org.bukkit.Bukkit
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.util.VersionUtil
import pl.funnyskaddon.util.color
import java.util.regex.Pattern

class UpdateCheckScheduler(private val plugin: FunnySkAddon) {

    private val PATTERN: Pattern = Pattern.compile("^([0-9]|\\.)+$")

    fun start() {
        val description = plugin.description
        plugin.server.scheduler.runTaskTimer(plugin, {
            if (plugin.configuration.updateCheck) {
                val url = "https://github.com/FunnyGuilds/FunnySkAddon/"
                val fullRelease = PATTERN.matcher(description.version).matches()
                val latestVersion: String? = VersionUtil.getLatestVersion(
                    "https://raw.githubusercontent.com/FunnyGuilds/FunnySkAddon/master/VERSION",
                    plugin.configuration.onlyFullReleases && fullRelease
                )
                if (!description.version.equals(latestVersion, true)) {
                    if (plugin.configuration.simpleUpdateCheck) {
                        plugin.logger.warning("[FSA] Wersja pluginu: " + description.version)
                        plugin.logger.warning("[FSA] Najnowsza wersja pluginu: " + latestVersion.toString())
                        plugin.logger.warning("[FSA] Wszystkie wersje: " + url)
                    } else {
                        Bukkit.getConsoleSender()
                            .sendMessage(("&8[&bFSA&8] &7Wersja pluginu: &f" + description.version).color())
                        Bukkit.getConsoleSender()
                            .sendMessage(("&8[&bFSA&8] &7Najnowsza wersja pluginu: &f" + latestVersion.toString()).color())
                        Bukkit.getConsoleSender().sendMessage(("&8[&bFSA&8] &7Wszystkie wersje: &f" + url).color())
                    }
                }
            }
        }, 0, plugin.configuration.checkTime * 60L * 20L)
    }

}