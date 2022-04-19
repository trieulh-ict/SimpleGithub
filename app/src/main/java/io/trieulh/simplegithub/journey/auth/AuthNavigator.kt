package io.trieulh.simplegithub.journey.auth

import io.trieulh.simplegithub.base.BaseNavigator
import io.trieulh.simplegithub.base.NavEvent
import io.trieulh.simplegithub.base.PopScreen
import io.trieulh.simplegithub.journey.auth.screens.splash.SplashRouter

object MoveToDashboard : NavEvent

class AuthNavigator : BaseNavigator(), SplashRouter {
    override fun moveToDashboard() {
        offerEvent(MoveToDashboard)
    }

    override fun goBack() {
        offerEvent(PopScreen)
    }
}