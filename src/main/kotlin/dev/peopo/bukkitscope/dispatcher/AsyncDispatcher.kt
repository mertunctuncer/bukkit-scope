package dev.peopo.bukkitscope.dispatcher


import kotlinx.coroutines.*
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

import kotlin.coroutines.CoroutineContext

class AsyncDispatcher(override val plugin: Plugin) : CoroutineDispatcher(), BukkitDispatcher {

	override fun dispatch(context: CoroutineContext, block: Runnable) {
		if (!context.isActive) return
		if (!Bukkit.isPrimaryThread()) block.run()
		else runTaskAsync(block)
	}
}
