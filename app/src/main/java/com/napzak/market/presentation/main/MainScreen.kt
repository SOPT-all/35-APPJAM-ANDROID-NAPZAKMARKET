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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.napzak.market.core.designsystem.component.snackbar.CommonSnackBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.presentation.chat.navigation.itemChatGraph
import com.napzak.market.presentation.chat.navigation.navigateToItemChat
import com.napzak.market.presentation.detailpage.navigation.detailPageGraph
import com.napzak.market.presentation.dummy.navigation.dummyGraph
import com.napzak.market.presentation.explore.explore.navigation.exploreGraph
import com.napzak.market.presentation.explore.explore.navigation.navigateToExplore
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
import com.napzak.market.presentation.splash.navigation.splashGraph
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    val context = LocalContext.current
    var backPressedState by remember { mutableStateOf(true) }
    var backPressedTime = 0L

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    BackHandler(enabled = backPressedState) {
        if (System.currentTimeMillis() - backPressedTime <= 2000) {
            (context as Activity).finish()
        } else {
            backPressedState = true
            coroutineScope.launch {
                snackBarHostState.showSnackbar(
                    message = "버튼을 한 번 더 누르면 종료돼요",
                    duration = SnackbarDuration.Short
                )
            }
        }
        backPressedTime = System.currentTimeMillis()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier.padding(bottom = 10.dp)
            ) { snackBarData ->
                CommonSnackBar(
                    message = snackBarData.visuals.message,
                    backgroundColor = NapzakMarketTheme.colors.black70,
                    textColor = NapzakMarketTheme.colors.white,
                    textStyle = NapzakMarketTheme.typography.bodyMedium14,
                )
            }
        },
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
                onSellRegisterClick = {/*TODO: 판매 등록 화면 연결*/ },
                onBuyRegisterClick = {/*TODO: 구매 등록 화면 연결*/ },
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
        exploreGraph(
            modifier = modifier,
            onSearchNavigate = navigator.navController::navigateToSearch,
        )
        onboardingGraph(
            modifier = Modifier.systemBarsPadding(),
            navigateToHome = navigator.navController::navigateToHome,
        )
        searchGraph(
            modifier = modifier,
            onExploreNavigate = navigator.navController::navigateToExplore,
        )
        homeGraph(modifier = modifier)
        myPageGraph(
            modifier = modifier,
            onMyMarketNavigate = navigator.navController::navigateToMarketInfo,
            onHistoryNavigate = {},
            onGenreNavigate = {},
            onFavoriteNavigate = {},
            onRecentNavigate = {}
        )
        marketInfoGraph(
            modifier = modifier,
            onBackButtonClick = { navigator.navController.popBackStack() },
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
    }
}
