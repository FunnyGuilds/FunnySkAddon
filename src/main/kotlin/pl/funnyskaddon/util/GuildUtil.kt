package pl.funnyskaddon.util

import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.Location
import org.bukkit.entity.Player
import java.util.*

class GuildUtil {
    companion object {
        fun getGuild(guildArgument: Any?): Guild? {
            var guild: Guild? = null
            when (guildArgument) {
                is Guild -> {
                    guild = guildArgument
                }
                is Player -> {
                    guild = User.get(guildArgument).guild
                }
                is Location -> {
                    guild = guildArgument.getGuildAtLocation()
                }
                else -> {
                    try {
                        guild = GuildUtils.getByName(guildArgument.toString())
                        if (guild == null) {
                            guild = GuildUtils.getByTag(guildArgument.toString())
                        }
                    } catch (ex: Exception) {
                    }
                }
            }
            return guild
        }
    }
}

fun Location.getGuildAtLocation(): Guild? {
    for (guild in GuildUtils.getGuilds()) {
        val region = guild.region
        if ((this.x > region.upperX && this.x < region.lowerX || this.x < region.upperX && this.x > region.lowerX)
            && (this.y > region.upperY && this.y < region.lowerY || this.y < region.upperY && this.y > region.lowerY)
            && (this.z > region.upperZ && this.z < region.lowerZ || this.z < region.upperZ && this.z > region.lowerZ)
        ) {
            return guild
        }
    }
    return null
}

fun Guild.getLowerPoint(): Location {
    val region = this.region
    return Location(
        region.world, region.lowerX.toDouble(), region.lowerY.toDouble(),
        region.lowerZ.toDouble()
    )
}

fun Guild.getUpperPoint(): Location {
    val region = this.region
    return Location(
        region.world, region.upperX.toDouble(), region.upperY.toDouble(),
        region.upperZ.toDouble()
    )
}

fun Guild.isPlayerInGuildRegion(player: Player?): Boolean {
    val location = player?.location
    val region = this.region
    if (location != null && region != null) {
        return ((location.x > region.upperX && location.x < region.lowerX || location.x < region.upperX && location.x > region.lowerX)
                && (location.y > region.upperY && location.y < region.lowerY || location.y < region.upperY && location.y > region.lowerY)
                && (location.z > region.upperZ && location.z < region.lowerZ || location.z < region.upperZ && location.z > region.lowerZ))
    }
    return false
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