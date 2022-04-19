package io.trieulh.simplegithub.journey.dashboard.di

import io.trieulh.simplegithub.journey.dashboard.DashboardNavigator
import io.trieulh.simplegithub.journey.dashboard.screens.detail.DetailViewModel
import io.trieulh.simplegithub.journey.dashboard.screens.search.SearchViewModel
import io.trieulh.simplegithub.utils.navigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardFlowModule = module {
    navigator { DashboardNavigator() }

    viewModel { SearchViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
}
