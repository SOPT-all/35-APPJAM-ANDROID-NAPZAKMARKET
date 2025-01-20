package com.napzak.market.presentation.registration

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.napzak.market.R
import com.napzak.market.R.string.regi_title_placeholder
import com.napzak.market.R.string.regi_topbar_title
import com.napzak.market.core.designsystem.component.button.CommonButton
import com.napzak.market.core.designsystem.component.topbar.CloseTopBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.ProductConditionType
import com.napzak.market.core.type.TradeType
import com.napzak.market.presentation.registration.component.RegistrationBuyGroup
import com.napzak.market.presentation.registration.component.RegistrationGenreButton
import com.napzak.market.presentation.registration.component.RegistrationPhotoPicker
import com.napzak.market.presentation.registration.component.RegistrationPlainTextField
import com.napzak.market.presentation.registration.component.RegistrationSellGroup
import com.napzak.market.presentation.registration.state.RegistrationUiState
import com.napzak.market.presentation.registration.type.NumeralInputType
import com.napzak.market.presentation.registration.type.PlainTextInputType
import com.napzak.market.presentation.registration.type.PostFeeType
import timber.log.Timber

@Composable
fun RegistrationRoute(
    tradeType: String,
    navigateUp: () -> Unit,
    navigateToGenreSearch: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentListSize = (MAX_ITEMS - uiState.imageUrlList.size).coerceAtLeast(MIN_ITEMS)
    val getImageStorageLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        val updatedListSize = uiState.imageUrlList.size + uris.size
        if (updatedListSize <= 10) {
            viewModel.updatePhotoList(uris.map { it.toString() })
        } else {
            val remainingUris = uris.take(MAX_ITEMS - uiState.imageUrlList.size)
            viewModel.updatePhotoList(remainingUris.map { it.toString() })
        }
    }
    val getPhotoPickerLauncher = when (uiState.imageUrlList.size) {
        MAX_ITEMS - 1 -> rememberLauncherForActivityResult(
            ActivityResultContracts.PickVisualMedia()
        ) { uri: Uri? ->
            uri?.let { viewModel.updatePhotoList(listOf(it.toString())) }
        }
        else -> rememberLauncherForActivityResult(
            ActivityResultContracts.PickMultipleVisualMedia(maxItems = currentListSize)
        ) { uris: List<Uri> ->
            val updatedListSize = uiState.imageUrlList.size + uris.size
            if (updatedListSize <= 10) {
                viewModel.updatePhotoList(uris.map { it.toString() })
            } else {
                val remainingUris = uris.take(MAX_ITEMS - uiState.imageUrlList.size)
                viewModel.updatePhotoList(remainingUris.map { it.toString() })
            }
        }
    }

    LaunchedEffect(tradeType) {
        viewModel.updateTradeType(if (tradeType == TradeType.SELL.label) TradeType.SELL else TradeType.BUY)
    }

    RegistrationScreen(
        uiState = uiState,
        registrationType = if (tradeType == TradeType.SELL.label) TradeType.SELL else TradeType.BUY,
        onCloseClick = navigateUp,
        onPhotoClick = {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                if (MAX_ITEMS - uiState.imageUrlList.size > 0) getImageStorageLauncher.launch(INPUT_TYPE)
                else {
                    /* TODO: 최대 개수 초과 시 스낵바 */
                }
            }
            else {
                if (MAX_ITEMS - uiState.imageUrlList.size > 0) getPhotoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                else {
                    /* TODO: 최대 개수 초과 시 스낵바 */
                }
            }
        },
        onPhotoLongPress = viewModel::changeRepresentPhoto,
        onDeleteClick = viewModel::deletePhoto,
        onGenreClick = navigateToGenreSearch,
        onTitleChange = { viewModel.updatePlainTextValue(it, PlainTextInputType.Title) },
        onDescriptionChange = { viewModel.updatePlainTextValue(it, PlainTextInputType.Description) },
        onSalePriceChange = { viewModel.updateNumericValue(it, NumeralInputType.ProductSalePrice) },
        onProductConditionChange = viewModel::updateProductCondition,
        onPostFeeChange = viewModel::updatePostFeeType,
        onNormalPostFeeChange = { viewModel.updateNumericValue(it, NumeralInputType.NormalPostFee) },
        onHalfPostFeeChange = { viewModel.updateNumericValue(it, NumeralInputType.HalfPostFee) },
        onOfferCheckChange = viewModel::updateOfferAvailability,
        onPurchasePriceChange = { viewModel.updateNumericValue(it, NumeralInputType.ProductPurchasePrice) },
        updateButtonState = viewModel::updateButtonState,
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegistrationScreen(
    uiState: RegistrationUiState,
    registrationType: TradeType,
    onCloseClick: () -> Unit,
    onPhotoClick: () -> Unit,
    onPhotoLongPress: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    onGenreClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSalePriceChange: (String) -> Unit,
    onProductConditionChange: (ProductConditionType) -> Unit,
    onPostFeeChange: (PostFeeType) -> Unit,
    onNormalPostFeeChange: (String) -> Unit,
    onHalfPostFeeChange: (String) -> Unit,
    onPurchasePriceChange: (String) -> Unit,
    onOfferCheckChange: (Boolean) -> Unit,
    updateButtonState: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val paddedModifier = Modifier.padding(horizontal = 20.dp)
    val listState = rememberLazyListState()
//    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isNormalPostChecked by rememberSaveable { mutableStateOf(false) }
    var isHalfPostChecked by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(listState.isScrollInProgress) {
        if (listState.isScrollInProgress) {
            focusManager.clearFocus()
        }
    }

    LaunchedEffect(uiState) {
        val isCommonFieldsValid = uiState.title.isNotEmpty() && uiState.description.isNotEmpty() && uiState.imageUrlList.isNotEmpty()

        val isPurchaseConditionValid = uiState.tradeType == TradeType.BUY && uiState.productPurchasePrice.isNotEmpty()

//        val isPostFeeValid = when {
//            isNormalPostChecked && uiState.normalPostFee.isNotEmpty() && !isHalfPostChecked -> true
//            isNormalPostChecked && uiState.normalPostFee.isNotEmpty() && isHalfPostChecked && uiState.halfPostFee.isNotEmpty() -> true
//            isNormalPostChecked && uiState.normalPostFee.isEmpty() && isHalfPostChecked && uiState.halfPostFee.isNotEmpty() -> false
//            isNormalPostChecked && uiState.normalPostFee.isEmpty() && isHalfPostChecked && uiState.halfPostFee.isEmpty() -> false
//            !isNormalPostChecked && isHalfPostChecked && uiState.halfPostFee.isNotEmpty() -> true
//            !isNormalPostChecked && isHalfPostChecked && uiState.halfPostFee.isEmpty() -> false
//            isNormalPostChecked && uiState.normalPostFee.isNotEmpty() && isHalfPostChecked && uiState.halfPostFee.isEmpty() -> false
//            !isNormalPostChecked && !isHalfPostChecked -> false
//            else -> false
//        }
//
//        val isSaleConditionValid = uiState.tradeType == TradeType.SELL &&
//                uiState.productCondition != null &&
//                uiState.productSalePrice.isNotEmpty() &&
//                (uiState.isPostFeeIncluded || isPostFeeValid)


        val isSaleConditionValid = uiState.tradeType == TradeType.SELL && uiState.productSalePrice.isNotEmpty() && uiState.productCondition != null &&
                when {
            !uiState.isPostFeeIncluded && isNormalPostChecked && isHalfPostChecked && (uiState.normalPostFee.isEmpty() || uiState.halfPostFee.isEmpty()) -> false
            !uiState.isPostFeeIncluded && !isNormalPostChecked && !isHalfPostChecked -> false
            !uiState.isPostFeeIncluded && isNormalPostChecked && uiState.normalPostFee.isEmpty() -> false
            !uiState.isPostFeeIncluded && isHalfPostChecked && uiState.halfPostFee.isEmpty() -> false
            uiState.isPostFeeIncluded -> true
            else -> true
        }

        val isButtonEnabled = isCommonFieldsValid && (isPurchaseConditionValid || isSaleConditionValid)
        updateButtonState(isButtonEnabled)

        Timber.d(
            "tradeType: ${uiState.tradeType} " +
            "isCommonFieldsValid: $isCommonFieldsValid, " +
            "isCommonFieldsValid: ${uiState.title}, " +
            "isCommonFieldsValid: ${uiState.description}, " +
            "isCommonFieldsValid: ${uiState.imageUrlList}, " +
                    "isPurchaseConditionValid: $isPurchaseConditionValid, " +
                    "isSaleConditionValid: $isSaleConditionValid, " +
                    "isButtonEnabled: $isButtonEnabled"
        )
    }

    LazyColumn(
        modifier = modifier
            .background(NapzakMarketTheme.colors.white),
        state = listState,
    ) {
        stickyHeader {
            CloseTopBar(
                title = stringResource(regi_topbar_title, registrationType.label),
                onCloseClick = onCloseClick,
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = paddedModifier,
                text = stringResource(R.string.regi_product_image),
                style = NapzakMarketTheme.typography.bodySemi16,
                color = NapzakMarketTheme.colors.gray900,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = paddedModifier,
                text = stringResource(R.string.regi_product_image_description),
                style = NapzakMarketTheme.typography.bodyMedium14,
                color = NapzakMarketTheme.colors.gray600,
            )
            Spacer(modifier = Modifier.height(16.dp))
            RegistrationPhotoPicker(
                modifier = Modifier,
                imageUrlList = uiState.imageUrlList,
                onPhotoClick = onPhotoClick,
                onLongPress = onPhotoLongPress,
                onDeleteClick = onDeleteClick,
            )
        }
        item {
            Spacer(modifier = Modifier.height(40.dp))
            RegistrationGenreButton(
                modifier = paddedModifier,
                genre = uiState.genre,
                onGenreClick = onGenreClick,
            )
        }
        item {
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                modifier = paddedModifier,
                text = stringResource(R.string.regi_title),
                style = NapzakMarketTheme.typography.bodySemi16,
                color = NapzakMarketTheme.colors.gray900,
            )
            Spacer(modifier = Modifier.height(12.dp))
            RegistrationPlainTextField(
                modifier = paddedModifier,
                text = uiState.title,
                placeholder = stringResource(regi_title_placeholder),
                onTextChange = onTitleChange,
                isTitle = true,
                maxLength = 48,
            )
            Spacer(modifier = Modifier.height(35.dp))
        }
        item {
            Text(
                modifier = paddedModifier,
                text = stringResource(R.string.regi_description),
                style = NapzakMarketTheme.typography.bodySemi16,
                color = NapzakMarketTheme.colors.gray900,
            )
            Spacer(modifier = Modifier.height(12.dp))
            RegistrationPlainTextField(
                modifier = paddedModifier,
                text = uiState.description,
                placeholder = stringResource(R.string.regi_description_placeholder),
                onTextChange = onDescriptionChange,
                isTitle = false,
                maxLength = 240,
            )
        }
        if (registrationType == TradeType.SELL) {
            item {
                Spacer(modifier = Modifier.height(35.dp))
                RegistrationSellGroup(
                    modifier = paddedModifier,
                    salePrice = uiState.productSalePrice,
                    salePricePlaceHolder = stringResource(R.string.regi_price_placeholder),
                    onSalePriceChange = onSalePriceChange,
                    productCondition = uiState.productCondition,
                    onProductConditionChange = onProductConditionChange,
                    postFeeType = if (uiState.isPostFeeIncluded) PostFeeType.INCLUDED else PostFeeType.EXCLUDED,
                    onPostFeeChange = onPostFeeChange,
                    isNormalPostChecked = isNormalPostChecked,
                    onNormalPostCheckedChange = {
                        isNormalPostChecked = it
                        if (!isNormalPostChecked) onNormalPostFeeChange(BLANK)
                    },
                    normalPostFee = uiState.normalPostFee,
                    onNormalPostFeeChange = onNormalPostFeeChange,
                    isHalfPostChecked = isHalfPostChecked,
                    onHalfPostCheckedChange = {
                        isHalfPostChecked = it
                        if (!isHalfPostChecked) onHalfPostFeeChange(BLANK)
                    },
                    halfPostFee = uiState.halfPostFee,
                    onHalfPostFeeChange = onHalfPostFeeChange,
                )
            }
        }
        if (registrationType == TradeType.BUY) {
            item {
                Spacer(modifier = Modifier.height(35.dp))
                RegistrationBuyGroup(
                    modifier = paddedModifier,
                    number = uiState.productPurchasePrice,
                    onNumberChange = onPurchasePriceChange,
                    isOfferAvailable = uiState.isOfferAvailable,
                    onCheckChange = onOfferCheckChange,
                )
            }
        }
        item {
            CommonButton(
                modifier = paddedModifier
                    .fillMaxWidth(),
                text = stringResource(R.string.register),
                onClick = { /*TODO*/ },
                buttonColors = ButtonDefaults.buttonColors().copy(
                    containerColor = NapzakMarketTheme.colors.purple30,
                    contentColor = NapzakMarketTheme.colors.white,
                    disabledContainerColor = NapzakMarketTheme.colors.gray400,
                ),
                shape = RoundedCornerShape(12.dp),
                textStyle = NapzakMarketTheme.typography.bodyBold16,
                contentPadding = PaddingValues(vertical = 15.dp),
                isEnabled = uiState.isButtonEnabled,
            )
        }
    }
}

private const val INPUT_TYPE = "image/*"
private const val MAX_ITEMS = 10
private const val MIN_ITEMS = 2
private const val BLANK = ""

@Preview
@Composable
private fun RegistrationScreenPreview() {
    NapzakMarketTheme {
        RegistrationRoute(
            tradeType = "팔아요",
            navigateUp = { },
            navigateToGenreSearch = { },
        )
    }
}
