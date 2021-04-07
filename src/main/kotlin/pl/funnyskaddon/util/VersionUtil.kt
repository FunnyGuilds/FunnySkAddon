package pl.funnyskaddon.util

import org.apache.commons.io.IOUtils
import org.bukkit.Bukkit
import java.io.InputStream
import java.net.URL
import java.nio.charset.StandardCharsets

class VersionUtil {
    companion object {
        private fun getLatestVersions(url: String): MutableList<String>? {
            var input: InputStream? = null

            try {
                input = URL(url).openStream()
            } catch (ex: Exception) {
                Bukkit.getLogger().info("Unable to check for updates!")
                ex.printStackTrace()
            }

            try {
                return IOUtils.readLines(input, StandardCharsets.UTF_8)
            } catch (ex: Exception) {
                Bukkit.getLogger().info("Unable to determine update!")
                ex.printStackTrace()
            } finally {
                IOUtils.close(input)
            }

            return null
        }

        fun getLatestVersion(url: String, onlyFullRelease: Boolean): String? {
            val versions: List<String>? = getLatestVersions(url)

            if (versions != null) {
                var value: String? = versions[0]

                if (!onlyFullRelease) {
                    value = versions[1]
                }

                return value
            }

            return null
        }
    }
}