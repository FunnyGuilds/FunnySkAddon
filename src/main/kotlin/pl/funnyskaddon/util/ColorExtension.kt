package pl.funnyskaddon.util

import net.md_5.bungee.api.ChatColor

fun String.color(): String {
    return ChatColor.translateAlternateColorCodes('&', this)
}