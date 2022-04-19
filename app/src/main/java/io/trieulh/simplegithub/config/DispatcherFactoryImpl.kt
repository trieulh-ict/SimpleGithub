package io.trieulh.simplegithub.config

import io.trieulh.simpledomain.config.DispatcherFactory
import kotlinx.coroutines.Dispatchers

class DispatcherFactoryImpl : DispatcherFactory {
    override fun io() = Dispatchers.IO

    override fun main() = Dispatchers.Main

    override fun default() = Dispatchers.Default

    override fun unconfined() = Dispatchers.Unconfined
}