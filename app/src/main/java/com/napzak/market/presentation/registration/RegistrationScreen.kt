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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.napzak.market.R.string.regi_description
import com.napzak.market.R.string.regi_description_placeholder
import com.napzak.market.R.string.regi_price_placeholder
import com.napzak.market.R.string.regi_product_image
import com.napzak.market.R.string.regi_product_image_description
import com.napzak.market.R.string.regi_title
import com.napzak.market.R.string.regi_title_placeholder
import com.napzak.market.R.string.regi_topbar_title
import com.napzak.market.R.string.register
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

@Composable
fun RegistrationRoute(
    tradeType: String,
    navigateUp: () -> Unit,
    onGenreSearchNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentImageSize = (MAX_ITEMS - uiState.imageUri.size).coerceAtLeast(MIN_ITEMS)
    val imageStorageLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        handleUris(uris, currentImageSize, viewModel::updatePhotoList)
    }
    val photoPickerLauncher = when (uiState.imageUri.size) {
        MAX_ITEMS - 1 -> rememberLauncherForActivityResult(
            ActivityResultContracts.PickVisualMedia()
        ) { uri: Uri? ->
            uri?.let { viewModel.updatePhotoList(listOf(it.toString())) }
        }

        else -> rememberLauncherForActivityResult(
            ActivityResultContracts.PickMultipleVisualMedia(maxItems = currentImageSize)
        ) { uris: List<Uri> -> handleUris(uris, currentImageSize, viewModel::updatePhotoList) }
    }

    LaunchedEffect(Unit) {
        viewModel.updateTradeType(if (tradeType == TradeType.SELL.label) TradeType.SELL else TradeType.BUY)
    }

    RegistrationScreen(
        uiState = uiState,
        registrationType = if (tradeType == TradeType.SELL.label) TradeType.SELL else TradeType.BUY,
        onCloseClick = navigateUp,
        onPhotoClick = {
            val remainImageSize = MAX_ITEMS - uiState.imageUri.size

            when {
                remainImageSize <= ZERO -> { /* TODO: 최대 개수 초과 시 스낵바 처리 */ }

                Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU -> imageStorageLauncher.launch(INPUT_TYPE)

                else -> photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        },
        onPhotoPress = viewModel::changeRepresentPhoto,
        onDeleteClick = viewModel::deletePhoto,
        onGenreClick = onGenreSearchNavigate,
        onTitleChange = { title -> viewModel.updatePlainTextValue(title, PlainTextInputType.Title) },
        onDescriptionChange = { description -> viewModel.updatePlainTextValue(description, PlainTextInputType.Description) },
        onSalePriceChange = { salePrice -> viewModel.updateNumericValue(salePrice, NumeralInputType.ProductSalePrice) },
        onProductConditionChange = viewModel::updateProductCondition,
        onPostFeeChange = viewModel::updatePostFeeType,
        onNormalPostStateChange = viewModel::updateNormalPostState,
        onNormalPostFeeChange = { normalPostFee -> viewModel.updateNumericValue(normalPostFee, NumeralInputType.NormalPostFee) },
        onHalfPostStateChange = viewModel::updateHalfPostState,
        onHalfPostFeeChange = { halfPostFee -> viewModel.updateNumericValue(halfPostFee, NumeralInputType.HalfPostFee) },
        onOfferCheckChange = viewModel::updateOfferAvailability,
        onPurchasePriceChange = { purchasePrice -> viewModel.updateNumericValue(purchasePrice, NumeralInputType.ProductPurchasePrice) },
        onButtonStateChange = viewModel::updateButtonState,
        onRegistrationClick = viewModel::getPresignedUrl,
        modifier = modifier,
    )
}

