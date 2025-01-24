package com.napzak.market.presentation.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.napzak.market.R.string.home_snack_bar_finish
import com.napzak.market.core.designsystem.component.snackbar.CommonSnackBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.TradeType
import com.napzak.market.presentation.chat.chat.navigation.chatGraph
import com.napzak.market.presentation.chat.itemchat.navigation.itemChatGraph
import com.napzak.market.presentation.chat.itemchat.navigation.navigateToItemChat
import com.napzak.market.presentation.detailpage.navigation.detailPageGraph
import com.napzak.market.presentation.detailpage.navigation.navigateToDetailPage
import com.napzak.market.presentation.explore.explore.navigation.GENRE_ID
import com.napzak.market.presentation.explore.explore.navigation.SEARCH_TERM
import com.napzak.market.presentation.explore.explore.navigation.exploreGraph
import com.napzak.market.presentation.explore.search.navigation.navigateToSearch
import com.napzak.market.presentation.explore.search.navigation.searchGraph
import com.napzak.market.presentation.home.navigation.homeGraph
import com.napzak.market.presentation.home.navigation.navigateToHome
import com.napzak.market.presentation.main.component.MainBottomBar
import com.napzak.market.presentation.main.component.MainRegisterDialog
import com.napzak.market.presentation.main.type.MainTab
import com.napzak.market.presentation.marketinfo.navigation.marketInfoGraph
import com.napzak.market.presentation.marketinfo.navigation.navigateToMarketInfo
import com.napzak.market.presentation.mypage.navigation.myPageGraph
import com.napzak.market.presentation.onboarding.navigation.navigateToOnboarding
import com.napzak.market.presentation.onboarding.navigation.onboardingGraph
import com.napzak.market.presentation.prepare.navigation.navigateToPrepare
import com.napzak.market.presentation.prepare.navigation.prepareGraph
import com.napzak.market.presentation.registration.navigation.navigateToGenreSearch
import com.napzak.market.presentation.registration.navigation.navigateToRegistration
import com.napzak.market.presentation.registration.navigation.registrationGraph
import com.napzak.market.presentation.splash.navigation.splashGraph
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    val context = LocalContext.current
    var backPressedState by remember { mutableStateOf(true) }
    var backPressedTime by remember { mutableLongStateOf(0L) }

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    BackHandler(enabled = backPressedState && navigator.currentTab == MainTab.HOME) {
        if (System.currentTimeMillis() - backPressedTime <= 2000) {
            (context as Activity).finish()
        } else {
            backPressedState = true
            coroutineScope.launch {
                snackBarHostState.showSnackbar(
                    message = context.getString(home_snack_bar_finish),
                    duration = SnackbarDuration.Short
                )
            }
        }
        backPressedTime = System.currentTimeMillis()
    }

    Scaffold(
        bottomBar = {
            MainBottomBar(
                isVisible = navigator.showBottomBar(),
                tabs = MainTab.entries.toImmutableList(),
                currentTab = navigator.currentTab,
                onTabSelected = navigator::navigate,
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier
                    .padding(bottom = 6.dp),
            ) { snackBarData ->
                CommonSnackBar(
                    message = snackBarData.visuals.message,
                    backgroundColor = NapzakMarketTheme.colors.black70,
                    textColor = NapzakMarketTheme.colors.white,
                    textStyle = NapzakMarketTheme.typography.bodyMedium14,
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            MainNavHost(
                navigator = navigator,
                modifier = Modifier.padding(innerPadding),
            )

            MainRegisterDialog(
                onSellRegisterClick = {
                    navigator.navController.navigateToRegistration(tradeType = TradeType.SELL.label)
                    navigator.navigate(MainTab.REGISTER)
                },
                onBuyRegisterClick = {
                    navigator.navController.navigateToRegistration(tradeType = TradeType.BUY.label)
                    navigator.navigate(MainTab.REGISTER)
                },
                onDismissRequest = {
                    navigator.navigate(MainTab.REGISTER)
                },
                visibility = navigator.registerDialogVisibility,
            )
        }
    }
}

@Composable
private fun MainNavHost(
    navigator: MainNavigator,
    modifier: Modifier = Modifier,
) {
    NavHost(
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        },
        navController = navigator.navController,
        startDestination = navigator.startDestination,
    ) {

        splashGraph(
            modifier = Modifier.navigationBarsPadding(),
            onOnboardingNavigate = navigator.navController::navigateToOnboarding,
        )

        onboardingGraph(
            modifier = Modifier.systemBarsPadding(),
            navigateToHome = navigator.navController::navigateToHome,
        )

        homeGraph(
            modifier = modifier,
            onDetailPageNavigate = navigator.navController::navigateToDetailPage,
        )

        exploreGraph(
            modifier = modifier,
            onBackButtonClick = navigator.navController::popBackStack,
            onSearchNavigate = navigator.navController::navigateToSearch,
            onDetailPageNavigate = navigator.navController::navigateToDetailPage,
        )

        searchGraph(
            modifier = modifier,
            onExploreNavigate = { searchTerm, genreId ->
                navigator.navController.previousBackStackEntry?.savedStateHandle?.set(
                    SEARCH_TERM,
                    searchTerm
                )
                navigator.navController.previousBackStackEntry?.savedStateHandle?.set(
                    GENRE_ID,
                    genreId
                )
                navigator.navController.popBackStack()
            }
        )

        chatGraph(
            modifier = modifier,
        )

        myPageGraph(
            modifier = modifier,
            onMyMarketNavigate = navigator.navController::navigateToMarketInfo,
            onHistoryNavigate = navigator.navController::navigateToPrepare,
            onGenreNavigate = navigator.navController::navigateToPrepare,
            onFavoriteNavigate = navigator.navController::navigateToPrepare,
            onRecentNavigate = navigator.navController::navigateToPrepare,
        )

        marketInfoGraph(
            modifier = modifier,
            onBackButtonClick = navigator.navController::popBackStack,
            onDetailPageNavigate = navigator.navController::navigateToDetailPage,
        )

        detailPageGraph(
            onChatNavigate = navigator.navController::navigateToItemChat,
            onNavigateUp = navigator.navController::navigateUp,
            onMarketInfoNavigate = navigator.navController::navigateToMarketInfo,
        )

        itemChatGraph(
            modifier = Modifier.systemBarsPadding(),
            onNavigateUp = navigator.navController::navigateUp,
        )

        prepareGraph(
            modifier = modifier,
            navController = navigator.navController,
        )

        registrationGraph(
            modifier = Modifier.systemBarsPadding(),
            navigateUp = navigator.navController::navigateUp,
            onGenreSearchNavigate = navigator.navController::navigateToGenreSearch,
            onDetailNavigate = { productId ->
                navigator.navController.navigateToDetailPage(
                    productId = productId,
                    navOptions = navOptions {
                        popUpTo(navigator.navController.currentDestination?.id ?: 0) {
                            inclusive = true
                        }
                    },
                )
            },
            getBackStackViewModel = {
                navigator.navController.previousBackStackEntry?.let { hiltViewModel(it) }
                    ?: hiltViewModel()
            },
        )
    }
}
