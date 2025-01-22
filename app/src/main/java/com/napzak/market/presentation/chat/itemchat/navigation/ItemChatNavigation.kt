package com.napzak.market.presentation.chat.itemchat.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.napzak.market.core.common.navigation.Route
import com.napzak.market.presentation.chat.itemchat.ItemChatRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToItemChat(productId: Long, navOptions: NavOptions? = null) {
    navigate(ItemChat(productId), navOptions)
}

fun NavGraphBuilder.itemChatGraph(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    composable<ItemChat> { backStackEntry ->
        val productId = backStackEntry.arguments?.getLong("productId") ?: 0L
        ItemChatRoute(
            productId = productId,
            modifier = modifier,
            onNavigateUp = onNavigateUp,
        )
    }
}

@Serializable
data class ItemChat(
    val productId: Long,
) : Route
