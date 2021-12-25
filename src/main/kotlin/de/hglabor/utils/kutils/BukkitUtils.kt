package de.hglabor.utils.kutils

import org.bukkit.Bukkit
import java.util.*

val logger = Bukkit.getLogger()
val pluginManager = Bukkit.getPluginManager()
val pluginsFolder = Bukkit.getPluginsFolder()
val maxPlayers = Bukkit.getMaxPlayers()

fun player(uuid: UUID) = Bukkit.getPlayer(uuid)
fun isPluginEnabled(name: String) = Bukkit.getPluginManager().isPluginEnabled(name)