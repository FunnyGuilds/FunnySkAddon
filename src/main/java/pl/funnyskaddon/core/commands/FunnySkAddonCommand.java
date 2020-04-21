package pl.funnyskaddon.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.funnyskaddon.core.FunnySkAddon;
import pl.funnyskaddon.core.utils.BasicUtil;

public class FunnySkAddonCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("funnyskaddon")){
            if(sender.hasPermission("funnyskaddon.cmd")) {
                String java = System.getProperty("java.version");
                String engine = Bukkit.getServer().getBukkitVersion();
                String fsaVersion = FunnySkAddon.getInst().getDescription().getVersion();
                String fgVersion = Bukkit.getPluginManager().getPlugin("FunnyGuilds").getDescription().getVersion();
                String skriptVersion = Bukkit.getPluginManager().getPlugin("Skript").getDescription().getVersion();

                sender.sendMessage(BasicUtil.color("&8&m-----------------------------------------------------"));
                sender.sendMessage(BasicUtil.color("  &bInformacje o Dodatku"));
                sender.sendMessage(BasicUtil.color("   &7Wersja: &f" + FunnySkAddon.getInst().getDescription().getVersion()));
                sender.sendMessage(BasicUtil.color("   &7Autorzy: &f" + FunnySkAddon.getInst().getDescription().getAuthors()));
                sender.sendMessage(BasicUtil.color("   &7Opis: &f" + FunnySkAddon.getInst().getDescription().getDescription()));
                sender.sendMessage(BasicUtil.color("   &7Strona internetowa: &f" + FunnySkAddon.getInst().getDescription().getWebsite()));
                sender.sendMessage(BasicUtil.color("  &bWersje:"));
                sender.sendMessage(BasicUtil.color("   &7Java: &f" + java));
                sender.sendMessage(BasicUtil.color("   &7Bukkit: &f" + engine));
                sender.sendMessage(BasicUtil.color("   &7FunnySkAddon: &f" + fsaVersion));
                sender.sendMessage(BasicUtil.color("   &7FunnyGuilds: &f" + fgVersion));
                sender.sendMessage(BasicUtil.color("   &7Skript: &f" + skriptVersion));
                sender.sendMessage(BasicUtil.color("&8&m-----------------------------------------------------"));
            } else {
                sender.sendMessage("&bFunnySkAddon &8> &7Niestety nie posiadasz uprawnien.");
            }
        }
        return true;
    }
}
