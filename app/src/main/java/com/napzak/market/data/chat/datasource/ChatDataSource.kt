package com.napzak.market.data.chat.datasource

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.chat.dto.ChatInfoResponse
import com.napzak.market.data.chat.service.ChatService
import javax.inject.Inject

class ChatDataSource @Inject constructor(
    private val service: ChatService,
) {
    suspend fun getChatInfo(productId: Long): BaseResponse<ChatInfoResponse> {
        return service.getChatInfo(productId)
    }
}
