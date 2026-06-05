package com.mz.domain.util

sealed interface DataError: Error {
    enum class Remote: DataError{
        BAD_REQUEST,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_FOUND,
        REQUEST_TIMEOUT,
        INTERNAL_SERVER_ERROR,
        CONFLICT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERVICE_UNAVAILABLE,
        SERIALIZATION,
        UNKNOWN

    }
    enum class Local: DataError{
        DISK_FULL,
        UNABLE_TO_WRITE,
        UNABLE_TO_READ,


    }
}