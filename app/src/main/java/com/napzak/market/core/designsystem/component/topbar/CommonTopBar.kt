package com.napzak.market.core.designsystem.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.component.noRippleEffect

/**
 * 공통 TopBar 컴포넌트
 *
 * @param title TopBar에 표시될 제목
 * @param textStyle 제목 텍스트 스타일
 * @param backgroundColor TopBar의 배경색
 * @param contentColor 제목 및 아이콘 색상
 * @param navigationIcon 네비게이션 아이콘 이미지
 * @param onNavigationClick 네비게이션 아이콘 클릭 시 수행할 동작
 * @param modifier TopBar에 적용할 Modifier
 */
@Composable
fun CommonTopBar(
    title: String,
    textStyle: TextStyle,
    backgroundColor: Color,
    contentColor: Color,
    navigationIcon: ImageVector? = null,
    onNavigationClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(backgroundColor)
            .padding(horizontal = 13.dp),
        contentAlignment = Alignment.Center
    ) {
        if (navigationIcon != null) {
            Icon(
                imageVector = navigationIcon,
                contentDescription = stringResource(id = R.string.navigation_icon_description),
                tint = contentColor,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .noRippleClickable {
                        onNavigationClick?.invoke() }
            )
        }
        Text(
            text = title,
            style = textStyle,
            color = contentColor,
        )
    }
}