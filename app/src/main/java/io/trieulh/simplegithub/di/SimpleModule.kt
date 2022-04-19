package io.trieulh.simplegithub.di

import io.trieulh.simplegithub.SimpleNavigator
import io.trieulh.simplegithub.config.AuthConfigImpl
import io.trieulh.simplegithub.config.DispatcherFactoryImpl
import io.trieulh.simplegithub.utils.navigator
import io.trieulh.simpledomain.config.AuthConfig
import io.trieulh.simpledomain.config.DispatcherFactory
import org.koin.dsl.module

val simpleModule = module {
    factory<DispatcherFactory> { DispatcherFactoryImpl() }
    single<AuthConfig> { AuthConfigImpl() }
    navigator { SimpleNavigator() }
}