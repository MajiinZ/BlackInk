package com.mz.blackink.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mz.blackink.data.domain.ConversationRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ConversationRepository) : ViewModel() {
    val conversations = repository.conversations

    fun createDraft() {
        viewModelScope.launch { repository.createDraft() }
    }
}
