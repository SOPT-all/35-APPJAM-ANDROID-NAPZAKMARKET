package com.napzak.market.presentation.prepare.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.napzak.market.core.common.navigation.MainTabRoute
import com.napzak.market.presentation.prepare.PrepareScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToPrepare(navOptions: NavOptions? = null) {
    navigate(Prepare, navOptions)
}

fun NavGraphBuilder.prepareGraph(
    modifier: Modifier = Modifier,
) {
    composable<Prepare> {
        PrepareScreen(
            modifier = modifier,
        )
    }
}

@Serializable
data object Prepare : MainTabRoute
