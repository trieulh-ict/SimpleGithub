package io.trieulh.simplegithub.journey.dashboard.screens.search.model

import io.trieulh.simpledomain.model.UserItem
import io.trieulh.simplegenericadapter.diff.Diffable

data class UserUIModel(val id: Long?, val login: String?, val avatarURL: String?) : Diffable {
    override fun areContentTheSame(other: Diffable): Boolean {
        return other is UserUIModel && login == other.login
    }

    override fun getUniqueIdentifier(): Long = id ?: System.currentTimeMillis()
}

fun UserItem.toUIModel(): UserUIModel = UserUIModel(
    id = this.id,
    login = this.login,
    avatarURL = this.avatarURL
)
