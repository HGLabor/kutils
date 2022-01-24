package de.hglabor.utils.kutils

import net.axay.kspigot.event.listen
import org.bukkit.Bukkit
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.EventPriority

fun Event.call() = apply { Bukkit.getPluginManager().callEvent(this) }

inline fun <reified T : Event> cancelEvent(
    priority: EventPriority = EventPriority.NORMAL,
    ignoreCancelled: Boolean = false,
    register: Boolean = true
) = cancelEventWhen<T>(priority, ignoreCancelled, register) { true }

inline fun <reified T : Event> cancelEventWhen(
    priority: EventPriority = EventPriority.NORMAL,
    ignoreCancelled: Boolean = false,
    register: Boolean = true,
    crossinline condition: T.() -> Boolean
) = listen<T>(priority, ignoreCancelled, register) {
    if (it.condition() && it is Cancellable) it.cancel()
}