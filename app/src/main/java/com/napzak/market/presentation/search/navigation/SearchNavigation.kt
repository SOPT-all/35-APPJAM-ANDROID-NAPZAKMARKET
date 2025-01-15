package com.napzak.market.presentation.search.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.napzak.market.core.common.navigation.Route
import com.napzak.market.presentation.search.SearchRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSearch(searchTerm: String? = null, navOptions: NavOptions? = null) =
    navigate(Search(searchTerm), navOptions)

fun NavGraphBuilder.searchGraph(
    onExploreNavigate: (String?, Long?) -> Unit,
    modifier: Modifier = Modifier,
) {
    composable<Search> { backStackEntry ->
        val search: Search = backStackEntry.toRoute()

        SearchRoute(
            initSearchTerm = search.searchTerm,
            onExploreNavigate = onExploreNavigate,
            modifier = modifier,
        )
    }
}

@Serializable
data class Search(val searchTerm: String? = null) : Route