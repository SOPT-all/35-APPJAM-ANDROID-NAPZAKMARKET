package com.napzak.market.presentation.main.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.presentation.main.MainTab
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

/**
 * 메인화면에서 노출되는 바텀바입니다.
 *
 * @param isVisible 바텀바 표시 여부, 메인화면에 포함되지 않는 화면의 경우 바텀바를 표시하지 않습니다.
 * @param tabs 바텀바에 표시되는 탭 목록입니다.
 * @param currentTab 현재 선택된 탭입니다.
 * @param onTabSelected 탭을 선택했을 때 호출되는 콜백입니다.
 */

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

        Surface(
            color = White,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
                    .defaultMinSize(minHeight = 72.dp)  //피그마상 바텀바 높이로 고정
                    .selectableGroup(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                tabs.forEach { tab ->

                    MainNavigationBarItem(
                        selected = tab == currentTab,
                        tab = tab,
                        onClick = { onTabSelected(tab) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 8.dp, bottom = 16.dp),
                    )
                }
            }
        }
    }
}


/**
 * 바텀바의 각 아이템 컴포저블입니다.
 *
 * @param selected 현재 선택된 탭인지 여부입니다.
 * @param tab 탭 정보입니다.
 * @param onClick 탭을 선택했을 때 호출되는 콜백입니다.
 * @param modifier 컴포저블의 수정자입니다.
 */
@Composable
private fun MainNavigationBarItem(
    selected: Boolean,
    tab: MainTab,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val iconRes =
        if (selected) tab.selectedIcon
        else tab.unselectedIcon

    val textStyle =
        if (selected) NapzakMarketTheme.typography.capBold12
        else NapzakMarketTheme.typography.capMedium12

    val textColor =
        if (selected) NapzakMarketTheme.colors.gray900
        else NapzakMarketTheme.colors.gray500

    Column(
        modifier = modifier.noRippleClickable(onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = stringResource(tab.contentDescription),
            tint = Color.Unspecified,
            modifier = Modifier.defaultMinSize(minHeight = 29.dp)   //높이가 가장 큰 아이콘을 기준으로 최소 높이 세팅
        )
        Text(
            text = stringResource(tab.contentDescription),
            style = textStyle,
            color = textColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainBottomBarPreview() {
    NapzakMarketTheme {
        var currentTab by remember { mutableStateOf(MainTab.HOME) }

        MainBottomBar(
            isVisible = true,
            tabs = MainTab.entries.toImmutableList(),
            currentTab = currentTab,
            onTabSelected = { currentTab = it }
        )
    }
}