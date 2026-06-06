package com.mz.data.networking

actual suspend fun <T> platformSafeCall(
    execute: suspend () -> io.ktor.client.statement.HttpResponse,
    handleResponse: suspend (io.ktor.client.statement.HttpResponse) -> com.mz.core.Result<T, com.mz.domain.util.DataError.Remote>
): com.mz.core.Result<T, com.mz.domain.util.DataError.Remote> {
    TODO("Not yet implemented")
}