package io.trieulh.simplegithub.journey.dashboard.screens.detail.model

import io.trieulh.simpledomain.model.User

data class UserDetailUIModel(
    val login: String? = null,
    val avatarURL: String? = null,
    val name: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val bio: String? = null,
    val followers: Long? = null,
    val following: Long? = null,
)

fun User.toUIModel(): UserDetailUIModel = UserDetailUIModel(
    login = this.login,
    avatarURL = this.avatarURL,
    name = this.name,
    blog = this.blog,
    location = this.location,
    email = this.email,
    bio = this.bio,
    followers = this.followers,
    following = this.following,
)
