package de.hglabor.utils.kutils

import de.dytanic.cloudnet.ext.bridge.bukkit.BukkitCloudNetHelper
import org.bukkit.Bukkit

/** Only runs the `block` when the CloudNet bukkit helper is loaded */
inline fun cloudNet(block: CloudNetContext.() -> Unit): Boolean {
    if (!Bukkit.getPluginManager().isPluginEnabled("cloudnet-bridge")) {
        Bukkit.getLogger().warning("CloudNet bridge is not loaded. Ignoring block")
        return false
    }
    Bukkit.getLogger().info("CloudNet bridge is loaded. Running block")
    CloudNetContext.block()
    return true
}

object CloudNetContext {
    fun motd(motd: String) = BukkitCloudNetHelper.setMotd(motd)
    fun motd(): String = BukkitCloudNetHelper.getMotd()
    fun state(state: String) = BukkitCloudNetHelper.setState(state)
    fun state(): String = BukkitCloudNetHelper.getState()
    fun extra(extra: String) = BukkitCloudNetHelper.setExtra(extra)
    fun ingame(autoStartService: Boolean = true) = BukkitCloudNetHelper.changeToIngame(autoStartService)
}