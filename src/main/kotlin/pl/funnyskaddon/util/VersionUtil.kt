package pl.funnyskaddon.util

import org.apache.commons.io.IOUtils
import org.bukkit.Bukkit
import java.io.InputStream
import java.net.URL

class VersionUtil {
    companion object {
        fun getLatestVersion(link: String): String? {
            var input: InputStream? = null
            try {
                input = URL(link).openStream()
            } catch (ex: Exception) {
                Bukkit.getLogger().info("Unable to check for updates!")
                ex.printStackTrace()
            }
            try {
                return IOUtils.readLines(input)[0]
            } catch (ex: Exception) {
                Bukkit.getLogger().info("Unable to determine update!")
                ex.printStackTrace()
            } finally {
                IOUtils.closeQuietly(input)
            }
            return null
        }
    }
}