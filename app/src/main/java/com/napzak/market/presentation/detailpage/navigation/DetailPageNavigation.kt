package com.napzak.market.presentation.detailpage.navigation

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.napzak.market.core.common.navigation.MainTabRoute
import com.napzak.market.presentation.detailpage.DetailPageRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToDetailPage(productId: Long = 0, navOptions: NavOptions? = null) {
    navigate(DetailPage(productId), navOptions)
}

fun NavGraphBuilder.detailPageGraph(
    onChatNavigate: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    composable<DetailPage> {
        DetailPageRoute(
            modifier = Modifier.systemBarsPadding(),
            onItemChatNavigate = onChatNavigate,
            onNavigateUp = onNavigateUp,
        )
    }
}

@Serializable
data class DetailPage(
    val productId: Long, // TODO: 기본값 추후에 지우기
) : MainTabRoute
