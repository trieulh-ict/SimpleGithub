package io.trieulh.simplegithub.base

import androidx.annotation.IdRes
import androidx.navigation.NavDirections

interface NavEvent

data class ActionNavEvent(@IdRes val actionId: Int) : NavEvent
data class DirectionNavEvent(val direction: NavDirections) : NavEvent
object PopScreen : NavEvent
