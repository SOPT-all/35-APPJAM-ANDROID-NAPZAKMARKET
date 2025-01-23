package com.napzak.market.presentation.registration.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.napzak.market.core.common.navigation.Route
import com.napzak.market.presentation.registration.RegistrationGenreSearchRoute
import com.napzak.market.presentation.registration.RegistrationRoute
import com.napzak.market.presentation.registration.RegistrationViewModel
import kotlinx.serialization.Serializable

fun NavController.navigateToRegistration(
    navOptions: NavOptions? = null,
    tradeType: String,
) = navigate(Registration(tradeType), navOptions)

fun NavController.navigateToGenreSearch(
    navOptions: NavOptions? = null,
) = navigate(GenreSearch, navOptions)

fun NavGraphBuilder.registrationGraph(
    navigateUp: () -> Unit,
    onGenreSearchNavigate: () -> Unit,
    onDetailNavigate: (Long) -> Unit,
    getBackStackViewModel: @Composable (NavBackStackEntry) -> RegistrationViewModel,
    modifier: Modifier = Modifier,
) {
    composable<Registration> {
        val registration = it.toRoute<Registration>()

        RegistrationRoute(
            tradeType = registration.tradeType,
            navigateUp = navigateUp,
            onGenreSearchNavigate = onGenreSearchNavigate,
            onDetailNavigate = onDetailNavigate,
            modifier = modifier,
        )
    }

    composable<GenreSearch> {
        RegistrationGenreSearchRoute(
            navigateUp = navigateUp,
            modifier = modifier,
            viewModel = getBackStackViewModel(it),
        )
    }
}

@Serializable
data class Registration(
    val tradeType: String,
) : Route

@Serializable
data object GenreSearch : Route
