package com.mz.blackink.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mz.blackink.data.domain.ConversationRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ConversationRepository) : ViewModel() {
    val conversations = repository.conversations
    val messages = repository.messages

    fun createDraft() {
        viewModelScope.launch { repository.createDraft() }
    }

    fun createConversation(title: String, onCreated: (String) -> Unit) {
        viewModelScope.launch { onCreated(repository.createConversation(title)) }
    }

    fun sendMessage(id: String, text: String) {
        viewModelScope.launch { repository.sendMessage(id, text) }
    }

    fun markRead(id: String) = repository.markRead(id)
}
