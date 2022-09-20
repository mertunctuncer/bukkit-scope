package dev.peopo.bukkitscope.coroutine

import dev.peopo.bukkitscope.dispatcher.AsyncDispatcher
import dev.peopo.bukkitscope.dispatcher.ServerDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.bukkit.plugin.Plugin

val Plugin.dispatcher: ServerDispatcher
	get() = ServerDispatcher(this)

val Plugin.asyncDispatcher: AsyncDispatcher
	get() = AsyncDispatcher(this)

@Suppress("unused")
val IOScope: CoroutineScope
	get() = CoroutineScope(Dispatchers.IO)

@Suppress("unused")
val DefaultScope: CoroutineScope
	get() = CoroutineScope(Dispatchers.Default)

@Suppress("unused")
val UnconfinedScope: CoroutineScope
	get() = CoroutineScope(Dispatchers.Unconfined)
