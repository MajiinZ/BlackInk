package com.mz.data.networking


import com.mz.domain.util.DataError
import com.mz.domain.util.DataError.Remote
import com.mz.domain.util.DataError.Remote.*
import com.mz.domain.util.Result
import com.mz.domain.util.Result.Failure
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import java.net.ConnectException
import java.net.UnknownHostException


actual suspend fun <T> platformSafeCall(
    execute: suspend () -> HttpResponse,
    handleResponse: suspend (HttpResponse) -> Result<T, DataError.Remote>
): Result<T, DataError.Remote> {
    return try {
        val response = execute()
        handleResponse(response)
    } catch (e: UnknownHostException) {
        Failure(NO_INTERNET)
    } catch (e: UnresolvedAddressException) {
        Failure(NO_INTERNET)
    } catch (e: ConnectException) {
        Failure(REQUEST_TIMEOUT)
    } catch (e: SerializationException) {
        Failure(SERIALIZATION)
    } catch (e: HttpRequestTimeoutException) {
        Failure(Remote.REQUEST_TIMEOUT)
    } catch (e: SerializationException) {
        Failure(SERIALIZATION)
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        Result.Failure(UNKNOWN)
    }
}

