package com.napzak.market.core.type

enum class ProductConditionType(val label: String) {
    GOOD("아주 좋은 상태"),
    UNUSED("미개봉"),
    SOSO("약간의 사용감"),
    USED("사용감 있음");

    companion object {
        fun fromCondition(condition: String?): ProductConditionType {
            return when (condition) {
                GOOD.label -> GOOD
                UNUSED.label -> UNUSED
                SOSO.label -> SOSO
                USED.label -> USED
                else -> UNUSED
            }
        }
    }
}
