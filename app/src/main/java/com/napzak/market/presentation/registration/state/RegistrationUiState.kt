package com.napzak.market.presentation.registration.state

import androidx.compose.runtime.Immutable

@Immutable
data class RegistrationUiState(
    val imageUrlList: List<String> = emptyList(),
    val genre: String = "",
    val title: String = "",
    val description: String = "",

    val productCondition: Int = -1,
    val productSalePrice: String = "",
    val normalPostFee: String = "",
    val halfPostFee: String = "",

    val productPurchasePrice: String = "",
    val isOfferAvailable: Boolean = false,
)
