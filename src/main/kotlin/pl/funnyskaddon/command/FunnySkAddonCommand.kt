package pl.funnyskaddon.command

import ch.njol.skript.Skript
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.docs.FunnyHTMLGenerator
import pl.funnyskaddon.extension.color
import java.io.File

class FunnySkAddonCommand(private val plugin: FunnySkAddon) : CommandExecutor {

    override fun onCommand(sender: CommandSender, cmd: Command?, label: String?, args: Array<String?>?): Boolean {
        if (!sender.hasPermission("funnyskaddon.cmd")) {
            sender.sendMessage(plugin.configuration.messages.noPerm?.color())
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

        sender.sendMessage("&8&m-----------------------------------------------------".color())
        sender.sendMessage("  &bInformacje o Dodatku".color())
        sender.sendMessage("   &7Wersja: &f".color() + plugin.description.version)
        sender.sendMessage("   &7Autorzy: &f".color() + plugin.description.authors)
        sender.sendMessage("   &7Opis: &f".color() + plugin.description.description)
        sender.sendMessage("   &7Strona internetowa: &f".color() + plugin.description.website)
        sender.sendMessage("  &bWersje:".color())
        sender.sendMessage("   &7Java: &f$java".color())
        sender.sendMessage("   &7Bukkit: &f$engine".color())
        sender.sendMessage("   &7FunnySkAddon: &f$fsaVersion".color())
        sender.sendMessage("   &7FunnyGuilds: &f$fgVersion".color())
        sender.sendMessage("   &7Skript: &f$skriptVersion".color())
        sender.sendMessage("&8&m-----------------------------------------------------".color())

        return true
    }

}