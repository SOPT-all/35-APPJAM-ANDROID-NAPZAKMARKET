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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import com.napzak.market.core.type.TradeType
import com.napzak.market.presentation.registration.component.RegistrationBuyGroup
import com.napzak.market.presentation.registration.component.RegistrationGenreButton
import com.napzak.market.presentation.registration.component.RegistrationPhotoPicker
import com.napzak.market.presentation.registration.component.RegistrationPlainTextField
import com.napzak.market.presentation.registration.component.RegistrationSellGroup
import com.napzak.market.presentation.registration.state.RegistrationUiState
import com.napzak.market.presentation.registration.type.NumeralInputType
import com.napzak.market.presentation.registration.type.PlainTextInputType

@Composable
fun RegistrationRoute(
    isSale: Boolean,
    navigateUp: () -> Unit,
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
            /* TODO: 최대 개수 초과 시 로직 */
        }
    }
    val getPhotoPickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia(maxItems = currentListSize)
    ) { uris: List<Uri> ->
        val updatedListSize = uiState.imageUrlList.size + uris.size
        if (updatedListSize <= 10) {
            viewModel.updatePhotoList(uris.map { it.toString() })
        } else {
            /* TODO: 최대 개수 초과 시 로직 */
            val remainingUris = uris.takeLast(MAX_ITEMS - uiState.imageUrlList.size)
            viewModel.updatePhotoList(remainingUris.map { it.toString() })
        }
    }

    RegistrationScreen(
        uiState = uiState,
        registrationType = if (isSale) TradeType.SELL else TradeType.BUY,
        onCloseClick = navigateUp,
        onPhotoClick = {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                if (MAX_ITEMS - uiState.imageUrlList.size > 0) getImageStorageLauncher.launch(INPUT_TYPE)
                else {
                    /* TODO: 최대 개수 초과 시 다이얼로그 */
                }
            }
            else {
                getPhotoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        },
        onPhotoLongPress = viewModel::changeRepresentPhoto,
        onDeleteClick = viewModel::deletePhoto,
        onGenreClick = { },
        onTitleChange = { viewModel.updatePlainTextValue(it, PlainTextInputType.Title) },
        onDescriptionChange = { viewModel.updatePlainTextValue(it, PlainTextInputType.Description) },
        onSalePriceChange = { viewModel.updateNumericValue(it, NumeralInputType.ProductSalePrice) },
        onProductConditionChange = viewModel::updateProductCondition,
        onCheckChange = viewModel::updateOfferAvailability,
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
    onSalePriceChange: (String) -> Unit = {},
    onProductConditionChange: (Int) -> Unit,
    onCheckChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val paddedModifier = Modifier.padding(horizontal = 20.dp)
    val listState = rememberLazyListState()
    var postFeeState by remember { mutableStateOf(0) }
    var isNormalPostChecked by remember { mutableStateOf(false) }
    var isHalfPostChecked by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .imePadding()
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
                color = NapzakMarketTheme.colors.gray900
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = paddedModifier,
                text = stringResource(R.string.regi_product_image_description),
                style = NapzakMarketTheme.typography.bodyMedium14,
                color = NapzakMarketTheme.colors.gray600
            )
            Spacer(modifier = Modifier.height(16.dp))
            RegistrationPhotoPicker(
                modifier = Modifier,
                imageUrlList = uiState.imageUrlList,
                onPhotoClick = onPhotoClick,
                onLongPress = onPhotoLongPress,
                onDeleteClick = onDeleteClick
            )
        }
        item {
            Spacer(modifier = Modifier.height(40.dp))
            RegistrationGenreButton(
                modifier = paddedModifier,
                onGenreClick = onGenreClick
            )
        }
        item {
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                modifier = paddedModifier,
                text = stringResource(R.string.regi_title),
                style = NapzakMarketTheme.typography.bodySemi16,
                color = NapzakMarketTheme.colors.gray900
            )
            Spacer(modifier = Modifier.height(12.dp))
            RegistrationPlainTextField(
                modifier = paddedModifier,
                text = uiState.title,
                placeholder = stringResource(regi_title_placeholder),
                onTextChange = onTitleChange,
                isTitle = true,
                maxLength = 48
            )
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                modifier = paddedModifier,
                text = stringResource(R.string.regi_description),
                style = NapzakMarketTheme.typography.bodySemi16,
                color = NapzakMarketTheme.colors.gray900
            )
            Spacer(modifier = Modifier.height(12.dp))
            RegistrationPlainTextField(
                modifier = paddedModifier,
                text = uiState.description,
                placeholder = stringResource(R.string.regi_description_placeholder),
                onTextChange = onDescriptionChange,
                isTitle = false,
                maxLength = 240
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
                    postFeeState = postFeeState,
                    onPostFeeChange = { postFeeState = it },
                    isNormalPostChecked = isNormalPostChecked,
                    onNormalPostCheckedChange = { isNormalPostChecked = it },
                    isHalfPostChecked = isHalfPostChecked,
                    onHalfPostCheckedChange = { isHalfPostChecked = it },
                )
            }
        }
        if (registrationType == TradeType.BUY) {
            item {
                Spacer(modifier = Modifier.height(35.dp))
                RegistrationBuyGroup(
                    modifier = paddedModifier,
                    number = uiState.productPurchasePrice,
                    onNumberChange = { },
                    isOfferAvailable = uiState.isOfferAvailable,
                    onCheckChange = onCheckChange,
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
                isEnabled = true,
            )
        }
    }
}

private const val INPUT_TYPE = "image/*"
private const val MAX_ITEMS = 10
private const val MIN_ITEMS = 2

@Preview
@Composable
private fun RegistrationScreenPreview() {
    NapzakMarketTheme {
        RegistrationRoute(
            isSale = false,
            navigateUp = { },
        )
    }
}
