package com.napzak.market.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.napzak.market.core.common.navigation.MainTabRoute
import com.napzak.market.core.common.navigation.Route
import com.napzak.market.presentation.dummy.navigation.Dummy


// TODO: 구현 시작 시 수정
enum class MainTab(
    val icon: ImageVector,
    val contentDescription: String,
    val route: MainTabRoute,
) {
    DUMMY(
        icon = Icons.Filled.Home,
        contentDescription = "더미1",
        route = Dummy,
    ),
    DUMMY2(
        icon = Icons.Filled.Search,
        contentDescription = "더미2",
        route = Dummy,
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
