package io.trieulh.simplegithub.journey.auth.screens.splash

import androidx.lifecycle.lifecycleScope
import io.trieulh.simplegithub.R
import io.trieulh.simplegithub.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<SplashRouter>(R.layout.fragment_screen_splash) {

    override fun initView() {
        super.initView()
        lifecycleScope.launch {
            delay(2000)
            router?.moveToDashboard()
        }
    }
}