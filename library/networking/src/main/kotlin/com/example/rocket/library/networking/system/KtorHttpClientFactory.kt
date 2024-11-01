package com.example.rocket.library.networking.system

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal object KtorHttpClientFactory {

    fun createClient(
        json: Json,
    ): HttpClient {
        return HttpClient {
            expectSuccess = true
            followRedirects = false

            installLogging()
            installContentNegotiation(json)
        }
    }
}

private const val NETWORKING_TAG = "Ktor"

fun HttpClientConfig<*>.installLogging() {
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v(NETWORKING_TAG, message)
            }
        }
        level = LogLevel.ALL
    }
}

fun HttpClientConfig<*>.installContentNegotiation(json: Json) {
    install(ContentNegotiation) {
        json(json)
    }
}
