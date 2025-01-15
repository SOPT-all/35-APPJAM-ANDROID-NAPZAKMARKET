package com.napzak.market.presentation.onboarding.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.napzak.market.core.common.navigation.Route
import com.napzak.market.presentation.onboarding.OnboardingRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToOnboarding(navOptions: NavOptions? = null) = navigate(Onboarding, navOptions)

fun NavGraphBuilder.onboardingGraph(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    composable<Onboarding> {
        OnboardingRoute(
            navigateToHome = navigateToHome,
            modifier = modifier,
        )
    }
}

@Serializable
data object Onboarding : Route