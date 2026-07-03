package com.mz.data.networking

import com.mz.domain.AuthInfo
import com.mz.domain.User
import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoSerializable(
    val accessToken: String,
    val refreshToken: String,
    val user: UserSerializable
)

@Serializable
data class UserSerializable(
    val id: String,
    val email: String,
    val username: String,
    val profilePictureUrl: String?
)

fun AuthInfoSerializable.toDomain(): AuthInfo {
    return AuthInfo(
        accessToken = accessToken,
        refreshToken = refreshToken,
        user = user.toDomain()
    )
}

fun UserSerializable.toDomain(): User {
    return User(
        id = id,
        email = email,
        username = username,
        profilePictureUrl = profilePictureUrl
    )
}

@Serializable
data class RefreshRequest(
    val refreshToken: String
)
