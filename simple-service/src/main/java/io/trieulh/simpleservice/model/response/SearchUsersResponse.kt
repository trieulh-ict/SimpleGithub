package io.trieulh.simpleservice.model.response

import com.google.gson.annotations.SerializedName

data class SearchUsersResponse (
    @SerializedName("total_count")
    val totalCount: Long? = null,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    @SerializedName("items")
    val items: List<UserItemResponse>? = null
)