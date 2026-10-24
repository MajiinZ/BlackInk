package com.mz.blackink.shared.domain

data class Conversation(
    val id: String,
    val title: String,
    val preview: String,
    val time: String = "",
    val unreadCount: Int = 0,
    val isGroup: Boolean = false,
    val isMuted: Boolean = false,
    val isPinned: Boolean = false,
    val isOutgoing: Boolean = false,
    val avatarColor: Long = 0xFF62A5D2,
)

data class Message(
    val id: String,
    val conversationId: String,
    val text: String,
    val time: String,
    val isOutgoing: Boolean,
)
