package de.hglabor.utils.kutils

import net.axay.kspigot.event.SingleListener
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.unregister
import net.axay.kspigot.gui.GUIClickEvent
import net.axay.kspigot.gui.GUICloseEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.ItemStack

fun Player.inv(size: Int, name: String = "", content: List<ItemStack>, onClose: InventoryCloseEvent.() -> Unit = {}) {
    @Suppress("DEPRECATION")
    openInventory(Bukkit.createInventory(null, size, name).apply {
        addItem(*content.toTypedArray())
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

fun GUIClickEvent<*>.cancel() = bukkitEvent.cancel()

val GUICloseEvent<*>.closedByPlayer get() = bukkitEvent.reason == InventoryCloseEvent.Reason.PLAYER