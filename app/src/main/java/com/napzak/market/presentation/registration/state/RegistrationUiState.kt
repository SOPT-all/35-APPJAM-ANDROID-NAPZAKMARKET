package com.napzak.market.presentation.registration.state

import androidx.compose.runtime.Immutable
import com.napzak.market.core.type.TradeType

@Immutable
data class RegistrationUiState(
    val tradeType: TradeType = TradeType.SELL,
    val imageUrlList: List<String> = emptyList(),
    val searchTerm: String = "",
    val genre: String = "",
    val genreList: List<String> = emptyList(),
    val title: String = "",
    val description: String = "",
    val isButtonEnabled: Boolean = false,

    val productCondition: Int = -1,
    val productSalePrice: String = "",
    val isPostFeeIncluded: Boolean = false,
    val normalPostFee: String = "",
    val halfPostFee: String = "",

    val productPurchasePrice: String = "",
    val isOfferAvailable: Boolean = false,
)
