package pl.funnyskaddon.skript

import ch.njol.skript.lang.Expression
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.Event
import panda.std.Option
import pl.funnyskaddon.util.getGuild

fun <T> Event.getValue(expression: Expression<T>): T? {
    return expression.getSingle(this)
}

fun <T> Event.getValueOption(expression: Expression<T>): Option<T> {
    return Option.of(this.getValue(expression))
}

fun Event.getOfflinePlayer(expression: Expression<OfflinePlayer>): OfflinePlayer? {
    return expression.getSingle(this)
}

fun Event.getOfflinePlayerOption(expression: Expression<OfflinePlayer>): Option<OfflinePlayer> {
    return Option.of(this.getOfflinePlayer(expression))
}

fun Event.getPlayer(expression: Expression<OfflinePlayer>): Player? {
    return this.getPlayerOption(expression).orNull
}

fun Event.getPlayerOption(expression: Expression<OfflinePlayer>): Option<Player> {
    return getOfflinePlayerOption(expression)
        .map { it.player }
}

fun Event.getUser(expression: Expression<OfflinePlayer>): User? {
    return this.getUserOption(expression).orNull
}

fun Event.getUserOption(expression: Expression<OfflinePlayer>): Option<User> {
    return getOfflinePlayerOption(expression)
        .flatMap(FunnyGuilds.getInstance().userManager::findByPlayer)
}

fun Event.getGuild(expression: Expression<Any>): Guild? {
    return expression.getSingle(this)?.getGuild()
}

fun Event.getGuildOption(expression: Expression<Any>): Option<Guild> {
    return Option.of(this.getGuild(expression))
}