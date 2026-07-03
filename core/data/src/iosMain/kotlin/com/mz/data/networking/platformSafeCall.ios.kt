package com.mz.data.networking

import com.mz.core.Result
import com.mz.domain.util.DataError
import io.ktor.client.statement.HttpResponse

actual suspend fun <T> platformSafeCall(
    execute: suspend () -> HttpResponse,
    handleResponse: suspend (HttpResponse) -> Result<T, DataError.Remote>
): Result<T, DataError.Remote> {
    TODO("Not yet implemented")
}