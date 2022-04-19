package io.trieulh.simpleservice.di

import io.trieulh.simpledomain.config.AuthConfig
import io.trieulh.simpledomain.repository.UserRepository
import io.trieulh.simpleservice.api.UserApi
import io.trieulh.simpleservice.repository.UserRepositoryImpl
import io.trieulh.simpleservice.repository.mappers.UserMappers
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val baseUrl = "https://api.github.com"

val serviceModule = module {
    //Config
    single(named("Github")) {
        Retrofit
            .Builder()
            .client(get()).baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor {
                val newRequest = it.request()
                    .newBuilder()
                    .addHeader("Authorization", "token ${get<AuthConfig>().getAccessToken()}")
                    .build()
                it.proceed(newRequest)
            }
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Api
    single { get<Retrofit>(named("Github")).create(UserApi::class.java) }

    //Repos
    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    // Mappers
    single { UserMappers() }
}