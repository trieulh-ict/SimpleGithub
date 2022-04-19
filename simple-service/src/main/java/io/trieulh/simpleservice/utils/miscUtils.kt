package io.trieulh.simpleservice.utils

import io.trieulh.simpledomain.model.base.DataState
import retrofit2.Response

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): DataState<T> {
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                return DataState.Result(it)
            }
        }
        return DataState.Failed(Error("${response.code()} ${response.message()}"))
    } catch (e: Exception) {
        return DataState.Failed(Error(e.message ?: e.toString()))
    }
}