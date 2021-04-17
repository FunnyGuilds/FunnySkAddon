package pl.funnyskaddon.util

import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils
import net.dzikoysk.funnyguilds.basic.guild.RegionUtils
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.Location
import org.bukkit.entity.Player

fun Any.getGuild(): Guild? {
    var guild: Guild? = null

    when (this) {
        is Guild -> {
            guild = this
        }
        is Player -> {
            guild = User.get(this).guild
        }
        is Location -> {
            guild = this.getGuildAtLocation()
        }
        else -> {
            try {
                guild = GuildUtils.getByName(this.toString())
                if (guild == null) {
                    guild = GuildUtils.getByTag(this.toString())
                }
            } catch (ignored: Exception) {
            }
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

fun Location.getGuildAtLocation(): Guild? {
    return RegionUtils.getAt(this).guild
}

fun Player.isInGuildRegion(): Boolean {
    return RegionUtils.getAt(this.location) == null
}

fun Guild.isLocationInGuildRegion(location: Location?): Boolean {
    return RegionUtils.getAt(location).equals(this.region)
}

fun Guild.isPlayerInGuildRegion(player: Player?): Boolean {
    return isLocationInGuildRegion(player?.location)
}

fun Guild.getPlayersInGuildRegion(): Array<Player> {
    val region = this.region
    val list: MutableList<Player> = ArrayList()
    for (player in region.world.players) {
        if (this.isPlayerInGuildRegion(player)) {
            list.add(player)
        }
    }
    return list.toTypedArray()
}