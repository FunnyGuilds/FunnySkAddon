package pl.funnyskaddon.update

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.extension.color
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
            val description = plugin.description

            val fullRelease = PATTERN.matcher(description.version).matches()
            val latestVersion: String = VersionChecker.getLatestVersion(
                "https://raw.githubusercontent.com/FunnyGuilds/FunnySkAddon/master/VERSION",
                plugin.configuration.update.onlyFullReleases && fullRelease
            )

            if (description.version.equals(latestVersion, true)) {
                return@runTaskTimerAsynchronously
            }

            sendUpdateMessage(Bukkit.getConsoleSender(), latestVersion.toString())
        }, 0, plugin.configuration.update.checkTime * 60L * 20L)
    }

    private fun sendUpdateMessage(receiver: CommandSender, latestVersion: String) {
        val github = "https://github.com/funnyguilds/funnyskaddon"
        val discord = "https://discord.com/invite/CYvyq3u"

        listOf(
            "&8------------- &bFunnySkAddon &8-------------",
            "&7Dostepna jest nowa wersja &bFunnySkAddon&7!",
            "&7Obecna: &b" + plugin.description.version,
            "&7Najnowsza: &b$latestVersion",
            "&7GitHub: &b$github",
            "&7Discord: &b$discord",
            "&8------------- &bFunnySkAddon &8-------------",
        ).forEach {
            receiver.sendMessage(it.color())
        }
    }

}