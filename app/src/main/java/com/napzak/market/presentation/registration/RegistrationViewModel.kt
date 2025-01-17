package com.napzak.market.presentation.registration

import androidx.lifecycle.ViewModel
import com.napzak.market.presentation.registration.state.RegistrationUiState
import com.napzak.market.presentation.registration.type.NumeralInputType
import com.napzak.market.presentation.registration.type.PlainTextInputType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    /* TODO: Repository 연결 */
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState = _uiState.asStateFlow()

    fun photoUpload() {

    }

    fun representPhotoChange(newPhoto: Int) {

    }

    fun deletePhoto(photoIndex: Int) {

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
            is NumeralInputType.ProductPurchasePrice -> if (newValue.length <= MAX_PURCHASE_PRICE_LENGTH) _uiState.update {
                it.copy(productPurchasePrice = formatNumericValue(newValue, MAX_PURCHASE_PRICE))
            }
            is NumeralInputType.ProductSalePrice -> _uiState.update {
                it.copy(productSalePrice = formatNumericValue(newValue, MAX_SALE_PRICE))
            }
            is NumeralInputType.NormalPostFee -> _uiState.update {
                it.copy(normalPostFee = formatNumericValue(newValue, MAX_NORMAL_POST_FEE))
            }
            is NumeralInputType.HalfPostFee -> _uiState.update {
                it.copy(halfPostFee = formatNumericValue(newValue, MAX_HALF_POST_FEE))
            }
        }
    }

    private fun formatNumericValue(
        newValue: String,
        maxValue: Int
    ) : String {
        val rawValue = newValue.filter { it.isDigit() }.toIntOrNull() ?: 0
        val limitedValue = rawValue.coerceAtMost(maxValue)
        return DecimalFormat("#,###").format(limitedValue)
    }

    companion object {
        private const val MAX_TITLE_LENGTH = 48
        private const val MAX_DESCRIPTION_LENGTH = 240
        private const val MAX_PURCHASE_PRICE_LENGTH = 3
        private const val MAX_PURCHASE_PRICE = 999
        private const val MAX_SALE_PRICE = 1_000_000
        private const val MAX_NORMAL_POST_FEE = 30_000
        private const val MAX_HALF_POST_FEE = 5_000
    }
}
