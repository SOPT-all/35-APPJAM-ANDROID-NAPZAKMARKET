package com.napzak.market.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.type.ProductConditionType
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.registration.usecase.GetPresignedUrlUseCase
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
    private val getPresignedUrlUseCase: GetPresignedUrlUseCase,
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

    fun updateTradeType(newTradeType: TradeType) = _uiState.update { currentState ->
        currentState.copy(tradeType = newTradeType)
    }

    fun updatePhotoList(newImageUrlList: List<String>) = _uiState.update { currentState ->
        currentState.copy(imageUrl = currentState.imageUrl + newImageUrlList)
    }

    fun deletePhoto(photoIndex: Int) = _uiState.update { currentState ->
        currentState.copy(imageUrl = currentState.imageUrl.filterIndexed { index, _ -> index != photoIndex })
    }

    fun changeRepresentPhoto(newPhoto: Int) = _uiState.update { currentState ->
        val newImageUrlList = currentState.imageUrl.toMutableList()
        newImageUrlList.add(0, newImageUrlList.removeAt(newPhoto))
        currentState.copy(imageUrl = newImageUrlList)
    }

    fun updatePlainTextValue(
        newValue: String,
        inputType: PlainTextInputType,
    ) {
        when (inputType) {
            is PlainTextInputType.Title -> if (newValue.length <= MAX_TITLE_LENGTH) _uiState.update { currentState ->
                currentState.copy(title = newValue)
            }

            is PlainTextInputType.Description -> if (newValue.length <= MAX_DESCRIPTION_LENGTH) _uiState.update { currentState ->
                currentState.copy(description = newValue)
            }
        }
    }

    fun updateNumericValue(
        newValue: String,
        inputType: NumeralInputType,
    ) {
        when (inputType) {
            is NumeralInputType.ProductPurchasePrice -> _uiState.update { currentState ->
                currentState.copy(productPurchasePrice = formatPriceValue(newValue, MAX_PURCHASE_PRICE))
            }

            is NumeralInputType.ProductSalePrice -> _uiState.update { currentState ->
                currentState.copy(productSalePrice = formatPriceValue(newValue, MAX_SALE_PRICE))
            }

            is NumeralInputType.NormalPostFee -> _uiState.update { currentState ->
                currentState.copy(normalPostFee = formatPriceValue(newValue, MAX_NORMAL_POST_FEE))
            }

            is NumeralInputType.HalfPostFee -> _uiState.update { currentState ->
                currentState.copy(halfPostFee = formatPriceValue(newValue, MAX_HALF_POST_FEE))
            }
        }
    }

    private fun formatPriceValue(
        input: String,
        maxValue: Int,
    ): String {
        if (input.isEmpty()) return ""

        val rawValue = input.replace(",", "").toIntOrNull() ?: 0
        val limitedValue = rawValue.coerceAtMost(maxValue)

        return DecimalFormat("#,###").format(limitedValue)
    }

    fun fetchGenreList() = _uiState.update { currentState ->
        currentState.copy(genreList = currentState.genreList)
    }

    fun updateGenre(newGenre: String) = _uiState.update { currentState ->
        currentState.copy(genre = newGenre)
    }

    fun updateSearchTerm(newSearchTerm: String) = _uiState.update { currentState ->
        currentState.copy(searchTerm = newSearchTerm)
    }

    fun searchGenre() = viewModelScope.launch { }

    fun updateProductCondition(newCondition: ProductConditionType) = _uiState.update { currentState ->
        currentState.copy(productCondition = newCondition)
    }

    fun updatePostFeeType(newPostFeeType: PostFeeType) = _uiState.update { currentState ->
        currentState.copy(isPostFeeIncluded = newPostFeeType == PostFeeType.INCLUDED)
    }

    fun updateNormalPostState(newCheckState: Boolean) = _uiState.update { currentState ->
        currentState.copy(isNormalPostChecked = newCheckState) }

    fun updateHalfPostState(newCheckState: Boolean) = _uiState.update { currentState ->
        currentState.copy(isHalfPostChecked = newCheckState)
    }

    fun updateOfferAvailability(isAvailable: Boolean) = _uiState.update { currentState ->
        currentState.copy(isOfferAvailable = isAvailable)
    }

    fun updateButtonState() {
        val isCommonFieldsValid = _uiState.value.title.isNotEmpty()
                && _uiState.value.description.isNotEmpty()
                && _uiState.value.imageUrl.isNotEmpty()

        val isPurchaseConditionValid = _uiState.value.tradeType == TradeType.BUY
                && _uiState.value.productPurchasePrice.isNotEmpty()

        val isPostFeeValid = when {
            _uiState.value.isNormalPostChecked && _uiState.value.normalPostFee.isNotEmpty()
                    && (!_uiState.value.isHalfPostChecked || _uiState.value.halfPostFee.isNotEmpty()) -> true

            !_uiState.value.isNormalPostChecked && _uiState.value.isHalfPostChecked
                    && _uiState.value.halfPostFee.isNotEmpty() -> true

            else -> false
        }

        val isSaleConditionValid = _uiState.value.tradeType == TradeType.SELL
                && _uiState.value.productCondition != null && _uiState.value.productSalePrice.isNotEmpty()
                && (_uiState.value.isPostFeeIncluded || isPostFeeValid)

        val isButtonEnabled = isCommonFieldsValid && (isPurchaseConditionValid || isSaleConditionValid)

        _uiState.update { currentState ->
            currentState.copy(isButtonEnabled = isButtonEnabled)
        }
    }

    fun getPresignedUrl() = viewModelScope.launch {
        val presignedUrlMap = getPresignedUrlUseCase(_uiState.value.imageUrl)
    }

    companion object {
        private const val MAX_TITLE_LENGTH = 48
        private const val MAX_DESCRIPTION_LENGTH = 240
        private const val MAX_PURCHASE_PRICE = 999
        private const val MAX_SALE_PRICE = 1_000_000
        private const val MAX_NORMAL_POST_FEE = 30_000
        private const val MAX_HALF_POST_FEE = 5_000
    }
}
