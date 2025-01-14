package com.napzak.market.presentation.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.presentation.explore.state.ExploreUiState

@Composable
fun ExploreRoute(
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        viewModel.getExploreProductInformation()
    }

    ExploreScreen(
        modifier = modifier,
        uiState = uiState,
    )
}

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    uiState: ExploreUiState
) {
    when (uiState.loadState) {
        is UiState.Loading -> {
            TODO("Loading 화면")
        }

        is UiState.Empty -> {
            TODO("Empty 화면")
        }

        is UiState.Failure -> {
            TODO("Failure 화면")
        }

        is UiState.Success -> {
            with(uiState.loadState.data) {
                ExploreSuccessScreen(
                    modifier = modifier,
                    tradeType = uiState.tradeType,
                    genreList = uiState.genreList,
                    isOnSale = uiState.isOnSale,
                    isUnopened = uiState.isUnopened,
                    productList = uiState.loadState.data.productList
                )
            }
        }
    }
}

@Composable
fun ExploreSuccessScreen(
    modifier: Modifier = Modifier,
    tradeType: String,
    genreList: List<String>,
    isOnSale: Boolean,
    isUnopened: Boolean,
    productList: List<ProductItem>
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

    }
}

@Preview
@Composable
private fun ExploreRoutePreview(modifier: Modifier = Modifier) {
    ExploreRoute(modifier = modifier)
}