private fun handleUris(
    uris: List<Uri>,
    remainSlots: Int,
    updatePhoto: (List<String>) -> Unit,
) {
    if (uris.size <= remainSlots) {
        updatePhoto(uris.map { it.toString() })
    } else {
        val limitedUris = uris.take(remainSlots)
        updatePhoto(limitedUris.map { it.toString() })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegistrationScreen(
    uiState: RegistrationUiState,
    registrationType: TradeType,
    onCloseClick: () -> Unit,
    onPhotoClick: () -> Unit,
    onPhotoPress: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    onGenreClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSalePriceChange: (String) -> Unit,
    onProductConditionChange: (ProductConditionType) -> Unit,
    onPostFeeChange: (PostFeeType) -> Unit,
    onNormalPostStateChange: (Boolean) -> Unit,
    onNormalPostFeeChange: (String) -> Unit,
    onHalfPostStateChange: (Boolean) -> Unit,
    onHalfPostFeeChange: (String) -> Unit,
    onPurchasePriceChange: (String) -> Unit,
    onOfferCheckChange: (Boolean) -> Unit,
    onButtonStateChange: () -> Unit,
    onRegistrationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val paddedModifier = Modifier.padding(horizontal = 20.dp)
    val listState = rememberLazyListState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(listState.isScrollInProgress) {
        if (listState.isScrollInProgress) {
            focusManager.clearFocus()
        }
    }

    LaunchedEffect(uiState) { onButtonStateChange() }

    LazyColumn(
        modifier = modifier.background(NapzakMarketTheme.colors.white),
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
                text = stringResource(regi_product_image),
                style = NapzakMarketTheme.typography.bodySemi16,
                color = NapzakMarketTheme.colors.gray900,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = paddedModifier,
                text = stringResource(regi_product_image_description),
                style = NapzakMarketTheme.typography.bodyMedium14,
                color = NapzakMarketTheme.colors.gray600,
            )
            Spacer(modifier = Modifier.height(16.dp))
            RegistrationPhotoPicker(
                modifier = Modifier,
                imageUrlList = uiState.imageUri,
                onPhotoClick = onPhotoClick,
                onPress = onPhotoPress,
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
                text = stringResource(regi_title),
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
                maxLength = MAX_TITLE_LENGTH,
            )
            Spacer(modifier = Modifier.height(35.dp))
        }
        item {
            Text(
                modifier = paddedModifier,
                text = stringResource(regi_description),
                style = NapzakMarketTheme.typography.bodySemi16,
                color = NapzakMarketTheme.colors.gray900,
            )
            Spacer(modifier = Modifier.height(12.dp))
            RegistrationPlainTextField(
                modifier = paddedModifier,
                text = uiState.description,
                placeholder = stringResource(regi_description_placeholder),
                onTextChange = onDescriptionChange,
                isTitle = false,
                maxLength = MAX_DESCRIPTION_LENGTH,
            )
        }
        if (registrationType == TradeType.SELL) {
            item {
                Spacer(modifier = Modifier.height(35.dp))
                RegistrationSellGroup(
                    modifier = paddedModifier,
                    salePrice = uiState.productSalePrice,
                    salePricePlaceHolder = stringResource(regi_price_placeholder),
                    onSalePriceChange = onSalePriceChange,
                    productCondition = uiState.productCondition,
                    onProductConditionChange = onProductConditionChange,
                    postFeeType = if (uiState.isPostFeeIncluded) PostFeeType.INCLUDED else PostFeeType.EXCLUDED,
                    onPostFeeChange = onPostFeeChange,
                    isNormalPostChecked = uiState.isNormalPostChecked,
                    onNormalPostCheckedChange = { normalCheckState ->
                        onNormalPostStateChange(normalCheckState)
                        if (!uiState.isNormalPostChecked) onNormalPostFeeChange(BLANK)
                    },
                    normalPostFee = uiState.normalPostFee,
                    onNormalPostFeeChange = onNormalPostFeeChange,
                    isHalfPostChecked = uiState.isHalfPostChecked,
                    onHalfPostCheckedChange = { halfCheckState ->
                        onHalfPostStateChange(halfCheckState)
                        if (!uiState.isHalfPostChecked) onHalfPostFeeChange(BLANK)
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
                text = stringResource(register),
                onClick = onRegistrationClick,
                buttonColors = with(NapzakMarketTheme.colors) {
                    ButtonDefaults.buttonColors().copy(
                        containerColor = purple30,
                        contentColor = white,
                        disabledContainerColor = gray400,
                        disabledContentColor = white,
                    )
                },
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
private const val ZERO = 0
private const val BLANK = ""
private const val MAX_TITLE_LENGTH = 48
private const val MAX_DESCRIPTION_LENGTH = 240

@Preview
@Composable
private fun RegistrationScreenPreview() {
    NapzakMarketTheme {
        RegistrationRoute(
            tradeType = "팔아요",
            navigateUp = { },
            onGenreSearchNavigate = { },
        )
    }
}
