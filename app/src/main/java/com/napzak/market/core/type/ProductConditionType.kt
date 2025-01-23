package com.napzak.market.core.type

enum class ProductConditionType(val label: String) {
    NEW("미개봉"),
    LIKE_NEW("아주 좋은 상태"),
    SLIGHTLY_USED("약간의 사용감"),
    USED("사용감 있음");

    companion object {
        fun fromCondition(condition: String?): ProductConditionType {
            return when (condition) {
                LIKE_NEW.label -> LIKE_NEW
                NEW.label -> NEW
                SLIGHTLY_USED.label -> SLIGHTLY_USED
                USED.label -> USED
                else -> NEW
            }
        }

        fun fromConditionByName(condition: String?): ProductConditionType {
            return when (condition) {
                LIKE_NEW.name -> LIKE_NEW
                NEW.name -> NEW
                SLIGHTLY_USED.name -> SLIGHTLY_USED
                USED.name -> USED
                else -> NEW
            }
        }
    }
}
