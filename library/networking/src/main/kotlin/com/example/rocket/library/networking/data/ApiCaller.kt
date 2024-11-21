package com.example.rocket.library.networking.data

import com.example.rocket.core.architecture.domain.model.DataResult
import io.ktor.utils.io.CancellationException

interface ApiCaller {

    suspend fun <T> callApi(block: suspend () -> T) : DataResult<T>
}

internal class LiveApiCaller : ApiCaller {
    override suspend fun <T> callApi(block: suspend () -> T): DataResult<T> {
        return try {
            val result = block()
            DataResult.success(result)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            DataResult.error(e)
        }
    }
}