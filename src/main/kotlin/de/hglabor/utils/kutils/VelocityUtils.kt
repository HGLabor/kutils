package de.hglabor.utils.kutils

import net.axay.kspigot.extensions.geometry.vec
import net.axay.kspigot.extensions.geometry.vecY
import net.axay.kspigot.runnables.task
import org.bukkit.entity.Player
import org.bukkit.util.Vector
import kotlin.math.exp
import kotlin.math.sqrt


private const val c = 0.5 // kg/m 0.02 0.5
private const val m = 45 // kg 342 45
private const val g = 32.656  // m/s²
/** Unreliable for >40 */
fun Player.launchBlocks(count: Number) {
    val v = sqrt(((exp(2*count.toDouble() * c / m) * g * m) / c) - (g * m / c)) / 20
    if (v.isFinite()) setHighVelocity(vecY(v))
}


private const val DECELERATION_RATE = 0.95
private const val GRAVITY_CONSTANT = 0.08
private const val MAX_START_VELOCITY = 3.85 // Max normal start velocity (60 blocks)

/** For educational purposes only */
fun Player.setHighVelocity(speed: Vector) {
    var velY = speed.y
    task(period = 1) {
        velocity = if (velY > MAX_START_VELOCITY) vec(0, MAX_START_VELOCITY, 0) else {
            it.cancel()
            vec(0, velY, 0)
        }
        velY -= GRAVITY_CONSTANT
        velY *= DECELERATION_RATE
    }
}