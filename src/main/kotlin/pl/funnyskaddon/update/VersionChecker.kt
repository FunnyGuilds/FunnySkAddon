package pl.funnyskaddon.update

import org.apache.commons.io.IOUtils
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.extension.color
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.regex.Pattern

class VersionChecker {
    companion object {

        private val FULL_RELEASE_PATTERN: Pattern = Pattern.compile("^([0-9]|\\.)+$")

        private fun getLatestVersions(url: String): List<String> {
            val input: InputStream

            try {
                input = URL(url).openStream()
            } catch (ex: IOException) {
                Bukkit.getLogger().severe("Unable to check for updates")
                ex.printStackTrace()
                return emptyList()
            }

            try {
                return IOUtils.readLines(input, StandardCharsets.UTF_8)
            } catch (ex: IOException) {
                Bukkit.getLogger().severe("Unable to determine update")
                ex.printStackTrace()
            } finally {
                IOUtils.close(input)
            }

            return emptyList()
        }

        fun getLatestVersion(url: String, onlyFullRelease: Boolean): String {
            val versions: List<String> = getLatestVersions(url)

            if (versions.isNotEmpty()) {
                var value: String = versions[0]

                if (!onlyFullRelease) {
                    value = versions[1]
                }

                return value
            }

            return "Unknown"
        }

        fun checkUpdate(plugin: FunnySkAddon, receiver: CommandSender) {
            val description = plugin.description

            val fullRelease = FULL_RELEASE_PATTERN.matcher(description.version).matches()
            val latestVersion: String = getLatestVersion(
                "https://raw.githubusercontent.com/FunnyGuilds/FunnySkAddon/master/VERSION",
                plugin.configuration.update.onlyFullReleases && fullRelease
            )

            if (description.version.equals(latestVersion, true)) {
                return
            }

            sendUpdateMessage(receiver, description.version, latestVersion)
        }

        private fun sendUpdateMessage(receiver: CommandSender, currentVersion: String, latestVersion: String) {
            val github = "https://github.com/funnyguilds/funnyskaddon"
            val discord = "https://discord.com/invite/CYvyq3u"

            listOf(
                "&8------------- &bFunnySkAddon &8-------------",
                "&7Dostepna jest nowa wersja &bFunnySkAddon&7!",
                "&7Obecna: &b$currentVersion",
                "&7Najnowsza: &b$latestVersion",
                "&7GitHub: &b$github",
                "&7Discord: &b$discord",
                "&8------------- &bFunnySkAddon &8-------------",
            ).forEach {
                receiver.sendMessage(it.color())
            }
        }

    }
}