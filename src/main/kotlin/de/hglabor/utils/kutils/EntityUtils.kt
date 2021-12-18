package de.hglabor.utils.kutils

import net.axay.kspigot.extensions.bukkit.feedSaturate
import net.axay.kspigot.extensions.geometry.subtract
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.block.BlockFace
import org.bukkit.entity.Entity
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

fun Entity.statueAttributes() {
    isCustomNameVisible = true
    isPersistent = false
    isInvulnerable = true
    isSilent = true
    setGravity(false)
    if (this is LivingEntity) {
        setAI(false)
        removeWhenFarAway = false
    }
}

inline fun onlinePlayers(block: Player.() -> Unit) = net.axay.kspigot.extensions.onlinePlayers.forEach(block)

fun Cancellable.cancel() { isCancelled = true }

fun HumanEntity.clearInv() = inventory.clear()

fun HumanEntity.noMove(seconds: Int) {
    this.addPotionEffect(PotionEffect(PotionEffectType.SLOW, seconds*20, 6, true, false))
    this.addPotionEffect(PotionEffect(PotionEffectType.JUMP, seconds*20, 250, true, false))
}

fun HumanEntity.closeAndClearInv() {
    this.closeInventory()
    this.clearInv()
}

fun Player.clearHealFeedSaturate() {
    clearInv()
    feedSaturate()
    health = healthScale
}

fun Player.addToInv(items: List<ItemStack>)   { items.forEach { this.inventory.addItem(it) } }
fun Player.playPlingSound(pitch: Number = 1) = this.playSound(this.location, Sound.BLOCK_NOTE_BLOCK_PLING, 1F, pitch.toFloat())
fun Player.playSound(sound: Sound, pitch: Number = 1, volume: Number = 1, location: Location = this.location) = playSound(location, sound, volume.toFloat(), pitch.toFloat())

fun HumanEntity.creative()  { this.gameMode = GameMode.CREATIVE }
fun HumanEntity.survival()  { this.gameMode = GameMode.SURVIVAL }
fun HumanEntity.adventure() { this.gameMode = GameMode.ADVENTURE }
fun HumanEntity.spectator() { this.gameMode = GameMode.SPECTATOR }

fun Entity.isCreative()  = this is Player && this.gameMode == GameMode.CREATIVE
fun Entity.isSurvival()  = this is Player && this.gameMode == GameMode.SURVIVAL
fun Entity.isAdventure() = this is Player && this.gameMode == GameMode.ADVENTURE
fun Entity.isSpectator() = this is Player && this.gameMode == GameMode.SPECTATOR

fun Player.onGround() = !isFlying && location.clone().subtract(0, 0.1, 0).block.type != Material.AIR && velocity.y == 0.0