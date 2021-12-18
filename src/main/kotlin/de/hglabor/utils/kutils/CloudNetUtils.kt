package de.hglabor.utils.kutils

import org.bukkit.Bukkit

/** Only runs the `block` when the CloudNet bukkit helper is loaded */
inline fun cloudNet(block: () -> Unit): Boolean {
    if (!Bukkit.getPluginManager().isPluginEnabled("cloudnet-bridge")) {
        Bukkit.getLogger().warning("CloudNet bridge is not loaded. Ignoring block")
        return false
    }
    Bukkit.getLogger().info("CloudNet bridge is loaded. Running block")
    block()
    return true
}