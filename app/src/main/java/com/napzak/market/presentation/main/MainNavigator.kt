package com.napzak.market.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.napzak.market.presentation.explore.navigation.navigateToExplore
import com.napzak.market.presentation.home.navigation.navigateToHome
import com.napzak.market.presentation.main.type.MainTab
import com.napzak.market.presentation.onboarding.navigation.Onboarding

class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination
    private var registerDialogVisibilityState by mutableStateOf(false)

    val registerDialogVisibility: Boolean
        get() = this.registerDialogVisibilityState

    val startDestination = Onboarding

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it){
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.EXPLORE -> navController.navigateToExplore(navOptions)
            MainTab.REGISTER ->  {
                this.registerDialogVisibilityState = !this.registerDialogVisibilityState
            }
            MainTab.CHAT -> {/*TODO: 채팅화면 연결*/}
            MainTab.MY ->  {/*TODO: 마이페이지 화면 연결*/}
        }
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    @Composable
    fun showBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}