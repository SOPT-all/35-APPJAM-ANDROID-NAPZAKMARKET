package com.napzak.market.presentation.detailpage.type

enum class ProductChipType(val label: String) {
    SELL("팔아요"),
    BUY("구해요");

    companion object {
        fun fromLabel(label: String): ProductChipType? {
            return ProductChipType.entries.find { it.label == label }
        }
    }
}
