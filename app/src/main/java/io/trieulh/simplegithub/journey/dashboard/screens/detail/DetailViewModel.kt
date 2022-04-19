package io.trieulh.simplegithub.journey.dashboard.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.trieulh.simplegithub.journey.dashboard.screens.detail.model.UserDetailUIModel
import io.trieulh.simplegithub.journey.dashboard.screens.detail.model.toUIModel
import io.trieulh.simpledomain.config.DispatcherFactory
import io.trieulh.simpledomain.model.base.DataState
import io.trieulh.simpledomain.usecase.base.GetUserDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DetailViewModel(
    private val dispatcherFactory: DispatcherFactory,
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    private val _userDetail = MutableStateFlow<DataState<UserDetailUIModel>?>(null)
    val userDetail = _userDetail.asStateFlow()

    fun getUserDetail(login: String) {
        viewModelScope.launch {
            getUserDetailUseCase
                .execute(GetUserDetailUseCase.Params(login))
                .flowOn(dispatcherFactory.io())
                .collect { state ->
                    when (state) {
                        is DataState.Result -> _userDetail.emit(DataState.Result(state.data.toUIModel()))
                        is DataState.Loading -> state.data?.let { data -> _userDetail.emit(DataState.Result(data.toUIModel())) }
                        is DataState.Failed -> _userDetail.emit(DataState.Failed(state.error))
                    }
                }
        }
    }
}