package pl.funnyskaddon.extension

import net.dzikoysk.funnyguilds.user.User
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import panda.std.Option

fun User.getOfflinePlayer(): Option<OfflinePlayer> {
    return Option.of(Bukkit.getOfflinePlayer(this.uuid))
}

fun User.getPlayerOption(): Option<Player> {
    return Option.of(Bukkit.getPlayer(this.uuid))
}