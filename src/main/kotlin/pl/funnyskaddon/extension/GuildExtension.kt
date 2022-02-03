package pl.funnyskaddon.extension

import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.guild.Region
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.Location
import org.bukkit.OfflinePlayer
import org.bukkit.block.Block
import org.bukkit.entity.Player
import panda.std.stream.PandaStream
import java.util.stream.Collectors

fun Any.getGuild(): Guild? {
    val funnyGuilds = FunnyGuilds.getInstance()
    val userManager = funnyGuilds.userManager
    val guildManager = funnyGuilds.guildManager
    val regionManager = funnyGuilds.regionManager

    when (this) {
        is Guild -> {
            return this
        }
        is OfflinePlayer -> {
            return userManager.findByPlayer(this)
                .map(User::getGuild)
                .orNull
        }
        is Location, is Block -> {
            val location: Location = if (this is Block) {
                this.location
            } else {
                this as Location
            }

            return regionManager.findRegionAtLocation(location)
                .map(Region::getGuild)
                .orNull
        }
        else -> {
            val toString = this.toString()

            return guildManager.findByName(toString)
                .orElse(guildManager.findByTag(toString))
                .orNull
        }
    }
}

fun Guild.getLowerPoint(): Location {
    val region = this.region
    return Location(
        region.world,
        region.lowerX.toDouble(),
        region.lowerY.toDouble(),
        region.lowerZ.toDouble()
    )
}

fun Guild.getUpperPoint(): Location {
    val region = this.region
    return Location(
        region.world,
        region.upperX.toDouble(),
        region.upperY.toDouble(),
        region.upperZ.toDouble()
    )
}

fun Guild.isLocationInGuildRegion(location: Location?): Boolean {
    return FunnyGuilds.getInstance().regionManager.findRegionAtLocation(location)
        .map { region -> region == this.region }
        .orElseGet(false)
}

fun Guild.isPlayerInGuildRegion(player: Player): Boolean {
    return isLocationInGuildRegion(player.location)
}

fun Guild.getPlayersInGuildRegion(): Set<Player> {
    return PandaStream.of(this.region.world.players)
        .filter { player -> this.isPlayerInGuildRegion(player) }
        .collect(Collectors.toSet())
}