package com.napzak.market.presentation.detailpage.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.napzak.market.core.common.navigation.MainTabRoute
import com.napzak.market.presentation.detailpage.DetailPageRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToDetailPage(navOptions: NavOptions? = null) {
    navigate(DetailPage, navOptions)
}

fun NavGraphBuilder.detailPageGraph(
    onChatNavigate: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    composable<DetailPage> {
        DetailPageRoute(
            modifier = modifier,
            onItemChatNavigate = onChatNavigate,
            onNavigateUp = onNavigateUp,
        )
    }
}

@Serializable
data object DetailPage : MainTabRoute
