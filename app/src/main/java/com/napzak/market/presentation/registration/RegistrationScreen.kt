package com.napzak.market.presentation.registration

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.napzak.market.R
import com.napzak.market.R.string.regi_title_placeholder
import com.napzak.market.R.string.regi_topbar_title
import com.napzak.market.core.designsystem.component.topbar.CloseTopBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.TradeType
import com.napzak.market.presentation.registration.component.RegistrationBuyGroup
import com.napzak.market.presentation.registration.component.RegistrationGenreButton
import com.napzak.market.presentation.registration.component.RegistrationPhotoPicker
import com.napzak.market.presentation.registration.component.RegistrationPlainTextField
import com.napzak.market.presentation.registration.component.RegistrationSellGroup
import com.napzak.market.presentation.registration.state.RegistrationUiState
import com.napzak.market.presentation.registration.type.PlainTextInputType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationRoute(
    isSale: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RegistrationScreen(
        uiState = uiState,
        registrationType = if (isSale) TradeType.SELL else TradeType.BUY,
        onCloseClick = navigateUp,
//        onPhotoClick = viewModel::photoUpload,
//        onPhotoLongPress = viewModel::representPhotoChange,
//        onDeleteClick = viewModel::deletePhoto,
        onGenreClick = { },
        onTitleChange = {
            viewModel.updatePlainTextValue(it, PlainTextInputType.Title) },
        onDescriptionChange = { viewModel.updatePlainTextValue(it, PlainTextInputType.Description) },
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegistrationScreen(
    uiState: RegistrationUiState,
    registrationType: TradeType,
    onCloseClick: () -> Unit = {},
    onPhotoClick: () -> Unit = {},
    onPhotoLongPress: (Int) -> Unit = {},
    onDeleteClick: (Int) -> Unit = {},
    onGenreClick: () -> Unit,
    onTitleChange: (String) -> Unit = {},
    onDescriptionChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val paddedModifier = Modifier.padding(horizontal = 20.dp)

    LazyColumn(
        modifier = modifier
            .imePadding()
            .background(NapzakMarketTheme.colors.white),
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
                modifier = paddedModifier,
                imageUrlList = uiState.imageUrlList,
                onPhotoClick = onPhotoClick,
                onLongPressed = onPhotoLongPress,
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
                    normalPrice = "",
                    normalPricePlaceHolder = "{ TODO() }",
                    onNormalPriceChange = {  }
                )
                RegistrationSellGroup(
                    modifier = paddedModifier,
                    normalPrice = "",
                    normalPricePlaceHolder = "{ TODO() }",
                    onNormalPriceChange = {  }
                )
                RegistrationSellGroup(
                    modifier = paddedModifier,
                    normalPrice = "",
                    normalPricePlaceHolder = "{ TODO() }",
                    onNormalPriceChange = {  }
                )
                RegistrationSellGroup(
                    modifier = paddedModifier,
                    normalPrice = "",
                    normalPricePlaceHolder = "{ TODO() }",
                    onNormalPriceChange = {  }
                )
            }
        }

        if (registrationType == TradeType.BUY) {
            item {
                Spacer(modifier = Modifier.height(35.dp))
                RegistrationBuyGroup(
                    modifier = paddedModifier
                )
            }
        }
    }
}

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
