package io.trieulh.simplegithub.journey.dashboard

import io.trieulh.simplegithub.base.BaseNavigator
import io.trieulh.simplegithub.base.DirectionNavEvent
import io.trieulh.simplegithub.base.PopScreen
import io.trieulh.simplegithub.journey.dashboard.screens.detail.DetailArg
import io.trieulh.simplegithub.journey.dashboard.screens.detail.DetailRouter
import io.trieulh.simplegithub.journey.dashboard.screens.search.SearchRouter
import io.trieulh.simplegithub.journey.dashboard.screens.search.SearchScreenFragmentDirections


class DashboardNavigator : BaseNavigator(), SearchRouter, DetailRouter {
    override fun moveToDetail(login: String) {
        offerEvent(DirectionNavEvent(SearchScreenFragmentDirections.actionToDetail(DetailArg(login))))
    }

    override fun goBack() {
        offerEvent(PopScreen)
    }
}