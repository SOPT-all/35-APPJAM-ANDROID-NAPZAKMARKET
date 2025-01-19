package com.napzak.market.presentation.detailpage.type

enum class ProductCondition(val label: String) {
    BRAND_NEW("미개봉"),
    SLIGHTLY_USED("약간의 사용감"),
    USED("사용감 있음"),
    LIGHTLY_USED("약간 사용감 있음");

    companion object {
        fun fromCondition(condition: String?): ProductCondition {
            return when (condition) {
                BRAND_NEW.label -> BRAND_NEW
                SLIGHTLY_USED.label -> SLIGHTLY_USED
                USED.label -> USED
                LIGHTLY_USED.label -> LIGHTLY_USED
                else -> BRAND_NEW
            }
        }
    }
}
