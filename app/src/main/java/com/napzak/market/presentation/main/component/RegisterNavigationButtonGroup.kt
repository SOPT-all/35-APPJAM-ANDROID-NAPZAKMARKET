package com.napzak.market.presentation.main.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
fun BoxScope.RegisterNavigationButtonGroup(
    visibility: Boolean,
    onSellRegisterClick: () -> Unit,
    onBuyRegisterClick: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = visibility,
        modifier = Modifier.align(Alignment.BottomCenter),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = NapzakMarketTheme.colors.black70)
                .clickable { onDismissRequest() },
        )
    }

    AnimatedVisibility(
        visible = visibility,
        modifier = Modifier.align(Alignment.BottomCenter),
        enter = slideInVertically { fullHeight -> fullHeight },
        exit = slideOutVertically { fullHeight -> fullHeight }
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 14.dp)
                .width(160.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = NapzakMarketTheme.colors.white)
                .padding(vertical = 15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
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
}

@Composable
private fun RegisterNavigationButton(
    image: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.noRippleClickable { onClick() },
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
            RegisterNavigationButtonGroup(
                onSellRegisterClick = {},
                onBuyRegisterClick = {},
                onDismissRequest = {},
                visibility = true
            )
        }
    }
}
