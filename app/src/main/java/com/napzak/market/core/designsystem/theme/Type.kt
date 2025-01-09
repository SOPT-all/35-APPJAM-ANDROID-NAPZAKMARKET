package com.napzak.market.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.napzak.market.R

val PretendardBold = FontFamily(Font(R.font.pretendard_bold))
val PretendardSemiBold = FontFamily(Font(R.font.pretendard_semibold))
val PretendardMedium = FontFamily(Font(R.font.pretendard_medium))

@Immutable
data class NapzakMarketTypography(
    // Title
    val titleBold22: TextStyle,
    val titleBold20: TextStyle,
    val titleSemi20: TextStyle,
    val titleBold18: TextStyle,
    val titleSemi18: TextStyle,
    val titleMedium18: TextStyle,

    // Body
    val bodyBold16: TextStyle,
    val bodySemi16: TextStyle,
    val bodyMedium16: TextStyle,
    val bodyBold14: TextStyle,
    val bodySemi14: TextStyle,
    val bodyMedium14: TextStyle,

    // Caption
    val capBold12: TextStyle,
    val capSemi12: TextStyle,
    val capMedium12: TextStyle
)

val defaultLineHeightStyle = LineHeightStyle(
    trim = LineHeightStyle.Trim.None,
    alignment = LineHeightStyle.Alignment.Center
)

val defaultNapzakMarketTypography = NapzakMarketTypography(
    // Title
    titleBold22 = TextStyle(
        fontFamily = PretendardBold,
        fontSize = 22.sp,
        lineHeight = 30.8.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.44).sp
    ),
    titleBold20 = TextStyle(
        fontFamily = PretendardBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.4).sp
    ),
    titleSemi20 = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.4).sp
    ),
    titleBold18 = TextStyle(
        fontFamily = PretendardBold,
        fontSize = 18.sp,
        lineHeight = 25.2.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.36).sp
    ),
    titleSemi18 = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 18.sp,
        lineHeight = 25.2.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.36).sp
    ),
    titleMedium18 = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 18.sp,
        lineHeight = 25.2.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.36).sp
    ),

    // Body
    bodyBold16 = TextStyle(
        fontFamily = PretendardBold,
        fontSize = 16.sp,
        lineHeight = 22.4.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.32).sp
    ),
    bodySemi16 = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 16.sp,
        lineHeight = 22.4.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.32).sp
    ),
    bodyMedium16 = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 16.sp,
        lineHeight = 22.4.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.32).sp
    ),
    bodyBold14 = TextStyle(
        fontFamily = PretendardBold,
        fontSize = 14.sp,
        lineHeight = 19.6.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.28).sp
    ),
    bodySemi14 = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 14.sp,
        lineHeight = 19.6.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.28).sp
    ),
    bodyMedium14 = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 14.sp,
        lineHeight = 19.6.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.28).sp
    ),

    // Caption
    capBold12 = TextStyle(
        fontFamily = PretendardBold,
        fontSize = 12.sp,
        lineHeight = 16.8.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.24).sp
    ),
    capSemi12 = TextStyle(
        fontFamily = PretendardSemiBold,
        fontSize = 12.sp,
        lineHeight = 16.8.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.24).sp
    ),
    capMedium12 = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 12.sp,
        lineHeight = 16.8.sp,
        lineHeightStyle = defaultLineHeightStyle,
        letterSpacing = (-.24).sp
    )
)

val localNapzakMarketTypography = staticCompositionLocalOf { defaultNapzakMarketTypography }
