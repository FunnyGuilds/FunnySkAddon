package pl.funnyskaddon.data

import org.bukkit.configuration.ConfigurationSection
import pl.funnyskaddon.FunnySkAddon

class Configuration(private val plugin: FunnySkAddon) {

    var updateCheck = false

    var noPerm: String? = null


    fun loadConfiguration() {
        plugin.saveDefaultConfig()
        val config: ConfigurationSection = plugin.config

        updateCheck = config.getBoolean("update.check")
        noPerm = config.getString("message.noperm")
    }

}