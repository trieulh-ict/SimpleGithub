package io.trieulh.simplegithub.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<R : BaseRouter>(@LayoutRes val layoutResId: Int) : Fragment() {

    @Suppress("UNCHECKED_CAST")
    private val clazz: Class<R> =
        ((this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<R>)

    open val router: R? by lazy {
        (requireParentFragment().parentFragment as? BaseJourneyFragment<*, *>)?.navigator as? R
            ?: (requireParentFragment().activity as? BaseActivity<*>)?.navigator as? R
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    open fun initView() {}
    open fun initViewModel() {}
}