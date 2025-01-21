package com.napzak.market.presentation.marketinfo.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.napzak.market.core.common.navigation.Route
import com.napzak.market.presentation.marketinfo.MarketInfoRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToMarketInfo(
    storeId: Long,
    navOptions: NavOptions? = null,
) = navigate(MarketInfo(storeId), navOptions)

fun NavGraphBuilder.marketInfoGraph(
    onBackButtonClick: () -> Unit,
    onDetailPageNavigate: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    composable<MarketInfo> { backStackEntry ->
        val marketInfo: MarketInfo = backStackEntry.toRoute()

        MarketInfoRoute(
            storeId = marketInfo.storeId,
            onBackButtonClick = onBackButtonClick,
            onDetailPageNavigate = onDetailPageNavigate,
            modifier = modifier,
        )
    }
}

@Serializable
data class MarketInfo(
    val storeId: Long,
) : Route