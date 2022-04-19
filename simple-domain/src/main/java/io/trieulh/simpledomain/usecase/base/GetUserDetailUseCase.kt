package io.trieulh.simpledomain.usecase.base

import io.trieulh.simpledomain.model.SearchUsers
import io.trieulh.simpledomain.model.User
import io.trieulh.simpledomain.model.base.DataState
import io.trieulh.simpledomain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserDetailUseCase(private val userRepository: UserRepository) : BaseUseCase<GetUserDetailUseCase.Params,User>() {
    override fun execute(params: Params?): Flow<DataState<User>> {
        if(params == null) return flow { DataState.Failed<SearchUsers>(Error("Params must not null")) }
        return userRepository.getUserDetail(params.login)
    }

    data class Params(val login: String)
}