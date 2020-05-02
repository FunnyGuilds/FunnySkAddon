package pl.funnyskaddon.core;

import ch.njol.skript.Skript;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.data.configs.PluginConfiguration;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.funnyskaddon.bstats.bukkit.Metrics;
import pl.funnyskaddon.core.commands.FunnySkAddonCommand;
import pl.funnyskaddon.core.data.Config;
import pl.funnyskaddon.core.fix.GuildCreateListener;
import pl.funnyskaddon.core.fix.PointsChangeListener;
import pl.funnyskaddon.core.schedulers.UpdateCheckScheduler;
import pl.funnyskaddon.skript.SkriptLoader;

import java.io.InputStream;
import java.net.URL;

public class FunnySkAddon extends JavaPlugin implements Listener {

    private static PluginConfiguration funnyguildsConfiguration;
    private Config pluginConfiguration;

    public static String getLatestVersion(String link) {
        InputStream in = null;
        try {
            in = new URL(link).openStream();
        } catch (Exception ex) {
            Bukkit.getLogger().info("Unable to check for updates!");
            ex.printStackTrace();
        }
        try {
            return IOUtils.readLines(in).get(0);
        } catch (Exception ex) {
            Bukkit.getLogger().info("Unable to determine update!");
            ex.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
        return null;
    }

    public static PluginConfiguration getFunnyGuildsConfiguration() {
        return funnyguildsConfiguration;
    }

    @Override
    public void onEnable() {
        StringBuilder sB = new StringBuilder();
        boolean shouldStart = true;
        if (Bukkit.getServer().getPluginManager().getPlugin("Skript") == null) {
            sB.append("Skript");
            shouldStart = false;
        }
        if (Bukkit.getServer().getPluginManager().getPlugin("FunnyGuilds") == null) {
            if (!shouldStart) {
                sB.append(" i FunnyGuilds");
            } else {
                shouldStart = false;
                sB.append("FunnyGuilds");
            }
        }
        if (!shouldStart) {
            Bukkit.getLogger().severe("[FSA] Uruchamianie wstrzymane!");
            Bukkit.getLogger().severe("[FSA] Powod: Brak pluginu " + sB.toString() + "!");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        pluginConfiguration = new Config(this);
        pluginConfiguration.loadConfig();

        funnyguildsConfiguration = FunnyGuilds.getInstance().getPluginConfiguration();

        getCommand("funnyskaddon").setExecutor(new FunnySkAddonCommand(this));

        Bukkit.getServer().getPluginManager().registerEvents(new PointsChangeListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new GuildCreateListener(this), this);

        Skript.registerAddon(this);
        SkriptLoader skriptLoader = new SkriptLoader(this);
        skriptLoader.loadConditions();
        skriptLoader.loadEffects();
        skriptLoader.loadEvents();
        skriptLoader.loadExpressions();

        new UpdateCheckScheduler(this).start();

        int pluginId = 6363;
        new Metrics(this, pluginId);
    }

    public Config getPluginConfiguration() {
        return pluginConfiguration;
    }
}
