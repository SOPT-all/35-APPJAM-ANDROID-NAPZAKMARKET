package com.napzak.market.presentation.registration.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
import timber.log.Timber

fun NavController.navigateToRegistration(
    navOptions: NavOptions? = null,
    tradeType: String,
) = navigate(Registration(tradeType), navOptions)

fun NavController.navigateToGenreSearch(
    navOptions: NavOptions? = null,
) = navigate(GenreSearch, navOptions)

fun NavGraphBuilder.registrationGraph(
    navigateUp: () -> Unit,
    navigateToGenreSearch: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    composable<Registration> {
        val registration = it.toRoute<Registration>()

        RegistrationRoute(
            tradeType = registration.tradeType,
            navigateUp = navigateUp,
            navigateToGenreSearch = navigateToGenreSearch,
            modifier = modifier,
        )
    }

    composable<GenreSearch> {
        RegistrationGenreSearchRoute(
            onBackClick = navigateUp,
            modifier = modifier,
            viewModel = navController.previousBackStackEntry?.let { hiltViewModel(it) } ?: hiltViewModel(),
        )
    }
}

@Serializable
data class Registration(
    val tradeType: String,
) : Route

@Serializable
data object GenreSearch : Route
