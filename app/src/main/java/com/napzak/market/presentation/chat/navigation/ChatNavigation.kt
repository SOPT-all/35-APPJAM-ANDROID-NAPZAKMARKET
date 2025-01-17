package com.napzak.market.presentation.chat.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.napzak.market.core.common.navigation.MainTabRoute
import com.napzak.market.presentation.chat.screen.ItemChatRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToItemChat(navOptions: NavOptions? = null) {
    navigate(ItemChat, navOptions)
}

fun NavGraphBuilder.itemChatGraph(
    modifier: Modifier = Modifier,
) {
    composable<ItemChat> {
        ItemChatRoute(
            modifier = modifier
        )
    }
}

@Serializable
data object ItemChat : MainTabRoute
