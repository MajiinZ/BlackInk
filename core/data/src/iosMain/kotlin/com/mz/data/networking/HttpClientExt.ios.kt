package com.mz.data.networking

import com.mz.domain.util.DataError
import com.mz.domain.util.DataError.Remote
import com.mz.domain.util.DataError.Remote.NO_INTERNET
import com.mz.domain.util.DataError.Remote.REQUEST_TIMEOUT
import com.mz.domain.util.DataError.Remote.SERIALIZATION
import com.mz.domain.util.DataError.Remote.UNKNOWN
import com.mz.domain.util.Result
import com.mz.domain.util.Result.Failure
import io.ktor.client.engine.darwin.DarwinHttpRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import platform.Foundation.NSURLErrorDataNotAllowed
import platform.Foundation.NSURLErrorDomain
import platform.Foundation.NSURLErrorNetworkConnectionLost
import platform.Foundation.NSURLErrorNotConnectedToInternet

actual suspend fun <T> platformSafeCall(
    execute: suspend () -> HttpResponse,
    handleResponse: suspend (HttpResponse) -> Result<T, DataError.Remote>
): Result<T, DataError.Remote> {


    return try {
        val response = execute()
        handleResponse(response)
    } catch (e: DarwinHttpRequestException) {
        e.origin.code
        Failure(REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        Failure(NO_INTERNET)
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

private fun handleDarwinException(e: DarwinHttpRequestException): Result<Nothing, DataError.Remote> {
    val nsError = e.origin
    // TODO: map nsError → specific DataError.Remote
    return if (nsError.domain == NSURLErrorDomain) {
        when(nsError.code){
            NSURLErrorNotConnectedToInternet -> Failure(NO_INTERNET)
            NSURLErrorNetworkConnectionLost -> Failure(REQUEST_TIMEOUT)
            NSURLErrorDataNotAllowed -> Failure(REQUEST_TIMEOUT)
            else -> Failure(UNKNOWN)
        }

    }
}

