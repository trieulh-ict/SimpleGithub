package io.trieulh.simpledomain.model

data class SearchUsers(
    val totalCount: Long? = null,
    val incompleteResults: Boolean? = null,
    val items: List<UserItem>
)
