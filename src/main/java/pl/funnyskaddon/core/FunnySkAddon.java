package pl.funnyskaddon.core;

import bstats.bukkit.Metrics;
import ch.njol.skript.Skript;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.data.configs.PluginConfiguration;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.funnyskaddon.core.commands.FunnySkAddonCommand;
import pl.funnyskaddon.core.fix.GuildCreateListener;
import pl.funnyskaddon.core.fix.PointsChangeListener;
import pl.funnyskaddon.core.loaders.SkriptLoaders;
import pl.funnyskaddon.core.schedulers.TopUpdateScheduler;
import pl.funnyskaddon.core.schedulers.UpdateCheckScheduler;

import java.io.InputStream;
import java.net.URL;

public class FunnySkAddon extends JavaPlugin implements Listener {

	private static FunnySkAddon instance;

	public static PluginConfiguration pc;
	
	@Override
	public void onEnable() {
        StringBuilder sB = new StringBuilder();
        boolean shouldStart = true;
        if(Bukkit.getServer().getPluginManager().getPlugin("Skript") == null) {
            sB.append("Skript");
            shouldStart = false;
        }
        if(Bukkit.getServer().getPluginManager().getPlugin("FunnyGuilds") == null) {
            if(!shouldStart) {
                sB.append(" i FunnyGuilds");
            } else {
                shouldStart = false;
                sB.append("FunnyGuilds");
            }
        }
        if(!shouldStart) {
            Bukkit.getLogger().severe("[FSA] Uruchamianie wstrzymane!");
            Bukkit.getLogger().severe("[FSA] Powod: Brak pluginu " + sB.toString() + "!");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        instance = this;

        int pluginId = 6363; // <-- Replace with the id of your plugin!
        new Metrics(this, pluginId);

		pc = FunnyGuilds.getInstance().getPluginConfiguration();

		getCommand("funnyskaddon").setExecutor(new FunnySkAddonCommand());
		Bukkit.getServer().getPluginManager().registerEvents(new PointsChangeListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new GuildCreateListener(), this);

        Skript.registerAddon(this);
		SkriptLoaders.loadExpressions();
		SkriptLoaders.loadEvents();
		SkriptLoaders.loadEffects();
		SkriptLoaders.loadConditions();

		TopUpdateScheduler.start();
		UpdateCheckScheduler.start();
        this.saveDefaultConfig();
	}
	
	public static String getLatestVersion(String link){
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
	
	public static FunnySkAddon getInst() {
        return instance;
    }
}
