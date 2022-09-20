package dev.peopo.bukkitscope.coroutine

import dev.peopo.bukkitscope.dispatcher.AsyncDispatcher
import dev.peopo.bukkitscope.dispatcher.BukkitDispatcher
import dev.peopo.bukkitscope.dispatcher.ServerDispatcher
import dev.peopo.bukkitscope.exception.IllegalDispatcherException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

@Suppress("unused")
fun CoroutineScope.launchTimer(delay: Long, interval : Long, block: suspend (CoroutineContext) -> Unit) = launch {
	delay(delay)
	block(coroutineContext)
	while (coroutineContext.isActive) {
		delay(interval)
		block(coroutineContext)
	}
}

@Suppress("unused")
fun CoroutineScope.launchLater(delay: Long, block: suspend (CoroutineContext) -> Unit) = launch {
	delay(delay)
	block(coroutineContext)
}

@Suppress("unused")
fun CoroutineScope.launchTickTimer(delay: Long, interval: Long, block: (CoroutineContext) -> Unit) = launch {
	val dispatcher = coroutineContext
	if (dispatcher !is BukkitDispatcher) throw IllegalDispatcherException("Must be called from a bukkit dispatcher")
	when (dispatcher) {
		is AsyncDispatcher -> { dispatcher.runTaskTimerAsync(delay, interval) {
			block(coroutineContext)}
		}
		is ServerDispatcher -> { dispatcher.runTaskTimer(delay, interval) {
			block(coroutineContext)}
		}
	}
}

@Suppress("unused")
fun CoroutineScope.launchTickTimer(delay: Long, interval: Long, plugin: Plugin, block: (CoroutineContext) -> Unit) = CoroutineScope(plugin.dispatcher).launch {
	(coroutineContext as BukkitDispatcher).runTaskTimer(delay, interval) { block(coroutineContext) }
}

@Suppress("unused")
fun CoroutineScope.launchTickTimerAsync(delay: Long, interval: Long, plugin: Plugin, block: (CoroutineContext) -> Unit) = CoroutineScope(plugin.asyncDispatcher).launch {
	(coroutineContext as BukkitDispatcher).runTaskTimerAsync(delay, interval) { block(coroutineContext) }
}