package com.napzak.market.presentation.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.napzak.market.core.common.navigation.MainTabRoute
import com.napzak.market.presentation.home.HomeRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToHome(navOptions: NavOptions? = null) = navigate(Home, navOptions)

fun NavGraphBuilder.homeGraph(
    onDetailPageNavigate: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    composable<Home> {
        HomeRoute(
            onDetailPageNavigate = onDetailPageNavigate,
            modifier = modifier,
        )
    }
}

@Serializable
data object Home : MainTabRoute