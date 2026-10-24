package com.mz.blackink.data

import com.mz.blackink.data.domain.ConversationRepository
import com.mz.blackink.shared.domain.Conversation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

/** Local starter data only. Drafts are discarded when the process exits. */
class InMemoryConversationRepository : ConversationRepository {
    private val state = MutableStateFlow<List<Conversation>>(emptyList())
    override val conversations = state.asStateFlow()

    override suspend fun createDraft() {
        val id = UUID.randomUUID().toString()
        state.update { current ->
            current + Conversation(id, "Draft ${current.size + 1}", "Ready for your next idea")
        }
    }
}
