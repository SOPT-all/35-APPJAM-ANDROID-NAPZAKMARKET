package com.napzak.market.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.type.ProductConditionType
import com.napzak.market.core.type.TradeType
import com.napzak.market.presentation.registration.state.RegistrationUiState
import com.napzak.market.presentation.registration.type.NumeralInputType
import com.napzak.market.presentation.registration.type.PlainTextInputType
import com.napzak.market.presentation.registration.type.PostFeeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    /* TODO: Repository 연결 */
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState = _uiState.asStateFlow()

    init {
        // TODO: 더미 데이터
        _uiState.update {
            it.copy(
                genreList = listOf("건담")
            )
        }
    }

    fun updateTradeType(newTradeType: TradeType) = _uiState.update { it.copy(tradeType = newTradeType) }

    fun updatePhotoList(newImageUrlList: List<String>) = _uiState.update { it.copy(imageUrlList = it.imageUrlList + newImageUrlList) }

    fun deletePhoto(photoIndex: Int)  = _uiState.update { it.copy(imageUrlList = it.imageUrlList.filterIndexed { index, _ -> index != photoIndex }) }

    fun changeRepresentPhoto(newPhoto: Int) = _uiState.update {
        val newImageUrlList = it.imageUrlList.toMutableList()
        newImageUrlList.add(0, newImageUrlList.removeAt(newPhoto))
        it.copy(imageUrlList = newImageUrlList)
    }

    fun updatePlainTextValue(
        newValue: String,
        inputType: PlainTextInputType,
    ) {
        when (inputType) {
            is PlainTextInputType.Title -> if (newValue.length <= MAX_TITLE_LENGTH) _uiState.update {
                it.copy(title = newValue)
            }
            is PlainTextInputType.Description -> if (newValue.length <= MAX_DESCRIPTION_LENGTH) _uiState.update {
                it.copy(description = newValue)
            }
        }
    }

    fun updateNumericValue(
        newValue: String,
        inputType: NumeralInputType,
    ) {
        when (inputType) {
            is NumeralInputType.ProductPurchasePrice -> _uiState.update {
                it.copy(productPurchasePrice = formatPriceValue(newValue, MAX_PURCHASE_PRICE))
            }
            is NumeralInputType.ProductSalePrice -> _uiState.update {
                it.copy(productSalePrice = formatPriceValue(newValue, MAX_SALE_PRICE))
            }
            is NumeralInputType.NormalPostFee -> _uiState.update {
                it.copy(normalPostFee = formatPriceValue(newValue, MAX_NORMAL_POST_FEE))
            }
            is NumeralInputType.HalfPostFee -> _uiState.update {
                it.copy(halfPostFee = formatPriceValue(newValue, MAX_HALF_POST_FEE))
            }
        }
    }

    private fun formatPriceValue(
        input: String,
        maxValue: Int,
    ): String {
        val rawValue = input.replace(",", "").takeIf {
            it.isNotEmpty() && it.all { it.isDigit() }
        }?.toIntOrNull() ?: 0

        return if (rawValue == 0 && input.isEmpty()) "" else {
            val limitedValue = rawValue.coerceAtMost(maxValue)
            DecimalFormat("#,###").format(limitedValue)
        }
    }

    fun fetchGenreList() = _uiState.update { it.copy(genreList = it.genreList) }

    fun updateGenre(newGenre: String) = _uiState.update { it.copy(genre = newGenre) }

    fun updateSearchTerm(newSearchTerm: String) = _uiState.update { it.copy(searchTerm = newSearchTerm) }

    fun searchGenre() = viewModelScope.launch {  }

    fun updateProductCondition(newCondition: ProductConditionType) = _uiState.update { it.copy(productCondition = newCondition) }

    fun updatePostFeeType(newPostFeeType: PostFeeType) = _uiState.update { it.copy(isPostFeeIncluded = newPostFeeType == PostFeeType.INCLUDED) }

    fun updateNormalPostState(newCheckState: Boolean) = _uiState.update { it.copy(isNormalPostChecked = newCheckState) }

    fun updateHalfPostState(newCheckState: Boolean) = _uiState.update { it.copy(isHalfPostChecked = newCheckState) }

    fun updateOfferAvailability(isAvailable: Boolean) = _uiState.update { it.copy(isOfferAvailable = isAvailable) }

    fun updateButtonState(isButtonEnabled: Boolean) = _uiState.update { it.copy(isButtonEnabled = isButtonEnabled) }

    companion object {
        private const val MAX_TITLE_LENGTH = 48
        private const val MAX_DESCRIPTION_LENGTH = 240
        private const val MAX_PURCHASE_PRICE = 999
        private const val MAX_SALE_PRICE = 1_000_000
        private const val MAX_NORMAL_POST_FEE = 30_000
        private const val MAX_HALF_POST_FEE = 5_000
    }
}
