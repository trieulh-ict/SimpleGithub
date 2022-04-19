package io.trieulh.simplegithub

import io.trieulh.simplegithub.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimpleActivity : BaseActivity<SimpleNavigator>(R.layout.activity_main) {
    override val navigator: SimpleNavigator by viewModel()
    override fun getNavControllerId(): Int = R.id.container_main
}