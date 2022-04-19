package io.trieulh.simplegithub.journey.dashboard

import io.trieulh.simplegithub.R
import io.trieulh.simplegithub.base.BaseJourneyFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DashboardJourneyFragment : BaseJourneyFragment<DashboardNavigator, DashboardRouter>(R.layout.fragment_journey_dashboard) {
    override val navigator: DashboardNavigator by sharedViewModel()
    override fun getNavControllerId(): Int = R.id.container_dashboard
}