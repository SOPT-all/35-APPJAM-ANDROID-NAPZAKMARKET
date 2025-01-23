package com.napzak.market.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Purple
val Purple10 = Color(0xFFF1E8FF)
val Purple20 = Color(0xFFC0A1FF)
val Purple30 = Color(0xFF7534FF)

// Pink
val Pink = Color(0xFFFF195E)

// Gray Scale & Gradient
val White = Color(0xFFFFFFFF)
val White0 = Color(0x00FFFFFF)
val White20 = Color(0x33FFFFFF)
val Gray50 = Color(0xFFFAFAFA)
val Gray100 = Color(0xFFF4F4F4)
val Gray200 = Color(0xFFE9E9E9)
val Gray300 = Color(0xFFD9D9D9)
val Gray400 = Color(0xFFC4C4C4)
val Gray500 = Color(0xFF9D9D9D)
val Gray600 = Color(0xFF7B7B7B)
val Gray700 = Color(0xFF555555)
val Gray800 = Color(0xFF434343)
val Gray900 = Color(0xFF262626)
val Black = Color(0xFF010101)
val Black70 = Color(0xB3000000)

@Immutable
data class NapzakMarketColors(
    // Purple
    val purple10: Color,
    val purple20: Color,
    val purple30: Color,

    // Pink
    val pink: Color,

    // Gray Scale & Gradient
    val white: Color,
    val white0: Color,
    val white20: Color,
    val gray50: Color,
    val gray100: Color,
    val gray200: Color,
    val gray300: Color,
    val gray400: Color,
    val gray500: Color,
    val gray600: Color,
    val gray700: Color,
    val gray800: Color,
    val gray900: Color,
    val black: Color,
    val black70: Color
)

val defaultNapzakMarketColors = NapzakMarketColors(
    // Purple
    purple10 = Purple10,
    purple20 = Purple20,
    purple30 = Purple30,

    // Pink
    pink = Pink,

    // Gray Scale & Gradient
    white = White,
    white0 = White0,
    white20 = White20,
    gray50 = Gray50,
    gray100 = Gray100,
    gray200 = Gray200,
    gray300 = Gray300,
    gray400 = Gray400,
    gray500 = Gray500,
    gray600 = Gray600,
    gray700 = Gray700,
    gray800 = Gray800,
    gray900 = Gray900,
    black = Black,
    black70 = Black70
)

val localNapzakMarketColors = staticCompositionLocalOf { defaultNapzakMarketColors }
