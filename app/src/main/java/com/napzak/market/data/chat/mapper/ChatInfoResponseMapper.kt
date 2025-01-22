package com.napzak.market.data.chat.mapper

import com.napzak.market.data.chat.dto.ChatInfoResponse
import com.napzak.market.domain.chat.model.ChatInfo

fun ChatInfoResponse.toDomain(): ChatInfo = ChatInfo(
    nickname = nickname,
    firstPhoto = firstPhoto,
    tradeType = tradeType,
    title = title,
    price = price,
    isPriceNegotiable = isPriceNegotiable,
)
