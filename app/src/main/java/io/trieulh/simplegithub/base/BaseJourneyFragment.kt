package io.trieulh.simplegithub.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseJourneyFragment<N : BaseNavigator, R : BaseRouter>(@LayoutRes resId: Int) : BaseFragment<R>(resId) {

    @Suppress("UNCHECKED_CAST")
    private val clazz: KClass<N> =
        ((this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<N>).kotlin

    abstract val navigator: N
    abstract fun getNavControllerId(): Int
    private val getNavController by lazy {
        (childFragmentManager.findFragmentById(getNavControllerId()) as? NavHostFragment)?.navController ?: throw Exception(
            "This view is not a NavController"
        )
    }

    open fun onNavigate(navEvent: NavEvent) {
        when (navEvent) {
            is ActionNavEvent -> getNavController.navigate(navEvent.actionId)
            is DirectionNavEvent -> getNavController.navigate(navEvent.direction)
            is PopScreen -> getNavController.popBackStack()
            else -> {
                /* Do nothing now */
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            navigator.navEvent.collect {
                onNavigate(it)
            }
        }
    }
}