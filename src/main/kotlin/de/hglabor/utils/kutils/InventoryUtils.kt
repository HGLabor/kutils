package de.hglabor.utils.kutils

import net.axay.kspigot.event.SingleListener
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.unregister
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryCloseEvent

fun Player.inv(size: Int, name: String = "", onClose: InventoryCloseEvent.() -> Unit = {}) {
    @Suppress("DEPRECATION")
    openInventory(Bukkit.createInventory(null, size, name).apply {
        @Suppress("JoinDeclarationAndAssignment")
        lateinit var listener: SingleListener<InventoryCloseEvent>
        listener = listen {
            if (it.inventory == this && it.player == this@inv) {
                listener.unregister()
                it.onClose()
            }
        }
    })
}