package de.hglabor.utils.kutils

import net.axay.kspigot.extensions.broadcast
import org.bukkit.Bukkit
import org.bukkit.ChatColor

fun grayBroadcast(s: String): Int = broadcast("${ChatColor.GRAY}${s.replace(ChatColor.WHITE.toString(), ChatColor.GRAY.toString())}")

val logger = Bukkit.getLogger()