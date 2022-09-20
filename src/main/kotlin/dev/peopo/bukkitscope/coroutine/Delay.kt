package dev.peopo.bukkitscope.coroutine

import dev.peopo.bukkitscope.dispatcher.AsyncDispatcher
import dev.peopo.bukkitscope.dispatcher.BukkitDispatcher
import dev.peopo.bukkitscope.dispatcher.ServerDispatcher
import dev.peopo.bukkitscope.exception.IllegalDispatcherException
import kotlinx.coroutines.withContext
import org.bukkit.plugin.Plugin
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Suppress("unused")
suspend fun delayTicks(ticks: Long, plugin: Plugin) = withContext(plugin.dispatcher){
	val dispatcher = coroutineContext as BukkitDispatcher
	suspendCoroutine { dispatcher.runTaskLater(ticks) { it.resume(ticks) } }
}

@Suppress("unused")
suspend fun delayTicksAsync(ticks: Long, plugin: Plugin) = withContext(plugin.asyncDispatcher){
	val dispatcher = coroutineContext as BukkitDispatcher
	suspendCoroutine { dispatcher.runTaskLaterAsync(ticks) { it.resume(ticks) } }
}

@Suppress("unused")
suspend fun delayTicks(ticks: Long) {
	val dispatcher = coroutineContext
	if(dispatcher !is BukkitDispatcher) throw IllegalDispatcherException("Must be called from a bukkit dispatcher")
	when(dispatcher) {
		is AsyncDispatcher -> { suspendCoroutine { dispatcher.runTaskLaterAsync(ticks) { it.resume(ticks) } } }
		is ServerDispatcher -> { suspendCoroutine { dispatcher.runTaskLater(ticks) { it.resume(ticks) } } }
	}
}