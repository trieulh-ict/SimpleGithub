package io.trieulh.simplegithub.journey.dashboard.screens.search

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.trieulh.simplegithub.R
import io.trieulh.simplegithub.base.BaseFragment
import io.trieulh.simplegithub.journey.dashboard.screens.search.adapter.items.UserModule
import io.trieulh.simplegithub.journey.dashboard.screens.search.adapter.items.UsersEmptyModule
import io.trieulh.simplegithub.journey.dashboard.screens.search.model.UserUIModel
import io.trieulh.simplegithub.utils.textChanges
import io.trieulh.simpledomain.model.base.DataState
import io.trieulh.simplegenericadapter.SimpleGenericAdapter
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder
import io.trieulh.simplegenericadapter.module.PagingModule
import io.trieulh.simplegenericadapter.utils.animation.SimpleAnimationType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class SearchScreenFragment : BaseFragment<SearchRouter>(R.layout.fragment_screen_search), UserModule.OnModuleClickListener {
    companion object {
        private const val LOADING_MORE_THRESHOLD: Int = 1
    }

    private val barLoading: ProgressBar? get() = view?.findViewById(R.id.bar_loading)
    private val edtSearch: AppCompatEditText? get() = view?.findViewById(R.id.edt_search)
    private val lvUsers: RecyclerView? get() = view?.findViewById(R.id.lv_users)
    private var usersAdapter: SimpleGenericAdapter? = null

    private val viewModel by viewModel<SearchViewModel>()

    override fun initView() {
        super.initView()
        initListView()
        initEditText()
    }

    override fun initViewModel() {
        super.initViewModel()

        lifecycleScope.launchWhenResumed {
            viewModel.listUsersFlow
                .collect {
                    barLoading?.visibility = View.GONE
                    when (it) {
                        is DataState.Result -> {
                            usersAdapter?.setItems(it.data)
                        }
                        is DataState.Loading -> {
                            barLoading?.visibility = View.VISIBLE
                        }
                    }
                }
        }
    }

    private fun initEditText() {
        edtSearch?.apply {
            textChanges()
                .debounce(300)
                .map { it?.trim()?.toString() }
                .distinctUntilChanged()
                .onEach {
                    viewModel.submitSearchInput(it)
                }.launchIn(lifecycleScope)
        }
    }

    private fun initListView() {
        lvUsers?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            usersAdapter = SimpleGenericAdapter.Builder()
                .addItemAnimation(SimpleAnimationType.SLIDE_IN_RIGHT)
                .addItemModule(UserModule(this@SearchScreenFragment))
                .addEmptyModule(UsersEmptyModule())
                .addPagingModule(object : PagingModule() {
                    override val layoutRes: Int = R.layout.view_item_loading_users

                    override fun onBind(holder: SimpleViewHolder) {
                        // Do nothing now
                    }

                    override fun onLoadMore(currentPage: Int) {
                        viewModel.loadMoreUsers()
                    }

                    override fun withVisibleThreshold(): Int = LOADING_MORE_THRESHOLD

                })
                .attachTo(this)
                .build()
        }
    }

    override fun onUserClicked(item: UserUIModel) {
        if (item.login.isNullOrEmpty()) return
        router?.moveToDetail(item.login)
//        val direction = SearchScreenFragmentDirections.actionSearchToDetail(DetailArg(item.login))
//        findNavController().navigate(direction)
    }

}