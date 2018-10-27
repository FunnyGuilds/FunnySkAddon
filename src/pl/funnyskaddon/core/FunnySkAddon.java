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
		Bukkit.getLogger().info("[FSA] Widzisz to? ;v Ty tak ni umisz!");
        Bukkit.getLogger().info("[FSA] Wladowanie ;v");
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
            Bukkit.getLogger().info("[FSA] Wladowanie wstrzymane!");
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
        	PluginDescriptionFile d = this.getDescription();
            if(!d.getVersion().equalsIgnoreCase(getLatestVersion("https://raw.githubusercontent.com/MLGroupMC/FunnySkAddon/master/VERSION"))) {
                Bukkit.getLogger().info("[FSA] Wersja pluginu: "+this.getDescription().getVersion());
                Bukkit.getLogger().info("[FSA] Najnowsza wersja pluginu: "+getLatestVersion("https://raw.githubusercontent.com/MLGroupMC/FunnySkAddon/master/VERSION"));
                Bukkit.getLogger().info("[FSA] Wszystkie wersje: https://github.com/MLGroupMC/FunnySkAddon/releases/");
                Bukkit.getLogger().info("");
            }
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
