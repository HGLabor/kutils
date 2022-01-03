package de.hglabor.utils.kutils

import com.destroystokyo.paper.entity.ai.Goal
import org.bukkit.Bukkit
import org.bukkit.entity.Mob

val mobGoals get() = Bukkit.getMobGoals()

fun Mob.addGoal(priority: Int, goal: Goal<Mob>) = mobGoals.addGoal(this, priority, goal)