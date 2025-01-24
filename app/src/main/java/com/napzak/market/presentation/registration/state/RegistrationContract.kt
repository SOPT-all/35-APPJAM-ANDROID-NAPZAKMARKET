package com.napzak.market.presentation.registration.state

import androidx.compose.runtime.Immutable
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.type.ProductConditionType
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.genre.model.Genre

class RegistrationContract {
    @Immutable
    data class RegistrationUiState(
        val tradeType: TradeType? = null,
        val imageUri: List<String> = emptyList(),
        val genre: Genre? = null,
        val genreList: List<Genre> = emptyList(),
        val title: String = "",
        val description: String = "",
        val isButtonEnabled: Boolean = false,
        val productId: Long = -1L,

        val productCondition: ProductConditionType? = null,
        val productSalePrice: String = "",
        val isPostFeeIncluded: Boolean = true,
        val isNormalPostChecked: Boolean = false,
        val normalPostFee: String = "",
        val isHalfPostChecked: Boolean = false,
        val halfPostFee: String = "",

        val productPurchasePrice: String = "",
        val isOfferAvailable: Boolean = false,

        val loadState: UiState<Unit> = UiState.Empty,
    )

    sealed class RegistrationSideEffect {
        data class OnDetailNavigate(val productId: Long) : RegistrationSideEffect()
    }

    sealed class RegistrationEvent
}