package com.mz.blackink.data

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class InMemoryConversationRepositoryTest {
    @Test
    fun draftsHaveUniqueIdsAndKeepInsertionOrder() = runTest {
        val repository = InMemoryConversationRepository()
        assertTrue(repository.conversations.value.isEmpty())
        repeat(3) { repository.createDraft() }
        val drafts = repository.conversations.value
        assertEquals(listOf("Draft 1", "Draft 2", "Draft 3"), drafts.map { it.title })
        assertEquals(3, drafts.map { it.id }.distinct().size)
    }

    @Test
    fun aNewRepositoryDoesNotRetainPreviousDrafts() = runTest {
        val original = InMemoryConversationRepository()
        original.createDraft()
        assertEquals(1, original.conversations.value.size)
        assertTrue(InMemoryConversationRepository().conversations.value.isEmpty())
    }
}
