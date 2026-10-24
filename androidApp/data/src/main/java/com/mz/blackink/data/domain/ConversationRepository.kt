package com.mz.blackink.data.domain

import com.mz.blackink.shared.domain.Conversation
import kotlinx.coroutines.flow.StateFlow

interface ConversationRepository {
    val conversations: StateFlow<List<Conversation>>
    suspend fun createDraft()
}
