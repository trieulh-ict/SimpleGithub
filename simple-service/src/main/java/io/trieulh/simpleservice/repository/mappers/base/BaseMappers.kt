package io.trieulh.simpleservice.repository.mappers.base

import io.trieulh.simpledomain.model.base.DataState

open class BaseMappers {
    fun <F, T> transformDataState(response: DataState<F>, transform: (F) -> T): DataState<T> {
        return when (response) {
            is DataState.Failed -> DataState.Failed(response.error)
            is DataState.Loading -> DataState.Loading(response.data?.let { transform(it) })
            is DataState.Result -> DataState.Result(transform(response.data))
        }
    }
}