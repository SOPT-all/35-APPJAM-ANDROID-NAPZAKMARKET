package com.napzak.market.presentation.detailpage.type

enum class ProductCondition(val label: String) {
    EXCELLENT_CONDITION("아주 좋은 상태"),
    BRAND_NEW("미개봉"),
    SLIGHTLY_USED("약간의 사용감"),
    USED("사용감 있음");

    companion object {
        fun fromCondition(condition: String?): ProductCondition {
            return when (condition) {
                EXCELLENT_CONDITION.label -> EXCELLENT_CONDITION
                BRAND_NEW.label -> BRAND_NEW
                SLIGHTLY_USED.label -> SLIGHTLY_USED
                USED.label -> USED
                else -> BRAND_NEW
            }
        }
    }
}
