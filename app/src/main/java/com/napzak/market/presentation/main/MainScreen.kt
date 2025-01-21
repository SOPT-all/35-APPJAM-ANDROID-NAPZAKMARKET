package com.napzak.market.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.napzak.market.core.type.TradeType
import com.napzak.market.presentation.chat.chat.navigation.chatGraph
import com.napzak.market.presentation.chat.itemchat.navigation.itemChatGraph
import com.napzak.market.presentation.chat.itemchat.navigation.navigateToItemChat
import com.napzak.market.presentation.detailpage.navigation.detailPageGraph
import com.napzak.market.presentation.dummy.navigation.dummyGraph
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

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    Scaffold(
        bottomBar = {
            MainBottomBar(
                isVisible = navigator.showBottomBar(),
                tabs = MainTab.entries.toImmutableList(),
                currentTab = navigator.currentTab,
                onTabSelected = navigator::navigate,
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            MainNavHost(
                navigator = navigator,
                modifier = Modifier.padding(innerPadding),
            )

            MainRegisterDialog(
                onSellRegisterClick = { navigator.navController.navigateToRegistration(tradeType = TradeType.SELL.label) },
                onBuyRegisterClick = { navigator.navController.navigateToRegistration(tradeType = TradeType.BUY.label) },
                onDismissRequest = { navigator.navigate(MainTab.REGISTER) },
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
        dummyGraph(modifier = modifier)

        splashGraph(
            modifier = Modifier.navigationBarsPadding(),
            onOnboardingNavigate = navigator.navController::navigateToOnboarding,
        )

        onboardingGraph(
            modifier = Modifier.systemBarsPadding(),
            navigateToHome = navigator.navController::navigateToHome,
        )

        homeGraph(modifier = modifier)

        exploreGraph(
            modifier = modifier,
            onBackButtonClick = navigator.navController::popBackStack,
            onSearchNavigate = navigator.navController::navigateToSearch,
        )

        searchGraph(
            modifier = modifier,
            onExploreNavigate = { searchTerm, genreId ->
                navigator.navController.previousBackStackEntry?.savedStateHandle?.set(
                    "searchTerm",
                    searchTerm
                )
                navigator.navController.previousBackStackEntry?.savedStateHandle?.set(
                    "genreId",
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
            onProductDetailNavigate = { /* TODO: 상품상세 화면으로 이동 연결 */ },
        )

        detailPageGraph(
            modifier = modifier,
            onChatNavigate = navigator.navController::navigateToItemChat,
            onNavigateUp = navigator.navController::navigateUp,
        )

        itemChatGraph(
            modifier = Modifier.systemBarsPadding(),
            onNavigateUp = navigator.navController::navigateUp,
        )

        prepareGraph(
            modifier = modifier,
        )

        registrationGraph(
            modifier = modifier,
            navigateUp = navigator.navController::navigateUp,
            onGenreSearchNavigate = navigator.navController::navigateToGenreSearch,
            getBackStackViewModel = {
                navigator.navController.previousBackStackEntry?.let { hiltViewModel(it) }
                    ?: hiltViewModel()
            },
        )
    }
}
