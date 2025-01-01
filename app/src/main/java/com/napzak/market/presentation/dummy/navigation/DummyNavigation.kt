package com.napzak.market.presentation.dummy.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.napzak.market.core.common.navigation.MainTabRoute
import com.napzak.market.presentation.dummy.DummyRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToDummy(navOptions: NavOptions? = null) = navigate(Dummy, navOptions)

fun NavGraphBuilder.dummyGraph(
    modifier: Modifier = Modifier
) {
    composable<Dummy> {
        DummyRoute(
            modifier = modifier
        )
    }
}

@Serializable
data object Dummy : MainTabRoute