package com.napzak.market.presentation.main.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.napzak.market.R
import com.napzak.market.core.common.navigation.MainTabRoute
import com.napzak.market.core.common.navigation.Route
import com.napzak.market.presentation.dummy.navigation.Dummy
import com.napzak.market.presentation.explore.navigation.Explore


// TODO: 구현 시작 시 수정
enum class MainTab(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val contentDescription: Int,
    val route: MainTabRoute,
) {
    HOME(
        selectedIcon = R.drawable.ic_home_select_28,
        unselectedIcon = R.drawable.ic_home_unselect_28,
        contentDescription = R.string.main_bottom_bar_home,
        route = Dummy, // TODO: Home 루트로 변경
    ),
    SEARCH(
        selectedIcon = R.drawable.ic_look_select_28,
        unselectedIcon = R.drawable.ic_look_unselect_28,
        contentDescription = R.string.main_bottom_bar_search,
        route = Explore,
    ),
    REGISTER(
        selectedIcon = R.drawable.ic_register_select_28,
        unselectedIcon = R.drawable.ic_register_unselect_28,
        contentDescription = R.string.main_bottom_bar_register,
        route = Dummy, // TODO: Register 루트로 변경
    ),
    CHAT(
        selectedIcon = R.drawable.ic_chat_select_28,
        unselectedIcon = R.drawable.ic_chat_unselect_28,
        contentDescription = R.string.main_bottom_bar_chat,
        route = Dummy, // TODO: Chat 루트로 변경
    ),
    MY(
        selectedIcon = R.drawable.ic_my_select_28,
        unselectedIcon = R.drawable.ic_my_unselect_28,
        contentDescription = R.string.main_bottom_bar_my,
        route = Dummy, // TODO: My 루트로 변경
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