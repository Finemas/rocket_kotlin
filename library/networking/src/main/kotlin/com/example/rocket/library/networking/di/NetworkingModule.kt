package com.example.rocket.library.networking.di

import com.example.rocket.library.networking.data.ApiCaller
import com.example.rocket.library.networking.data.LiveApiCaller
import com.example.rocket.library.networking.system.KtorHttpClientFactory
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkingModule = module {
    single {
        Json {
            isLenient = false
            ignoreUnknownKeys = true
        }
    }
    single {
        KtorHttpClientFactory.createClient(get())
    }
    singleOf(::LiveApiCaller) bind ApiCaller::class
}
