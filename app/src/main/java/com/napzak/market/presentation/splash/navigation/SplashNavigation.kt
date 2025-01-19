package com.napzak.market.presentation.splash.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.napzak.market.core.common.navigation.Route
import com.napzak.market.presentation.splash.SplashRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSplash(navOptions: NavOptions? = null) = navigate(Splash, navOptions)

fun NavGraphBuilder.splashGraph(
    onOnboardingNavigate: (navOptions: NavOptions) -> Unit,
    modifier: Modifier = Modifier,
) {
    val navOptions = navOptions {
        popUpTo(Splash) {
            inclusive = true
        }
    }

    composable<Splash> {
        SplashRoute(
            onOnboardingNavigate = { onOnboardingNavigate(navOptions) },
            modifier = modifier,
        )
    }
}

@Serializable
data object Splash : Route