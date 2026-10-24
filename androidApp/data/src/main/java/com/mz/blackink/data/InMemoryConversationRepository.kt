package com.mz.blackink.data

import com.mz.blackink.data.domain.ConversationRepository
import com.mz.blackink.shared.domain.Conversation
import com.mz.blackink.shared.domain.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID

/** Demo conversations and messages are local and discarded when the process exits. */
class InMemoryConversationRepository(seedDemo: Boolean = true) : ConversationRepository {
    private val state = MutableStateFlow(if (seedDemo) demoConversations else emptyList())
    private val messageState = MutableStateFlow(if (seedDemo) demoMessages else emptyList())
    override val conversations = state.asStateFlow()
    override val messages = messageState.asStateFlow()

    override suspend fun createDraft() {
        createConversation("Draft ${state.value.size + 1}")
    }

    override suspend fun createConversation(title: String): String {
        require(title.isNotBlank()) { "A conversation needs a name" }
        val id = UUID.randomUUID().toString()
        state.update { it + Conversation(id, title.trim(), "No messages yet", time = "Now") }
        return id
    }

    override suspend fun sendMessage(conversationId: String, text: String) {
        if (text.isBlank()) return
        require(state.value.any { it.id == conversationId }) { "Conversation not found" }
        val time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
        messageState.update { it + Message(UUID.randomUUID().toString(), conversationId, text.trim(), time, true) }
        state.update { list ->
            list.map { if (it.id == conversationId) it.copy(preview = text.trim(), time = time, isOutgoing = true) else it }
        }
    }

    override fun markRead(conversationId: String) {
        state.update { list -> list.map { if (it.id == conversationId) it.copy(unreadCount = 0) else it } }
    }
}

private val demoConversations = listOf(
    Conversation("saved", "Saved Messages", "A little space for everything worth keeping.", "12:42", isPinned = true, avatarColor = 0xFF55A4DB),
    Conversation("maya", "Maya Chen", "That sounds perfect. See you there!", "12:38", 2, avatarColor = 0xFFE4A05A),
    Conversation("studio", "The Studio", "Alex: Just shared the latest concepts", "12:24", 14, isGroup = true, isPinned = true, avatarColor = 0xFF8D85CE),
    Conversation("james", "James Wilson", "You: I'll send them over this afternoon", "11:56", isOutgoing = true, avatarColor = 0xFF6EB4A0),
    Conversation("weekend", "Weekend people", "Sofia: Who's up for a little adventure?", "11:32", 5, isGroup = true, isMuted = true, avatarColor = 0xFFDA8FA0),
    Conversation("olivia", "Olivia Parker", "The photos turned out so good", "10:48", avatarColor = 0xFF7CABC4),
    Conversation("design", "Design collective", "New inspiration for your Monday", "09:15", 32, isGroup = true, isMuted = true, avatarColor = 0xFF78A97B),
    Conversation("noah", "Noah Williams", "You: Thanks for the recommendation!", "Yesterday", isOutgoing = true, avatarColor = 0xFFC28A6C),
    Conversation("emma", "Emma Davis", "Let's catch up soon", "Yesterday", avatarColor = 0xFFA58EC7),
    Conversation("bookclub", "Between the lines", "Daniel: Next chapter, anyone?", "Mon", isGroup = true, avatarColor = 0xFF6BAFBC),
)

private val demoMessages = demoConversations.flatMap { conversation ->
    if (conversation.id == "saved") {
        listOf(Message("saved-1", "saved", "Welcome to your Saved Messages. Keep notes, ideas, and little reminders here.", "12:42", true))
    } else {
        listOf(
            Message("${conversation.id}-1", conversation.id, "Hey! Hope your day is going well.", "11:20", false),
            Message("${conversation.id}-2", conversation.id, "It is! Just taking a moment to catch up on everything.", "11:22", true),
            Message("${conversation.id}-3", conversation.id, "By the way, are we still on for coffee this weekend?", "11:24", false),
            Message("${conversation.id}-4", conversation.id, "Absolutely. How about our usual spot at 10?", "11:25", true),
            Message("${conversation.id}-5", conversation.id, conversation.preview.removePrefix("You: "), conversation.time, conversation.isOutgoing),
        )
    }
}
