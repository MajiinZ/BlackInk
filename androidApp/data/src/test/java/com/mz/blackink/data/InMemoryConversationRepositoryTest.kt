package com.mz.blackink.data

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class InMemoryConversationRepositoryTest {
    @Test
    fun sendingUpdatesOnlyTheSelectedConversationAndIgnoresBlankMessages() = runTest {
        val repository = InMemoryConversationRepository(seedDemo = false)
        val first = repository.createConversation("Maya")
        val second = repository.createConversation("James")
        repository.sendMessage(first, "  Hello Maya  ")
        repository.sendMessage(first, "   ")
        assertEquals(1, repository.messages.value.size)
        assertEquals(first, repository.messages.value.single().conversationId)
        assertEquals("Hello Maya", repository.messages.value.single().text)
        assertEquals("Hello Maya", repository.conversations.value.first { it.id == first }.preview)
        assertEquals("No messages yet", repository.conversations.value.first { it.id == second }.preview)
    }

    @Test
    fun openingDemoChatClearsOnlyItsUnreadCount() {
        val repository = InMemoryConversationRepository()
        assertEquals(2, repository.conversations.value.first { it.id == "maya" }.unreadCount)
        repository.markRead("maya")
        assertEquals(0, repository.conversations.value.first { it.id == "maya" }.unreadCount)
        assertEquals(14, repository.conversations.value.first { it.id == "studio" }.unreadCount)
    }

    @Test
    fun draftsHaveUniqueIdsAndKeepInsertionOrder() = runTest {
        val repository = InMemoryConversationRepository(seedDemo = false)
        assertTrue(repository.conversations.value.isEmpty())
        repeat(3) { repository.createDraft() }
        val drafts = repository.conversations.value
        assertEquals(listOf("Draft 1", "Draft 2", "Draft 3"), drafts.map { it.title })
        assertEquals(3, drafts.map { it.id }.distinct().size)
    }

    @Test
    fun aNewRepositoryDoesNotRetainPreviousDrafts() = runTest {
        val original = InMemoryConversationRepository(seedDemo = false)
        original.createDraft()
        assertEquals(1, original.conversations.value.size)
        assertTrue(InMemoryConversationRepository(seedDemo = false).conversations.value.isEmpty())
    }
}
