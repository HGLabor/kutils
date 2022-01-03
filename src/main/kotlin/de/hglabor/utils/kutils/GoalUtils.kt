package de.hglabor.utils.kutils

import com.destroystokyo.paper.entity.ai.Goal
import com.destroystokyo.paper.entity.ai.GoalKey
import org.bukkit.Bukkit
import org.bukkit.entity.Mob

val mobGoals get() = Bukkit.getMobGoals()

fun Mob.addGoal(priority: Int, goal: Goal<Mob>) = mobGoals.addGoal(this, priority, goal)
fun Mob.removeGoal(goal: Goal<Mob>) = mobGoals.removeGoal(this, goal)
fun Mob.removeGoal(goal: GoalKey<Mob>) = mobGoals.removeGoal(this, goal)
fun Mob.hasGoal(key: GoalKey<Mob>) = mobGoals.hasGoal(this, key)
val Mob.goals: Collection<Goal<Mob>> get() = mobGoals.getAllGoals(this)
val Mob.runningGoals: Collection<Goal<Mob>> get() = mobGoals.getRunningGoals(this)