package com.napzak.market.presentation.main.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.napzak.market.core.common.util.NoRippleInteractionSource
import com.napzak.market.presentation.main.MainTab
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainBottomBar(
    isVisible: Boolean,
    tabs: ImmutableList<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideIn { IntOffset(0, 0) },
        exit = fadeOut() + slideOut { IntOffset(0, 0) }
    ) {
        NavigationBar(containerColor = White) {
            tabs.forEach { itemType ->
                NavigationBarItem(
                    interactionSource = NoRippleInteractionSource,
                    selected = currentTab == itemType,
                    onClick = {
                        onTabSelected(itemType)
                    },
                    icon = {
                        Icon(
                            imageVector = itemType.icon,
                            contentDescription = itemType.contentDescription,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = itemType.contentDescription,
                            fontSize = 9.sp
                        )
                    },
                    colors = NavigationBarItemDefaults
                        .colors(
                            selectedIconColor = Black,
                            selectedTextColor = Black,
                            unselectedIconColor = Gray,
                            unselectedTextColor = Gray,
                            indicatorColor = White
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainBottomBarPreview() {
    MainBottomBar(
        isVisible = true,
        tabs = MainTab.entries.toImmutableList(),
        currentTab = MainTab.DUMMY,
        onTabSelected = {}
    )
}