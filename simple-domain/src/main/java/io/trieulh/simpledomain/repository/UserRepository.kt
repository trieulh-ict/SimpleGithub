package io.trieulh.simpledomain.repository

import io.trieulh.simpledomain.model.SearchUsers
import io.trieulh.simpledomain.model.User
import io.trieulh.simpledomain.model.base.DataState
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun searchUsers(searchString: String, perPage: Int, page: Int): Flow<DataState<SearchUsers>>

    fun getUserDetail(login: String): Flow<DataState<User>>
}