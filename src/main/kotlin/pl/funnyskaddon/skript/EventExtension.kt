package pl.funnyskaddon.skript

import ch.njol.skript.lang.Expression
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.Event
import panda.std.Option
import pl.funnyskaddon.extension.getGuild

fun <T> Event.getValue(expression: Expression<T>): Option<T> {
    return Option.of(expression.getSingle(this))
}

fun Event.getOfflinePlayer(expression: Expression<OfflinePlayer>): Option<OfflinePlayer> {
    return Option.of((expression.getSingle(this)))
}

fun Event.getPlayer(expression: Expression<OfflinePlayer>): Option<Player> {
    return getOfflinePlayer(expression)
        .map { it.player }
}

fun Event.getUser(expression: Expression<OfflinePlayer>): Option<User> {
    return getOfflinePlayer(expression)
        .flatMap(FunnyGuilds.getInstance().userManager::findByPlayer)
}

fun Event.getGuild(expression: Expression<*>): Option<Guild> {
    return expression.getSingle(this)?.getGuild() ?: Option.none()
}