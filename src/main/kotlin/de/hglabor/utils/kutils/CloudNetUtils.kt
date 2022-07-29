package de.hglabor.utils.kutils

import eu.cloudnetservice.modules.bridge.BridgeServiceHelper
import org.bukkit.Bukkit

/** Only runs the `block` when the CloudNet bukkit helper is loaded */
inline fun cloudNet(block: CloudNetContext.() -> Unit): Boolean {
    if (!Bukkit.getPluginManager().isPluginEnabled("cloudnet-bridge")) {
        logger.warning("CloudNet bridge is not loaded. Ignoring block")
        return false
    }
    logger.info("CloudNet bridge is loaded. Running block")
    try {
        CloudNetContext.block()
    }
    catch (e: Exception) {
        logger.warning("Error while executing cloudnet block: $e")
    }
    return true
}

object CloudNetContext {
    var motd: String
        set(value) = BridgeServiceHelper.MOTD.set(value)
        get() = BridgeServiceHelper.MOTD.get()
    var state: String
        set(value) = BridgeServiceHelper.STATE.set(value)
        get() = BridgeServiceHelper.STATE.get()
    var extra: String
        set(value) = BridgeServiceHelper.EXTRA.set(value)
        get() = BridgeServiceHelper.EXTRA.get()
    fun ingame(autoStartService: Boolean = true) = BridgeServiceHelper.changeToIngame(autoStartService)
}