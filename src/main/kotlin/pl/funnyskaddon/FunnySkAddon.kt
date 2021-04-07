package pl.funnyskaddon

import ch.njol.skript.Skript
import ch.njol.skript.SkriptAddon
import eu.okaeri.configs.ConfigManager
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.data.configs.PluginConfiguration
import org.bstats.bukkit.Metrics
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import pl.funnyskaddon.commands.FunnySkAddonCommand
import pl.funnyskaddon.data.Configuration
import pl.funnyskaddon.events.guilds.GuildCreateListener
import pl.funnyskaddon.events.rank.PointsChangeListener
import pl.funnyskaddon.schedulers.UpdateCheckScheduler
import java.io.File


class FunnySkAddon : JavaPlugin() {

    lateinit var configuration: Configuration

    companion object {
        lateinit var fgConfiguration: PluginConfiguration
        lateinit var addon: SkriptAddon
    }

    override fun onEnable() {
        val pluginManager: PluginManager = this.server.pluginManager

        val missing: StringBuilder = StringBuilder()
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

        if (shouldStart.not()) {
            logger.warning("[FSA] Uruchamianie wstrzymane")
            logger.warning("[FSA] Powod: Brak pluginu $missing!")
            pluginManager.disablePlugin(this)
            return
        }

        configuration = ConfigManager.create(Configuration::class.java) {
            it.withConfigurer(YamlBukkitConfigurer())
            it.withBindFile(File(dataFolder, "config.yml"))
            it.saveDefaults()
            it.load(true)
        }

        fgConfiguration = FunnyGuilds.getInstance().pluginConfiguration

        pluginManager.registerEvents(GuildCreateListener(this), this)
        pluginManager.registerEvents(PointsChangeListener(this), this)

        getCommand("funnyskaddon").executor = FunnySkAddonCommand(this)

        UpdateCheckScheduler(this).start()

        addon = Skript.registerAddon(this)

        addon.loadClasses(
            "pl.funnyskaddon.skript",
            "conditions.guild",
            "conditions.player",
            "effects.guild",
            "effects.player",
            "events.guild",
            "events.guild.ally",
            "events.guild.member",
            "events.rank",
            "expressions.events",
            "expressions.guild",
            "expressions.player",
            "expressions.top",
            "expressions.config"
        )

        val pluginId = 6363
        val metrics = Metrics(this, pluginId)

        metrics.addCustomChart(Metrics.SimplePie("funnyguilds_version") {
            this.server.pluginManager.getPlugin("FunnyGuilds").description.version
        })
    }

}