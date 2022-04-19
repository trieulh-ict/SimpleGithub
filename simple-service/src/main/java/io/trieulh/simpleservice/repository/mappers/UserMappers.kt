package io.trieulh.simpleservice.repository.mappers

import io.trieulh.simpledomain.model.SearchUsers
import io.trieulh.simpledomain.model.User
import io.trieulh.simpledomain.model.UserItem
import io.trieulh.simpledomain.model.base.DataState
import io.trieulh.simpleservice.model.response.SearchUsersResponse
import io.trieulh.simpleservice.model.response.UserResponse
import io.trieulh.simpleservice.repository.mappers.base.BaseMappers

class UserMappers : BaseMappers() {
    fun mapToSearchUsers(response: DataState<SearchUsersResponse>): DataState<SearchUsers> {
        return transformDataState(response) {
            SearchUsers(
                totalCount = it.totalCount,
                incompleteResults = it.incompleteResults,
                items = it.items?.map { item ->
                    UserItem(
                        id = item.id,
                        login = item.login,
                        avatarURL = item.avatarURL
                    )
                } ?: listOf(),
            )
        }
    }

    fun mapToUser(response: DataState<UserResponse>): DataState<User> {
        return transformDataState(response) {
            User(
                login = it.login,
                id = it.id,
                nodeID = it.nodeID,
                avatarURL = it.avatarURL,
                gravatarID = it.gravatarID,
                url = it.url,
                htmlURL = it.htmlURL,
                followersURL = it.followersURL,
                followingURL = it.followingURL,
                gistsURL = it.gistsURL,
                starredURL = it.starredURL,
                subscriptionsURL = it.subscriptionsURL,
                organizationsURL = it.organizationsURL,
                reposURL = it.reposURL,
                eventsURL = it.eventsURL,
                receivedEventsURL = it.receivedEventsURL,
                type = it.type,
                siteAdmin = it.siteAdmin,
                name = it.name,
                company = it.company,
                blog = it.blog,
                location = it.location,
                email = it.email,
                hireable = it.hireable,
                bio = it.bio,
                twitterUsername = it.twitterUsername,
                publicRepos = it.publicRepos,
                publicGists = it.publicGists,
                followers = it.followers,
                following = it.following,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
            )
        }
    }
}