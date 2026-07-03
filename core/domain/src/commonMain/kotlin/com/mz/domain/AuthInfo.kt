package com.mz.domain

data class AuthInfo(
    val accessToken: String,
    val refreshToken: String,
    val user: User
)

data class User(
    val id: String,
    val email: String,
    val username: String,
    val profilePictureUrl: String?
)
