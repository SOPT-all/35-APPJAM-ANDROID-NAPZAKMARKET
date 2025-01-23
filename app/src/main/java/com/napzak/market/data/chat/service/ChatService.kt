package com.napzak.market.data.chat.service

import com.napzak.market.core.network.BaseResponse
import com.napzak.market.data.chat.dto.ChatInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatService {
    @GET("products/chat/{productId}")
    suspend fun getChatInfo(
        @Path("productId") productId: Long,
    ): BaseResponse<ChatInfoResponse>
}
