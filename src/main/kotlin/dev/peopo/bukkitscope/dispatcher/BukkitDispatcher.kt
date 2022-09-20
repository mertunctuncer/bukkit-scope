package dev.peopo.bukkitscope.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.isActive
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

import kotlin.coroutines.CoroutineContext

private val bukkitScheduler
	get() = Bukkit.getScheduler()

class BukkitDispatcher(private val plugin: Plugin) : CoroutineDispatcher(){

	override fun dispatch(context: CoroutineContext, block: Runnable) {
		if (!context.isActive) return
		if (Bukkit.isPrimaryThread()) block.run()
		else bukkitScheduler.callSyncMethod(plugin) { block.run() }
	}

	override fun toString(): String {
		return super.toString()
	}
}


