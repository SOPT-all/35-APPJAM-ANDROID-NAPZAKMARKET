package com.napzak.market.presentation.mypage.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.napzak.market.core.common.navigation.MainTabRoute
import com.napzak.market.presentation.mypage.MyPageScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToMyPage(navOptions: NavOptions? = null) {
    navigate(MyPage, navOptions)
}

fun NavGraphBuilder.myPageGraph(
    modifier: Modifier = Modifier,
    onMyMarketNavigate: () -> Unit,
    onHistoryNavigate: () -> Unit,
    onGenreNavigate: () -> Unit,
    onFavoriteNavigate: () -> Unit,
    onRecentNavigate: () -> Unit
) {
    composable<MyPage> {
        MyPageScreen(
            modifier = modifier,
            onMyMarketNavigate = onMyMarketNavigate,
            onHistoryNavigate = onHistoryNavigate,
            onGenreNavigate = onGenreNavigate,
            onFavoriteNavigate = onFavoriteNavigate,
            onRecentNavigate = onRecentNavigate
        )
    }
}

@Serializable
data object MyPage : MainTabRoute
