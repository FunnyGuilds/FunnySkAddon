package pl.funnyskaddon.extension

import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.guild.Region
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.Location
import org.bukkit.OfflinePlayer
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import panda.std.Option
import java.util.stream.Collectors

fun Any.getGuild(): Option<Guild> {
    val funnyGuilds = FunnyGuilds.getInstance()
    val userManager = funnyGuilds.userManager
    val guildManager = funnyGuilds.guildManager
    val regionManager = funnyGuilds.regionManager

    when (this) {
        is Guild -> {
            return Option.of(this)
        }
        is OfflinePlayer -> {
            return userManager.findByPlayer(this)
                .flatMap(User::getGuild)
        }
        is Location, is Block -> {
            val location: Location = if (this is Block) {
                this.location
            } else {
                this as Location
            }

            return regionManager.findRegionAtLocation(location)
                .map(Region::getGuild)
        }
        else -> {
            val toString = this.toString()

            return guildManager.findByName(toString)
                .orElse(guildManager.findByTag(toString))
        }
    }
}

fun Guild.isLocationInGuildRegion(locationOption: Option<Location>): Boolean {
    return locationOption
        .map { location -> FunnyGuilds.getInstance().regionManager.findRegionAtLocation(location) }
        .map { region -> region == this.region }
        .orElseGet(false)
}


fun Guild.isPlayerInGuildRegion(player: Player): Boolean {
    return isLocationInGuildRegion(Option.of(player.location))
}

fun Guild.getPlayersInGuildRegion(): Set<Player> {
    return this.region.toStream()
        .map(Region::getWorld)
        .flatMap(World::getPlayers)
        .filter { player -> this.isPlayerInGuildRegion(player) }
        .collect(Collectors.toSet())
}