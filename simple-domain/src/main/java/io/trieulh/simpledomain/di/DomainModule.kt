package io.trieulh.simpledomain.di

import io.trieulh.simpledomain.usecase.base.GetUserDetailUseCase
import io.trieulh.simpledomain.usecase.base.SearchUsersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { SearchUsersUseCase(get()) }
    factory { GetUserDetailUseCase(get()) }
}