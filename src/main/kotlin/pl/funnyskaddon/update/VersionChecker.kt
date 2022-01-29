package pl.funnyskaddon.update

import org.apache.commons.io.IOUtils
import org.bukkit.Bukkit
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.nio.charset.StandardCharsets

class VersionChecker {
    companion object {
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
    }
}