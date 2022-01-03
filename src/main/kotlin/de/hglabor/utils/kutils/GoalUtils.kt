package de.hglabor.utils.kutils

import com.destroystokyo.paper.entity.ai.Goal
import com.destroystokyo.paper.entity.ai.GoalKey
import org.bukkit.Bukkit
import org.bukkit.entity.Mob

val mobGoals get() = Bukkit.getMobGoals()

fun Mob.addGoal(priority: Int, goal: Goal<Mob>) = mobGoals.addGoal(this, priority, goal)
fun Mob.hasGoal(priority: Int, key: GoalKey<Mob>) = mobGoals.hasGoal(this, key)