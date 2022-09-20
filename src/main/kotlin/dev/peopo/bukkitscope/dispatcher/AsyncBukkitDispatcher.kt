package dev.peopo.bukkitscope.dispatcher

import kotlinx.coroutines.*
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import java.lang.Runnable
import kotlin.coroutines.CoroutineContext

private val bukkitScheduler
	get() = Bukkit.getScheduler()

class AsyncBukkitDispatcher(private val plugin: Plugin) : CoroutineDispatcher() {

	override fun dispatch(context: CoroutineContext, block: Runnable) {
		if (!context.isActive) return
		if (!Bukkit.isPrimaryThread()) block.run()
		else bukkitScheduler.runTaskAsynchronously(plugin, block)
	}
}
