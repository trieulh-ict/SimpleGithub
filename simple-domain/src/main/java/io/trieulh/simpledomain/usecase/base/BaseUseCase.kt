package io.trieulh.simpledomain.usecase.base

import io.trieulh.simpledomain.model.base.DataState
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<P,R> {
    abstract fun execute(params: P? = null) : Flow<DataState<R>>
}