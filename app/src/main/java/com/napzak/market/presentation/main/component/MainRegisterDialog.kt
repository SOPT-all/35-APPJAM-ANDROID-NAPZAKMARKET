package com.napzak.market.presentation.main.component

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.presentation.main.type.RegisterTab

/**
 * 바텀바의 등록 버튼을 눌렀을 때 나타나는 버튼 그룹입니다.
 *
 * @param visibility 버튼 그룹의 화면 표시 여부입니다.
 * @param onSellRegisterClick 팔아요 등록을 눌렀을 때 호출되는 콜백입니다.
 * @param onBuyRegisterClick 구매 등록을 눌렀을 때 호출되는 콜백입니다.
 * @param onDismissRequest 버튼 그룹을 닫을 때 호출되는 콜백입니다.
 */

@Composable
fun BoxScope.MainRegisterDialog(
    visibility: Boolean,
    onSellRegisterClick: () -> Unit,
    onBuyRegisterClick: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    BackHandler(visibility) {
        onDismissRequest()
    }

    AnimatedVisibility(
        visible = visibility,
        modifier = Modifier.align(Alignment.BottomCenter),
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NapzakMarketTheme.colors.black70)
                .noRippleClickable(onDismissRequest),
        )
    }

    AnimatedVisibility(
        visible = visibility,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .navigationBarsPadding(),
        enter = slideInVertically { fullHeight -> fullHeight },
        exit = fadeOut()
    ) {
        if (visibility) {
            MainRegisterDialog(
                onSellRegisterClick = onSellRegisterClick,
                onBuyRegisterClick = onBuyRegisterClick,
                modifier = Modifier.padding(bottom = 86.dp)
            )
        }
    }
}

@Composable
fun MainRegisterDialog(
    onSellRegisterClick: () -> Unit,
    onBuyRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .clip(RoundedCornerShape(12.dp))
            .background(color = NapzakMarketTheme.colors.white)
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        RegisterTab.entries.forEachIndexed { index, tab ->
            val onClick = when (tab) {
                RegisterTab.SELL -> onSellRegisterClick
                RegisterTab.BUY -> onBuyRegisterClick
            }

            RegisterNavigationButton(
                image = ImageVector.vectorResource(tab.iconRes),
                label = stringResource(tab.titleRes),
                onClick = onClick,
                modifier = Modifier.padding(horizontal = 26.dp)
            )

            if (index != RegisterTab.entries.lastIndex) {
                HorizontalDivider(
                    thickness = Dp.Hairline,
                    color = NapzakMarketTheme.colors.gray200,
                    modifier = Modifier.padding(horizontal = 22.5.dp)
                )
            }
        }
    }
}

@Composable
private fun RegisterNavigationButton(
    image: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .noRippleClickable(onClick)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = image,
            contentDescription = label,
            tint = Color.Unspecified
        )
        Text(
            text = label,
            style = NapzakMarketTheme.typography.bodySemi16,
            color = NapzakMarketTheme.colors.gray900,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterNavigationButtonGroupPreview() {
    NapzakMarketTheme {
        Box {
            MainRegisterDialog(
                onSellRegisterClick = {},
                onBuyRegisterClick = {},
                onDismissRequest = {},
                visibility = true
            )
        }
    }
}
