package com.napzak.market.core.type

enum class TradeType(val label: String) {
    SELL(label = "팔아요"),
    BUY(label = "구해요");

    companion object {
        fun fromName(name: String): TradeType {
            return try {
                valueOf(name.uppercase())
            } catch (e: Exception) {
                SELL
            }
        }
    }
}
