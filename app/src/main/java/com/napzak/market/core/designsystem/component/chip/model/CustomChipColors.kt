package com.napzak.market.core.designsystem.component.chip.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.napzak.market.core.designsystem.theme.Gray200
import com.napzak.market.core.designsystem.theme.Gray500
import com.napzak.market.core.designsystem.theme.Purple30
import com.napzak.market.core.designsystem.theme.White

/**
 * Chip의 배경색, 컨텐츠색, 경계색을 정의하는 클래스
 *
 * @param contentColor 내부 컴포저블(텍스트) 색상
 * @param containerColor 배경 색상
 * @param borderColor 칩 경계 색상
 */
@Immutable
class CustomChipColors constructor(
    private val contentColor: Color = Purple30,
    private val containerColor: Color = White,
    private val borderColor: Color = Gray200,
    private val trailingIconColor: Color = Gray500,
    private val leadingIconColor: Color = Gray500
) {
    @Stable
    internal fun contentColor() = contentColor

    @Stable
    internal fun containerColor() = containerColor

    @Stable
    internal fun borderColor() = borderColor

    @Stable
    internal fun trailingIconColor() = trailingIconColor

    @Stable
    internal fun leadingIconColor() = leadingIconColor
}