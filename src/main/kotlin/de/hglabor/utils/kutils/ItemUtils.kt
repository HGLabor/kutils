package de.hglabor.utils.kutils

import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import net.axay.kspigot.runnables.taskRunLater
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

fun MutableList<ItemStack>.add(material: Material, amount: Int = 1) = add(material.stack(amount))
fun MutableList<ItemStack>.addAll(vararg items: Any) {
    items.forEach {
        when (it) {
            is Material -> add(it)
            is ItemStack -> add(it)
            else -> error("Item must be either Material or ItemStack")
        }
    }
}

fun List<ItemStack>.addToInv(player: Player) { player.addToInv(this) }

fun Material.stack(amount: Int = 1): ItemStack = ItemStack(this, amount)
fun List<Material>.stack(): List<ItemStack> {
    val itemStacks = ArrayList<ItemStack>()
    forEach { itemStacks.add(it.stack()) }
    return itemStacks
}
fun Inventory.addAll(items: List<ItemStack>) = items.forEach { this.addItem(it) }
fun List<String>.materials(): List<Material> {
    val list = ArrayList<Material>()
    forEach {
        list.add(Material.getMaterial(it.uppercase()) ?: return@forEach)
    }
    return list
}

fun namedItem(material: Material, name: Component): ItemStack {
    return itemStack(material) {
        meta { this.name = name }
    }
}

val PlayerInteractEvent.isRightClick get() = action.isRightClick
val PlayerInteractEvent.isLeftClick get() = action.isLeftClick

private fun Player.setItems(startSlot: Int, items: List<Material>) {
    for (slot in items.indices) inventory.setItem(slot+startSlot, items[slot].stack())
}

fun Block.removeAfter(ticks: Long) = taskRunLater(ticks) {
    type = Material.AIR
}