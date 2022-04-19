package io.trieulh.simpleservice.repository

import io.trieulh.simpledomain.model.SearchUsers
import io.trieulh.simpledomain.model.User
import io.trieulh.simpledomain.model.base.DataState
import io.trieulh.simpledomain.repository.UserRepository
import io.trieulh.simpleservice.api.UserApi
import io.trieulh.simpleservice.repository.mappers.UserMappers
import io.trieulh.simpleservice.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class UserRepositoryImpl(
    private val userApi: UserApi,
    private val userMappers: UserMappers
) : UserRepository {
    override fun searchUsers(searchString: String, perPage: Int, page: Int): Flow<DataState<SearchUsers>> {
        return flow {
            emit(safeApiCall { userApi.searchUsers(searchString, perPage, page) })
        }.map { userMappers.mapToSearchUsers(it) }.flowOn(Dispatchers.IO)
    }

    override fun getUserDetail(login: String): Flow<DataState<User>> {
        return flow {
            emit(safeApiCall { userApi.getUserDetail(login) })
        }.map { userMappers.mapToUser(it) }.flowOn(Dispatchers.IO)
    }
}