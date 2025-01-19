package com.napzak.market.presentation.registration.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.napzak.market.core.common.navigation.Route
import com.napzak.market.presentation.registration.RegistrationGenreSearchRoute
import com.napzak.market.presentation.registration.RegistrationRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToRegistration(
    navOptions: NavOptions? = null,
    isSale: Boolean,
) = navigate(Registration(isSale), navOptions)

fun NavController.navigateToGenreSearch(
    navOptions: NavOptions? = null,
) = navigate(GenreSearch, navOptions)

fun NavGraphBuilder.registrationGraph(
    navigateUp: () -> Unit,
    navigateToGenreSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    composable<Registration> {
        val tradeType = it.toRoute<Registration>()

        RegistrationRoute(
            isSale = tradeType.isSale,
            navigateUp = navigateUp,
            navigateToGenreSearch = navigateToGenreSearch,
            modifier = modifier,
        )
    }

    composable<GenreSearch> {
        RegistrationGenreSearchRoute(
            onBackClick = navigateUp,
            modifier = modifier,
        )
    }
}

@Serializable
data class Registration(
    val isSale: Boolean,
) : Route

@Serializable
data object GenreSearch : Route
