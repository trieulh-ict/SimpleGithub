package io.trieulh.simplegithub.journey.dashboard.screens.search

import io.trieulh.simplegithub.base.BaseRouter

interface SearchRouter : BaseRouter {
    fun moveToDetail(login: String)
}
