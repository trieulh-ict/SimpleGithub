package io.trieulh.simpledomain.usecase.base

import io.trieulh.simpledomain.model.SearchUsers
import io.trieulh.simpledomain.model.base.DataState
import io.trieulh.simpledomain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchUsersUseCase(
    private val userRepository: UserRepository
) : BaseUseCase<SearchUsersUseCase.Params, SearchUsers>() {
    data class Params(val searchString: String, val perPage: Int = 20, val page: Int = 1)

    override fun execute(params: Params?): Flow<DataState<SearchUsers>> {
        if (params == null) return flow { DataState.Failed<SearchUsers>(Error("Params must not null")) }
        return userRepository.searchUsers(params.searchString, params.perPage, params.page)
    }
}