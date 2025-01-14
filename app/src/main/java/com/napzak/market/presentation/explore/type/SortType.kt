package com.napzak.market.presentation.explore.type

enum class SortType(val label: String) {
    RECENT(label = "최신순"),
    POPULAR(label = "인기순"),
    HIGH_PRICE(label = "고가순"),
    LOW_PRICE(label = "저가순");

    companion object {
        fun getSortLabel(type: String): String {
            return entries.find { it.name == type }?.label ?: "잘못된 SortType 값 입니다."
        }
    }
}