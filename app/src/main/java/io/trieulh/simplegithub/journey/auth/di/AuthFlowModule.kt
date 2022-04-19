package io.trieulh.simplegithub.journey.auth.di

import io.trieulh.simplegithub.journey.auth.AuthNavigator
import io.trieulh.simplegithub.utils.navigator
import org.koin.dsl.module

val authFlowModule = module {
    navigator { AuthNavigator() }
}
