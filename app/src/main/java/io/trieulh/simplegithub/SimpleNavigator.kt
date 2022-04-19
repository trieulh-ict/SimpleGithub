package io.trieulh.simplegithub

import io.trieulh.simplegithub.base.ActionNavEvent
import io.trieulh.simplegithub.base.BaseNavigator
import io.trieulh.simplegithub.base.PopScreen
import io.trieulh.simplegithub.journey.auth.AuthRouter
import io.trieulh.simplegithub.journey.dashboard.DashboardRouter

class SimpleNavigator : BaseNavigator(), AuthRouter, DashboardRouter {
    override fun moveToDashboard() {
        offerEvent(ActionNavEvent(R.id.action_AuthJourney_to_DashboardJourney))
    }

    override fun goBack() {
        offerEvent(PopScreen)
    }
}
