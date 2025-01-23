package com.napzak.market.data.chat.repositoryimpl

import com.napzak.market.data.chat.datasource.ChatDataSource
import com.napzak.market.data.chat.mapper.toDomain
import com.napzak.market.domain.chat.model.ChatInfo
import com.napzak.market.domain.chat.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val dataSource: ChatDataSource,
) : ChatRepository {
    override suspend fun fetchChatInfo(productId: Long): Result<ChatInfo> = runCatching {
        dataSource.getChatInfo(productId).data.toDomain()
    }
}
