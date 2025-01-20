package com.napzak.market.presentation.registration.state

import androidx.compose.runtime.Immutable
import com.napzak.market.core.type.ProductConditionType
import com.napzak.market.core.type.TradeType

@Immutable
data class RegistrationUiState(
    val tradeType: TradeType? = null,
    val imageUrlList: List<String> = emptyList(),
    val searchTerm: String = "",
    val genre: String = "",
    val genreList: List<String> = emptyList(),
    val title: String = "",
    val description: String = "",
    val isButtonEnabled: Boolean = false,

    val productCondition: ProductConditionType? = null,
    val productSalePrice: String = "",
    val isPostFeeIncluded: Boolean = true,
    val isNormalPostChecked: Boolean = false,
    val normalPostFee: String = "",
    val isHalfPostChecked: Boolean = false,
    val halfPostFee: String = "",

    val productPurchasePrice: String = "",
    val isOfferAvailable: Boolean = false,
)
