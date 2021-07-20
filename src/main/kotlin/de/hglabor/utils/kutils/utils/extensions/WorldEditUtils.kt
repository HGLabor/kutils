package de.hglabor.utils.kutils.utils.extensions

import com.sk89q.worldedit.bukkit.BukkitWorld
import com.sk89q.worldedit.math.BlockVector2
import com.sk89q.worldedit.math.BlockVector3
import com.sk89q.worldedit.math.Vector3
import org.bukkit.Location
import org.bukkit.World

fun World.we() = BukkitWorld(this)
fun Location.we(): BlockVector3 = BlockVector3.at(x, y, z)
fun Location.blockVector2(): BlockVector2 = BlockVector2.at(x, z)
fun Vector3.location(): Location = Location(null, x, y, z)
fun BlockVector3.location(): Location = Location(null, x.toDouble(), y.toDouble(), z.toDouble())
