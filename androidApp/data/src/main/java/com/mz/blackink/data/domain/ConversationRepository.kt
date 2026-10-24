package com.mz.blackink.data.domain

import com.mz.blackink.shared.domain.Conversation
import com.mz.blackink.shared.domain.Message
import kotlinx.coroutines.flow.StateFlow

interface ConversationRepository {
    val conversations: StateFlow<List<Conversation>>
    val messages: StateFlow<List<Message>>
    suspend fun createDraft()
    suspend fun createConversation(title: String): String
    suspend fun sendMessage(conversationId: String, text: String)
    fun markRead(conversationId: String)
}
