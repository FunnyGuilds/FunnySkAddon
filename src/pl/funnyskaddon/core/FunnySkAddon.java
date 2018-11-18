package pl.funnyskaddon.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import bstats.bukkit.Metrics;

public class FunnySkAddon extends JavaPlugin{
	
	private static FunnySkAddon inst;
	
	@Override
	public void onEnable() {
		Bukkit.getLogger().info("");
		PluginDescriptionFile d = this.getDescription();
		Bukkit.getLogger().info("[FSA] Uruchamianie pluginu " + d.getFullName());
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

		new Metrics(this);
		inst = this;
		Bukkit.getServer().getPluginManager().registerEvents(new GuildCreateListener(), this);
		TopManager.update();
		Loaders.loadExpressions();
		Loaders.loadEvents();
		Loaders.loadEffects();
		Loaders.loadConditions();
		if(this.getConfig().getBoolean("update.check")) {
            if(!d.getVersion().equalsIgnoreCase(getLatestVersion("https://raw.githubusercontent.com/MLGroupMC/FunnySkAddon/master/VERSION"))) {
                Bukkit.getLogger().info("[FSA] Wersja pluginu: "+d.getVersion());
                Bukkit.getLogger().info("[FSA] Najnowsza wersja pluginu: "+getLatestVersion("https://raw.githubusercontent.com/MLGroupMC/FunnySkAddon/master/VERSION"));
                Bukkit.getLogger().info("[FSA] Wszystkie wersje: https://github.com/MLGroupMC/FunnySkAddon/releases/");
            }
        }
		Bukkit.getLogger().info("");
		if(this.getConfig().getBoolean("top.autoupdate.execute") && this.getConfig().isInt("top.autoupdate.time")) {
			final int ref = this.getConfig().getInt("top.autoupdate.time")*20;
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					TopManager.update();
				}
			}, ref, ref);
		}
        this.saveDefaultConfig();
	}
	
	public static FunnySkAddon getInst() {
        return inst;
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
	
}
