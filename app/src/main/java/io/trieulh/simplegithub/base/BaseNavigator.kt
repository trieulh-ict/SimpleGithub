package io.trieulh.simplegithub.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class BaseNavigator : ViewModel() {
    val navEvent: MutableSharedFlow<NavEvent> = MutableSharedFlow()

    fun offerEvent(event: NavEvent) {
        viewModelScope.launch {
            navEvent.emit(event)
        }
    }
}