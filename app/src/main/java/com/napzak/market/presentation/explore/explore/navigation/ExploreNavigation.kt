package com.napzak.market.presentation.explore.explore.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.napzak.market.core.common.navigation.MainTabRoute
import com.napzak.market.presentation.explore.explore.ExploreRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToExplore(
    searchTerm: String?,
    genreId: Long?,
    navOptions: NavOptions? = null,
) = navigate(Explore(searchTerm, genreId), navOptions)

fun NavGraphBuilder.exploreGraph(
    onSearchNavigate: (String?) -> Unit,
    modifier: Modifier = Modifier,
) {
    composable<Explore> { backStackEntry ->
        val explore: Explore = backStackEntry.toRoute()

        ExploreRoute(
            searchTerm = explore.searchTerm,
            genreId = explore.genreId,
            modifier = modifier,
            onSearchNavigate = onSearchNavigate,
            onProductDetailNavigate = { /* TODO: 상세페이지로 이동 */ },
        )
    }
}

@Serializable
data class Explore(
    val searchTerm: String? = null,
    val genreId: Long? = null,
) : MainTabRoute