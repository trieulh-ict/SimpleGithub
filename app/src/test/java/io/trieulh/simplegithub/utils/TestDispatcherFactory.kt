package io.trieulh.simplegithub.utils

import io.trieulh.simpledomain.config.DispatcherFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
object TestDispatcherFactory : DispatcherFactory {
    override fun io(): CoroutineDispatcher = UnconfinedTestDispatcher()

    override fun main(): CoroutineDispatcher = UnconfinedTestDispatcher()

    override fun default(): CoroutineDispatcher = UnconfinedTestDispatcher()

    override fun unconfined(): CoroutineDispatcher = UnconfinedTestDispatcher()
}