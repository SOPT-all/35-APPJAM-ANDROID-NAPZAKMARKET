package com.napzak.market.presentation.chat.itemchat.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.napzak.market.core.common.navigation.Route
import com.napzak.market.presentation.chat.itemchat.ItemChatRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToItemChat(navOptions: NavOptions? = null) {
    navigate(ItemChat, navOptions)
}

fun NavGraphBuilder.itemChatGraph(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    composable<ItemChat> {
        ItemChatRoute(
            modifier = modifier,
            onNavigateUp = onNavigateUp,
        )
    }
}

@Serializable
data object ItemChat : Route
