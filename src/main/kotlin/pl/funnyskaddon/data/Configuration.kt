package pl.funnyskaddon.data

import org.bukkit.configuration.ConfigurationSection
import pl.funnyskaddon.FunnySkAddon

class Configuration(private val plugin: FunnySkAddon) {

    var updateCheck = false
    var onlyFullReleases = true
    var simpleUpdateCheck = true
    var checkTime = 180

    var noPerm: String? = null

    var devMode = false

    fun loadConfiguration() {
        plugin.saveDefaultConfig()
        val config: ConfigurationSection = plugin.config

        updateCheck = config.getBoolean("update.check")
        onlyFullReleases = config.getBoolean("update.onlyFullReleases")
        simpleUpdateCheck = config.getBoolean("update.simple")
        checkTime = config.getInt("update.time")
        noPerm = config.getString("message.noPerm")
        devMode = config.getBoolean("devMode")
    }

}