package io.trieulh.simplegithub.journey.auth

import android.os.Bundle
import android.view.View
import io.trieulh.simplegithub.R
import io.trieulh.simplegithub.base.BaseJourneyFragment
import io.trieulh.simplegithub.base.NavEvent
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class AuthJourneyFragment : BaseJourneyFragment<AuthNavigator, AuthRouter>(R.layout.fragment_journey_auth) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override val navigator: AuthNavigator by sharedViewModel()

    override fun onNavigate(navEvent: NavEvent) {
        when (navEvent) {
            is MoveToDashboard -> {
                router?.moveToDashboard()
            }
            else -> super.onNavigate(navEvent)
        }
    }

    override fun getNavControllerId(): Int = R.id.container_auth
}