package io.trieulh.simpledomain.model.base

sealed class DataState<out T> {
    class Result<out T>(val data: T) : DataState<T>()
    class Loading<out T>(val data: T?) : DataState<T>()
    class Failed<out T>(val error: Error) : DataState<T>()
}

fun <T> DataState<T>.getCachedLoading(): DataState.Loading<T> {
    return DataState.Loading(
        when (this) {
            is DataState.Result -> data
            is DataState.Loading -> data
            else -> null
        }
    )
}
