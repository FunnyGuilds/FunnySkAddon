package pl.funnyskaddon.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.funnyskaddon.core.FunnySkAddon;
import pl.funnyskaddon.core.utils.BasicUtil;

public class FunnySkAddonCommand implements CommandExecutor {

    private final FunnySkAddon plugin;

    public FunnySkAddonCommand(FunnySkAddon plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("funnyskaddon.cmd")) {
            sender.sendMessage(BasicUtil.color(plugin.getPluginConfiguration().noPerm));
            return true;
        }
        String java = System.getProperty("java.version");
        String engine = Bukkit.getServer().getBukkitVersion();
        String fsaVersion = plugin.getDescription().getVersion();
        String fgVersion = Bukkit.getPluginManager().getPlugin("FunnyGuilds").getDescription().getVersion();
        String skriptVersion = Bukkit.getPluginManager().getPlugin("Skript").getDescription().getVersion();

        sender.sendMessage(BasicUtil.color("&8&m-----------------------------------------------------"));
        sender.sendMessage(BasicUtil.color("  &bInformacje o Dodatku"));
        sender.sendMessage(BasicUtil.color("   &7Wersja: &f" + plugin.getDescription().getVersion()));
        sender.sendMessage(BasicUtil.color("   &7Autorzy: &f" + plugin.getDescription().getAuthors()));
        sender.sendMessage(BasicUtil.color("   &7Opis: &f" + plugin.getDescription().getDescription()));
        sender.sendMessage(BasicUtil.color("   &7Strona internetowa: &f" + plugin.getDescription().getWebsite()));
        sender.sendMessage(BasicUtil.color("  &bWersje:"));
        sender.sendMessage(BasicUtil.color("   &7Java: &f" + java));
        sender.sendMessage(BasicUtil.color("   &7Bukkit: &f" + engine));
        sender.sendMessage(BasicUtil.color("   &7FunnySkAddon: &f" + fsaVersion));
        sender.sendMessage(BasicUtil.color("   &7FunnyGuilds: &f" + fgVersion));
        sender.sendMessage(BasicUtil.color("   &7Skript: &f" + skriptVersion));
        sender.sendMessage(BasicUtil.color("&8&m-----------------------------------------------------"));
        return true;
    }
}
