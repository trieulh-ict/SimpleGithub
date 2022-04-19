package io.trieulh.simplegithub.config

import io.trieulh.simplegithub.BuildConfig
import io.trieulh.simpledomain.config.AuthConfig

class AuthConfigImpl : AuthConfig {
    override fun getAccessToken(): String = BuildConfig.GITHUB_TOKEN
}