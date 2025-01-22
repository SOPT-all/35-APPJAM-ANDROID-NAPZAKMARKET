package com.napzak.market.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.type.ProductConditionType
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.genre.usecase.GenreSearchUseCase
import com.napzak.market.domain.registration.usecase.PresignedUrlUseCase
import com.napzak.market.domain.registration.usecase.ImageUriUseCase
import com.napzak.market.presentation.registration.state.RegistrationUiState
import com.napzak.market.presentation.registration.type.NumeralInputType
import com.napzak.market.presentation.registration.type.PlainTextInputType
import com.napzak.market.presentation.registration.type.PostFeeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val presignedUrlUseCase: PresignedUrlUseCase,
    private val imageUriUseCase: ImageUriUseCase,
    private val genreSearchUseCase: GenreSearchUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState = _uiState.asStateFlow()

    private val _searchTerm: MutableStateFlow<String> = MutableStateFlow("")
    val searchTerm = _searchTerm.asStateFlow()

//    init {
//        // TODO: 더미 데이터
//        _uiState.update {
//            it.copy(
//                genreList = listOf("건담")
//            )
//        }
//    }

    fun updateTradeType(newTradeType: TradeType) = _uiState.update { currentState ->
        currentState.copy(tradeType = newTradeType)
    }

    fun updatePhotoList(newImageUrlList: List<String>) = _uiState.update { currentState ->
        currentState.copy(imageUri = currentState.imageUri + newImageUrlList)
    }

    fun deletePhoto(photoIndex: Int) = _uiState.update { currentState ->
        currentState.copy(imageUri = currentState.imageUri.filterIndexed { index, _ -> index != photoIndex })
    }

    fun changeRepresentPhoto(newPhoto: Int) = _uiState.update { currentState ->
        val newImageUrlList = currentState.imageUri.toMutableList()
        newImageUrlList.add(0, newImageUrlList.removeAt(newPhoto))
        currentState.copy(imageUri = newImageUrlList)
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

//    fun fetchGenreList() = _uiState.update { currentState ->
//        currentState.copy(genreList = currentState.genreList)
//    }

    fun updateGenre(newGenre: String) = _uiState.update { currentState ->
        currentState.copy(genre = newGenre)
    }

    private fun updateSearchValue(newValue: String) = _searchTerm.update { newValue }

    fun updateSearchTerm(newValue: String) = viewModelScope.launch {
        updateSearchValue(newValue)
    }

//    fun updateSearchTerm(newSearchTerm: String) = _uiState.update { currentState ->
//        currentState.copy(searchTerm = newSearchTerm)
//    }

    @OptIn(FlowPreview::class)
    fun debounce() = viewModelScope.launch {
        _searchTerm.debounce(DEBOUNCE_DELAY)
            .collectLatest { debounced ->
                getGenres(debounced)
            }
    }

    private fun getGenres(searchTerm: String) = viewModelScope.launch {
        genreSearchUseCase(searchTerm)
            .onSuccess { response ->
                _uiState.update { currentState ->
                    currentState.copy(
                        genreList = response
                    )
                }
            }
            .onFailure { response ->
                Timber.d(response.message.toString())
            }
    }


    fun searchGenre() = viewModelScope.launch { }

    fun updateProductCondition(newCondition: ProductConditionType) =
        _uiState.update { currentState ->
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
                && _uiState.value.imageUri.isNotEmpty()

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
        val result = presignedUrlUseCase(_uiState.value.imageUri)

        result.onSuccess { presignedUrlMap ->
            uploadImageToS3(urlMap = presignedUrlMap)
        }.onFailure {
            /* TODO: presignedUrl 받아 오기 실패 로직 */
        }
    }

    private fun uploadImageToS3(urlMap: LinkedHashMap<String, String>) = viewModelScope.launch {
        val imageUris = _uiState.value.imageUri
        val sortedPresignedUrls = urlMap.entries.sortedBy {
            it.key.substringAfter("image_").toInt()
        }

        val urlFilePairs = sortedPresignedUrls.zip(imageUris) { urlEntry, uri ->
            urlEntry.value to uri
        }
        urlFilePairs.forEach { (presignedUrl, imageUri) ->
            val result2 = imageUriUseCase(presignedUrl, imageUri)
            result2.onSuccess {
            }
        }
    }

    companion object {
        private const val MAX_TITLE_LENGTH = 48
        private const val MAX_DESCRIPTION_LENGTH = 240
        private const val MAX_PURCHASE_PRICE = 999
        private const val MAX_SALE_PRICE = 1_000_000
        private const val MAX_NORMAL_POST_FEE = 30_000
        private const val MAX_HALF_POST_FEE = 5_000
        private const val DEBOUNCE_DELAY = 500L
    }
}
