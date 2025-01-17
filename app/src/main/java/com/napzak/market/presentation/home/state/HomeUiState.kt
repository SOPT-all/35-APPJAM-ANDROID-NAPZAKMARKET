package com.napzak.market.presentation.home.state

import com.napzak.market.R
import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.explore.model.ProductItem
import com.napzak.market.presentation.explore.type.TradeType

data class HomeUiState(
    val bannerImages: UiState<List<Int>> = UiState.Loading,
    val recommendedItems: UiState<List<ProductItem>> = UiState.Loading,
    val popularItems: UiState<List<ProductItem>> = UiState.Loading,
    val searchItems: UiState<List<ProductItem>> = UiState.Loading,
) {
    companion object {
        val dummyBanner = UiState.Success(
            listOf(
                R.drawable.img_banner_home1,
                R.drawable.img_banner_home2,
            )
        )
        val dummyData = UiState.Success(
            listOf(
                ProductItem(
                    productId = 1,
                    productName = "딸기 마이멜로디 마스코트 인형",
                    genreName = "산리오",
                    price = 35000,
                    uploadTime = "1시간전",
                    photo = "",
                    isLiked = false,
                    tradeType = TradeType.SELL.name,
                    tradeStatus = "판매중",
                    isPriceNegotiable = false,
                ),
                ProductItem(
                    productId = 2,
                    productName = "딸기 마이멜로디 마스코트 인형",
                    genreName = "산리오",
                    price = 35000,
                    uploadTime = "1시간전",
                    photo = "",
                    isLiked = false,
                    tradeType = TradeType.SELL.name,
                    tradeStatus = "판매중",
                    isPriceNegotiable = false,
                ),
                ProductItem(
                    productId = 3,
                    productName = "딸기 마이멜로디 마스코트 인형",
                    genreName = "산리오",
                    price = 35000,
                    uploadTime = "1시간전",
                    photo = "",
                    isLiked = false,
                    tradeType = TradeType.SELL.name,
                    tradeStatus = "판매중",
                    isPriceNegotiable = false,
                ),
                ProductItem(
                    productId = 4,
                    productName = "딸기 마이멜로디 마스코트 인형",
                    genreName = "산리오",
                    price = 35000,
                    uploadTime = "1시간전",
                    photo = "",
                    isLiked = false,
                    tradeType = TradeType.SELL.name,
                    tradeStatus = "판매중",
                    isPriceNegotiable = false,
                ),
            )
        )
    }
}
