package com.napzak.market.core.designsystem.component.button.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.napzak.market.core.designsystem.theme.Gray200
import com.napzak.market.core.designsystem.theme.Gray700
import com.napzak.market.core.designsystem.theme.White

/**
 * 버튼의 배경색, 컨텐츠색, 경계색을 정의하는 클래스
 *
 * @param contentColor 내부 컴포저블(텍스트) 색상
 * @param containerColor 배경 색상
 * @param borderColor 칩 경계 색상
 */
@Immutable
open class CustomButtonColors constructor(
    private val contentColor: Color = Gray700,
    private val containerColor: Color = White,
    private val borderColor: Color = Gray200,
) {
    @Stable
    internal fun contentColor() = contentColor

    @Stable
    internal fun containerColor() = containerColor

    @Stable
    internal fun borderColor() = borderColor
}