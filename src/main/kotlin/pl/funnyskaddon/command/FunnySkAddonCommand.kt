package pl.funnyskaddon.command

import ch.njol.skript.Skript
import ch.njol.skript.SkriptAddon
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import panda.std.stream.PandaStream
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.docs.FunnyHTMLGenerator
import pl.funnyskaddon.extension.color
import pl.funnyskaddon.extension.sendColorMessage
import java.io.File

class FunnySkAddonCommand(private val plugin: FunnySkAddon) : CommandExecutor {

    override fun onCommand(sender: CommandSender, cmd: Command?, label: String?, args: Array<String?>?): Boolean {
        if (!sender.hasPermission("funnyskaddon.cmd")) {
            sender.sendColorMessage(plugin.configuration.messages.noPerm.color())
            return true
        }

        if (args != null) {
            if (args.isNotEmpty() && args[0].equals(
                    "gen-docs",
                    true
                ) && sender.hasPermission("funnyskaddon.cmd.docs")
            ) {
                if (plugin.configuration.devMode) {
                    val templateDir = File(plugin.dataFolder, "docs/templates/")
                    if (!templateDir.exists()) {
                        Skript.info(sender, "Documentation templates not found. Cannot generate docs!")
                        return true
                    }
                    val outputDir = File(plugin.dataFolder, "docs/output")
                    outputDir.mkdirs()

                    val generator = FunnyHTMLGenerator(plugin, templateDir, outputDir)
                    Skript.info(sender, "Generating docs...")
                    generator.generateDocumentation() // Try to generate docs... hopefully
                    Skript.info(sender, "Documentation generated!")

                    return true
                }
            }
        }

        val java = System.getProperty("java.version")
        val engine = Bukkit.getServer().bukkitVersion
        val fsaVersion = plugin.description.version
        val fgVersion = plugin.server.pluginManager.getPlugin("FunnyGuilds").description.version
        val skriptVersion = plugin.server.pluginManager.getPlugin("Skript").description.version

        val skriptAddons = PandaStream.of(Skript.getAddons())
            .map(SkriptAddon::getName)
            .toSet()
        val plugins = PandaStream.of(plugin.server.pluginManager.plugins.toSet())
            .map { plugin -> plugin.name }
            .toSet()

        sender.sendColorMessage("&8&m-----------------------------------------------------")
        sender.sendColorMessage("  &bInformacje o Dodatku")
        sender.sendColorMessage("   &7Wersja: &f${plugin.description.version}")
        sender.sendColorMessage("   &7Autorzy: &f${plugin.description.authors}")
        sender.sendColorMessage("   &7Opis: &f${plugin.description.description}")
        sender.sendColorMessage("   &7Strona internetowa: &f${plugin.description.website}")
        sender.sendColorMessage(" ")
        sender.sendColorMessage("  &bWersje:")
        sender.sendColorMessage("   &7Java: &f$java")
        sender.sendColorMessage("   &7Bukkit: &f$engine")
        sender.sendColorMessage("   &7FunnySkAddon: &f$fsaVersion")
        sender.sendColorMessage("   &7FunnyGuilds: &f$fgVersion")
        sender.sendColorMessage("   &7Skript: &f$skriptVersion")
        sender.sendColorMessage(" ")
        sender.sendColorMessage("  &bPluginy:")
        sender.sendColorMessage("   &7Dodatki: &f$skriptAddons")
        sender.sendColorMessage("   &7Wtyczki: &f$plugins")
        sender.sendColorMessage("&8&m-----------------------------------------------------")

        return true
    }

}