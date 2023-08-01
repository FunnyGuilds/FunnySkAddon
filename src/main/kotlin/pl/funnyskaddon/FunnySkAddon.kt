package pl.funnyskaddon

import ch.njol.skript.Skript
import ch.njol.skript.SkriptAddon
import eu.okaeri.configs.ConfigManager
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.config.PluginConfiguration
import org.bstats.bukkit.Metrics
import org.bstats.charts.SimplePie
import org.bukkit.plugin.java.JavaPlugin
import pl.funnyskaddon.command.FunnySkAddonCommand
import pl.funnyskaddon.data.Configuration
import pl.funnyskaddon.update.PlayerJoinListener
import pl.funnyskaddon.update.UpdateCheckTask
import java.io.File

class FunnySkAddon : JavaPlugin() {

    lateinit var configuration: Configuration

    companion object {
        lateinit var fgConfiguration: PluginConfiguration
        lateinit var addon: SkriptAddon
    }

    override fun onEnable() {
        val pluginManager = this.server.pluginManager

        val missing = StringBuilder()
        var shouldStart = true

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

        if (!shouldStart) {
            logger.warning("[FSA] Uruchamianie wstrzymane")
            logger.warning("[FSA] Powod: Brak pluginu $missing!")
            pluginManager.disablePlugin(this)
            return
        }

        configuration = ConfigManager.create(Configuration::class.java) {
            it.withConfigurer(YamlBukkitConfigurer())
            it.withBindFile(File(this.dataFolder, "config.yml"))
            it.saveDefaults()
            it.load(true)
        }
        fgConfiguration = FunnyGuilds.getInstance().pluginConfiguration

        getCommand("funnyskaddon").executor = FunnySkAddonCommand(this)

        pluginManager.registerEvents(PlayerJoinListener(this), this)

        UpdateCheckTask(this).start()

        addon = Skript.registerAddon(this)
        addon.loadClasses(
            "pl.funnyskaddon.skript",
            "condition.guild",
            "condition.player",
            "effect.guild",
            "effect.player",
            "event.guild",
            "event.guild.ally",
            "event.guild.member",
            "event.rank",
            "expression.event",
            "expression.guild",
            "expression.player",
            "expression.top",
            "expression.config",
            "type"
        )

        val pluginId = 6363
        val metrics = Metrics(this, pluginId)
        metrics.addCustomChart(SimplePie("funnyguilds_version") {
            this.description.version
        })
    }

}