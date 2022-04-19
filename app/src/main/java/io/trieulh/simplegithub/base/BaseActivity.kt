package io.trieulh.simplegithub.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment

abstract class BaseActivity<N : BaseNavigator>(@LayoutRes val layoutResId: Int) : AppCompatActivity() {


    abstract val navigator: N
    abstract fun getNavControllerId(): Int
    private val getNavController by lazy {
        (supportFragmentManager.findFragmentById(getNavControllerId()) as? NavHostFragment)?.navController ?: throw Exception(
            "This view is not a NavController"
        )
    }

    open fun onNavigate(navEvent: NavEvent) {
        when (navEvent) {
            is ActionNavEvent -> getNavController.navigate(navEvent.actionId)
            is PopScreen -> getNavController.popBackStack()
            else -> {
                /* Do nothing now */
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        lifecycleScope.launchWhenResumed {
            navigator.navEvent.collect {
                onNavigate(it)
            }
        }
    }

}