package com.napzak.market.core.type

enum class ProductConditionType(
    val condition: String,
) {
    UNUSED(condition = "미개봉"),
    GOOD(condition = "아주 좋은 상태"),
    SOSO(condition = "약간의 사용감"),
    USED(condition = "사용감"),
}