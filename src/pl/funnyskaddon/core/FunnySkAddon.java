package pl.funnyskaddon.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import bstats.bukkit.Metrics;
import net.dzikoysk.funnyguilds.data.Settings;
import net.dzikoysk.funnyguilds.data.configs.PluginConfig;
import pl.funnyskaddon.core.fix.GuildCreateListener;
import pl.funnyskaddon.core.fix.PointsChangeListener;
import pl.funnyskaddon.core.loaders.SkriptLoaders;
import pl.funnyskaddon.core.schedulers.TopUpdateScheduler;
import pl.funnyskaddon.core.schedulers.UpdateCheckScheduler;

public class FunnySkAddon extends JavaPlugin implements Listener{
	
	private static FunnySkAddon inst;
	public static PluginConfig pc;
	
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
            Bukkit.getLogger().info("[FSA] Uruchamianie wstrzymane!");
            Bukkit.getLogger().info("[FSA] Powod: Brak pluginu " + sB.toString() + "!");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        pc = Settings.getConfig();
        inst = this;
		new Metrics(this);
		Bukkit.getServer().getPluginManager().registerEvents(new PointsChangeListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new GuildCreateListener(), this);
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
        } catch (MalformedURLException e) {
            Bukkit.getLogger().info("Unable to check for updates!");
            e.printStackTrace();
        } catch (IOException e) {
            Bukkit.getLogger().info("Unable to check for updates!");
            e.printStackTrace();
        }

        try {
            return IOUtils.readLines(in).get(0);
        } catch (IOException e) {
            Bukkit.getLogger().info("Unable to determine update!");
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
        return null;
    }
	
	public static FunnySkAddon getInst() {
        return inst;
    }
}
