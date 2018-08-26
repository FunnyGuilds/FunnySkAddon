package pl.funnyskaddon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import pl.funnyskaddon.loaders.ConditionLoader;
import pl.funnyskaddon.loaders.EffectsLoader;
import pl.funnyskaddon.loaders.EventLoader;
import pl.funnyskaddon.loaders.ExpressionsLoader;

public class FunnySkAddon extends JavaPlugin {
    
    private static FunnySkAddon inst;
    
    public void onEnable() {
        Bukkit.getLogger().info("[FSA] Widzisz to ? :V Ty tak ni umisz");
        Bukkit.getLogger().info("[FSA] Wladowanie ;V");
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
            Bukkit.getLogger().info("[FSA] Wladowanie wstrzymane ;V");
            Bukkit.getLogger().info("[FSA] Powod: Brak pluginu " + sB.toString() + "!");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Bukkit.getServer().getPluginManager().registerEvents(new EventLoader(), this);
        inst = this;
        Skript.registerAddon(this); 
        ConditionLoader.load();
        EventLoader.load();
        ExpressionsLoader.load();
        EffectsLoader.load();
        Bukkit.getLogger().info("[FSA] Wladowanie zakonczone!");
    }
        
    public static FunnySkAddon getInst() {
        return inst;
    }
}