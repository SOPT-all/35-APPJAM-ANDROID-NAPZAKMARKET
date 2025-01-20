package com.napzak.market.presentation.chat.chat.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.napzak.market.core.common.navigation.MainTabRoute
import com.napzak.market.presentation.prepare.ChatScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToChat(navOptions: NavOptions? = null) {
    navigate(Chat, navOptions)
}

fun NavGraphBuilder.chatGraph(
    modifier: Modifier = Modifier,
) {
    composable<Chat> {
        ChatScreen(
            modifier = modifier,
        )
    }
}

@Serializable
data object Chat : MainTabRoute
