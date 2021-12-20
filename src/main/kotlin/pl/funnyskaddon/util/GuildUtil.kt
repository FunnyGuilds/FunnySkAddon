package pl.funnyskaddon.util

import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.guild.Region
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.Location
import org.bukkit.entity.Player
import panda.std.stream.PandaStream
import java.util.stream.Collectors

fun Any.getGuild(): Guild? {
    val funnyGuilds = FunnyGuilds.getInstance()
    val userManager = funnyGuilds.userManager
    val guildManager = funnyGuilds.guildManager

    val guild: Guild?

    when (this) {
        is Guild -> {
            guild = this
        }
        is Player -> {
            guild = userManager.findByPlayer(player)
                .map(User::getGuild)
                .orNull
        }
        is Location -> {
            guild = FunnyGuilds.getInstance().regionManager.findRegionAtLocation(this)
                .map(Region::getGuild)
                .orNull
        }
        else -> {
            val toString = this.toString()

            guild = guildManager.findByName(toString)
                .orElse(guildManager.findByTag(toString))
                .orNull
        }
    }

    return guild
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
        .filter { region -> region == this.region }
        .isPresent
}

fun Guild.isPlayerInGuildRegion(player: Player): Boolean {
    return isLocationInGuildRegion(player.location)
}

fun Guild.getPlayersInGuildRegion(): Set<Player> {
    return PandaStream.of(this.region.world.players)
        .filter { player -> this.isPlayerInGuildRegion(player) }
        .collect(Collectors.toSet())
}