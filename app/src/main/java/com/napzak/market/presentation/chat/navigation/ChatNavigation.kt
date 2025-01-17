package com.napzak.market.presentation.chat.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.napzak.market.core.common.navigation.MainTabRoute
import com.napzak.market.presentation.chat.screen.ItemChatRoute
import com.napzak.market.presentation.chat.screen.ItemChatScreen
import com.napzak.market.presentation.chat.type.ChatType
import kotlinx.serialization.Serializable

fun NavController.navigateToItemChatScreen(navOptions: NavOptions? = null) {
    navigate(ChatScreen, navOptions)
}

fun NavGraphBuilder.itemChatGraph(
    modifier: Modifier = Modifier,
) {
    composable<ChatScreen> {
        ItemChatRoute(
            modifier = modifier
        )
    }
}

@Serializable
data object ChatScreen : MainTabRoute
