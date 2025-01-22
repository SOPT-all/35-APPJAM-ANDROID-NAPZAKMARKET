package com.napzak.market.domain.chat.repository

import com.napzak.market.domain.chat.model.ChatInfo

interface ChatRepository {
    suspend fun fetchChatInfo(productId: Long): Result<ChatInfo>
}
