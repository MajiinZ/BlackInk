package com.mz.domain

import kotlinx.coroutines.flow.Flow

interface SessionStorage {
    suspend fun set(authInfo: AuthInfo?)
    fun observeAuthInfo(): Flow<AuthInfo?>
}
