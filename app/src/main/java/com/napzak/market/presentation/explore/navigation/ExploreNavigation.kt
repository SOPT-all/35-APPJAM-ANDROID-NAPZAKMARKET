package com.napzak.market.presentation.explore.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.napzak.market.core.common.navigation.MainTabRoute
import com.napzak.market.presentation.explore.ExploreRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToExplore(navOptions: NavOptions? = null) = navigate(Explore, navOptions)

fun NavGraphBuilder.exploreGraph(
    modifier: Modifier = Modifier
) {
    composable<Explore> {
        ExploreRoute(
            modifier = modifier,
            onSearchBoxClick = { /* TODO: 검색화면으로 이동 */ },
            navigateToProductDetail = { /* TODO: 상세페이지로 이동 */ },
        )
    }
}

@Serializable
data object Explore : MainTabRoute