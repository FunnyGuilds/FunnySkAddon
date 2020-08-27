package pl.funnyskaddon

import ch.njol.skript.Skript
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.data.configs.PluginConfiguration
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import pl.funnyskaddon.bstats.bukkit.Metrics
import pl.funnyskaddon.commands.FunnySkAddonCommand
import pl.funnyskaddon.data.Configuration
import pl.funnyskaddon.events.guilds.GuildCreateListener
import pl.funnyskaddon.events.rank.PointsChangeListener
import pl.funnyskaddon.schedulers.UpdateCheckScheduler
import pl.funnyskaddon.skript.SkriptLoader

class FunnySkAddon : JavaPlugin() {

    lateinit var configuration: Configuration

    companion object {
        lateinit var fgConfiguration: PluginConfiguration;
    }

    override fun onEnable() {
        val pluginManager: PluginManager = this.server.pluginManager

        val missing: StringBuilder = StringBuilder()
        var shouldStart = true;

        if (pluginManager.getPlugin("Skript") == null) {
            missing.append("Skript")
            shouldStart = false
        }
        if (pluginManager.getPlugin("FunnyGuilds") == null) {
            if (shouldStart) {
                missing.append("FunnyGuilds")
                shouldStart = false
            } else {
                missing.append(" i FunnyGuilds")
            }
        }

        if (shouldStart.not()) {
            logger.warning("[FSA] Uruchamianie wstrzymane")
            logger.warning("[FSA] Powod: Brak pluginu $missing!")
            pluginManager.disablePlugin(this)
            return
        }

        configuration = Configuration(this)
        configuration.loadConfiguration()

        fgConfiguration = FunnyGuilds.getInstance().pluginConfiguration

        pluginManager.registerEvents(GuildCreateListener(this), this)
        pluginManager.registerEvents(PointsChangeListener(this), this)

        getCommand("funnyskaddon").executor = FunnySkAddonCommand(this)

        UpdateCheckScheduler(this).start()

        Skript.registerAddon(this)

        val skriptLoader = SkriptLoader()
        skriptLoader.loadConditions()
        skriptLoader.loadEffects()
        skriptLoader.loadEvents()
        skriptLoader.loadExpressions()

        val pluginId = 6363
        Metrics(this, pluginId)
    }

}