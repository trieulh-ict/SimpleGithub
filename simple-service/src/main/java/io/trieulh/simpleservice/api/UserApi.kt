package io.trieulh.simpleservice.api

import io.trieulh.simpleservice.model.response.SearchUsersResponse
import io.trieulh.simpleservice.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val PATH_SEARCH_USERS = "/search/users"
private const val PATH_GET_USER_DETAIL = "/users/{login}"


interface UserApi {
    @GET(PATH_SEARCH_USERS)
    suspend fun searchUsers(@Query("q") searchString: String, @Query("per_page") perPage: Int, @Query("page") page: Int):
            Response<SearchUsersResponse>

    @GET(PATH_GET_USER_DETAIL)
    suspend fun getUserDetail(@Path("login") login: String): Response<UserResponse>

}