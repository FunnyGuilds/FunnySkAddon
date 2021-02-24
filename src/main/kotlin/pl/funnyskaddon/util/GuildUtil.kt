package pl.funnyskaddon.util

import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils
import net.dzikoysk.funnyguilds.basic.guild.RegionUtils
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
    return RegionUtils.getAt(this).guild
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

fun Player.isInGuildRegion(): Boolean {
    return RegionUtils.getAt(this.location) == null
}

fun Guild.isPlayerInGuildRegion(player: Player?): Boolean {
    return RegionUtils.getAt(player?.location).equals(this.region)
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