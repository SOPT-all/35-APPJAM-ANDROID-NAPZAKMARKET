package com.napzak.market.presentation.detailpage.type

import androidx.annotation.StringRes
import com.napzak.market.R

enum class ProductCondition(@StringRes val labelResId: Int) {
    BRAND_NEW(R.string.detail_product_condition_brand_new),
    SLIGHTLY_USED(R.string.detail_product_condition_slightly_used),
    USED(R.string.detail_product_condition_used),
    LIGHTLY_USED(R.string.detail_product_condition_lightly_used);

    companion object {
        fun fromCondition(condition: String?): ProductCondition {
            return when (condition) {
                BRAND_NEW.name -> BRAND_NEW
                SLIGHTLY_USED.name -> SLIGHTLY_USED
                USED.name -> USED
                LIGHTLY_USED.name -> LIGHTLY_USED
                else -> BRAND_NEW
            }
        }
    }
}
