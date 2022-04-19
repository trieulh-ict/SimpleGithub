package io.trieulh.simplegithub.journey.dashboard.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.trieulh.simplegithub.journey.dashboard.screens.search.model.UserUIModel
import io.trieulh.simplegithub.journey.dashboard.screens.search.model.toUIModel
import io.trieulh.simpledomain.config.DispatcherFactory
import io.trieulh.simpledomain.model.base.DataState
import io.trieulh.simpledomain.usecase.base.SearchUsersUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val dispatcherFactory: DispatcherFactory,
    private val searchUsersUseCase: SearchUsersUseCase
) : ViewModel() {

    private var currentInput: String? = null
    private var currentPage: Int = 1
    private val _inputFlow: MutableSharedFlow<String?> = MutableSharedFlow()
    private val _listUsersFlow: MutableStateFlow<DataState<List<UserUIModel>>> = MutableStateFlow(DataState.Result(listOf()))
    val listUsersFlow = _listUsersFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _inputFlow.distinctUntilChanged()
                .flowOn(dispatcherFactory.io())
                .collect {
                    searchUsers(it)
                }
        }
    }

    fun submitSearchInput(input: String?) {
        viewModelScope.launch {
            _inputFlow.emit(input)
        }
    }

    fun searchUsers(input: String?, currentPage: Int = 1) {
        currentInput = input
        this.currentPage = currentPage
        viewModelScope.launch {
            if (input.isNullOrEmpty()) _listUsersFlow.emit(DataState.Result(listOf()))
            else {
                searchUsersUseCase.execute(SearchUsersUseCase.Params(searchString = input, page = currentPage))
                    .flowOn(dispatcherFactory.io())
                    .onStart {
                        if (currentPage == 1) _listUsersFlow.emit(DataState.Loading(listOf()))
                    }
                    .collect { state ->
                        when (state) {
                            is DataState.Result -> {
                                if (currentPage <= 1)
                                    _listUsersFlow.emit(DataState.Result(state.data.items.map { it.toUIModel() }))
                                else
                                    _listUsersFlow.emit(DataState.Result(
                                        ((_listUsersFlow.value as? DataState.Result)?.data ?: listOf()) + state.data.items.map {
                                            it
                                                .toUIModel()
                                        }
                                    ))
                            }
                            is DataState.Failed -> _listUsersFlow.emit(DataState.Failed(state.error))
                        }
                    }
            }
        }
    }

    fun loadMoreUsers() {
        searchUsers(currentInput, ++currentPage)
    }
}
