package dev.peopo.bukkitscope.dispatcher

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

interface BukkitDispatcher {

	val plugin : Plugin

	fun runTask(block: Runnable) = Bukkit.getScheduler().runTask(plugin, block)

	fun runTaskAsync(block: Runnable) = Bukkit.getScheduler().runTaskAsynchronously(plugin, block)

	fun runTaskLater(ticks: Long, block: Runnable) = Bukkit.getScheduler().runTaskLater(plugin, block, ticks)

	fun runTaskLaterAsync(ticks: Long, block: Runnable) = Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, block, ticks)

	fun runTaskTimer(delay: Long, interval: Long, block: Runnable) = Bukkit.getScheduler().runTaskTimer(plugin, block, delay, interval)

	fun runTaskTimerAsync(delay: Long, interval: Long, block: Runnable) = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, block, delay, interval)
}