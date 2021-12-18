package de.hglabor.utils.kutils

import net.axay.kspigot.extensions.console
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

fun sendCommand(command: String, sender: CommandSender = console) = Bukkit.dispatchCommand(sender, command)