package de.hglabor.utils.kutils

/**
 * Get's the return value of the reflected function for this object.
 */
@Suppress("UNCHECKED_CAST")
fun <T> Any.reflectMethod(method: String): T? = try { this::class.java.getMethod(method).invoke(this) as T } catch (e: ReflectiveOperationException) { null }