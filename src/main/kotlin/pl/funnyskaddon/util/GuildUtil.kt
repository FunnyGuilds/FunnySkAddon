package pl.funnyskaddon.util

import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import java.util.*

class GuildUtil {
    companion object {
        fun getLowerPoint(guild: Guild): Location {
            val region = guild.region
            return Location(
                region.world, region.lowerX.toDouble(), region.lowerY.toDouble(),
                region.lowerZ.toDouble()
            )
        }

        fun getUpperPoint(guild: Guild): Location {
            val region = guild.region
            return Location(
                region.world, region.upperX.toDouble(), region.upperY.toDouble(),
                region.upperZ.toDouble()
            )
        }

        fun isPlayerInGuildRegion(guild: Guild?, player: Player?): Boolean {
            val location = player?.location
            val region = guild?.region
            if (location != null && region != null) {
                    return ((location.x > region.upperX && location.x < region.lowerX || location.x < region.upperX && location.x > region.lowerX)
                            && (location.y > region.upperY && location.y < region.lowerY || location.y < region.upperY && location.y > region.lowerY)
                            && (location.z > region.upperZ && location.z < region.lowerZ || location.z < region.upperZ && location.z > region.lowerZ))
            }
            return false
        }

        fun getPlayersInGuildRegion(guild: Guild): Array<Player> {
            val region = guild.region
            val list: MutableList<Player> = ArrayList()
            for (player in region.world.players) {
                if (this.isPlayerInGuildRegion(guild, player)) {
                    list.add(player)
                }
            }
            return list.toTypedArray()
        }

        fun getGuildAtLocation(location: Location): Array<Guild>? {
            for (guild in GuildUtils.getGuilds()) {
                val region = guild.region
                if ((location.x > region.upperX && location.x < region.lowerX || location.x < region.upperX && location.x > region.lowerX)
                    && (location.y > region.upperY && location.y < region.lowerY || location.y < region.upperY && location.y > region.lowerY)
                    && (location.z > region.upperZ && location.z < region.lowerZ || location.z < region.upperZ && location.z > region.lowerZ)
                ) {
                    return arrayOf(guild)
                }
            }
            return null
        }

        fun getGuild(guildArgument: Any?): Guild? {
            var guild: Guild? = null
            when (guildArgument) {
                is Guild -> {
                    guild = guildArgument
                }
                is Player -> {
                    guild = User.get(guildArgument).guild
                }
                else -> {
                    try {
                        guild = GuildUtils.getByName(guildArgument.toString())
                    } catch (ex: Exception) {
                    }
                }
            }
            return guild
        }

        fun getLocation(locationArgument: Any?): Location? {
            return when (locationArgument) {
                is Location -> {
                    locationArgument
                }
                is Player -> {
                    locationArgument.location
                }
                is Entity -> {
                    locationArgument.location
                }
                is LivingEntity -> {
                    locationArgument.location
                }
                else -> null
            }
        }
    }
}