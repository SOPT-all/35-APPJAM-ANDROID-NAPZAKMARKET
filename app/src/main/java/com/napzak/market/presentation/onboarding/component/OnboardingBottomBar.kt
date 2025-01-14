package com.napzak.market.presentation.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.component.button.EnableDisableTextButton
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun OnboardingBottomBar(
    isButtonEnabled: Boolean,
    onCompleteClick: () -> Unit,
    onSkipClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EnableDisableTextButton(
            text = stringResource(R.string.onboarding_bottom_bar_complete),
            isEnabled = isButtonEnabled,
            onClick = onCompleteClick,
            modifier = Modifier
                .background(NapzakMarketTheme.colors.white)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )
        Text(
            text = stringResource(R.string.onboarding_bottom_bar_skip),
            style = NapzakMarketTheme.typography.bodySemi16.copy(color = NapzakMarketTheme.colors.gray500),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(NapzakMarketTheme.colors.white)
                .fillMaxWidth()
                .noRippleClickable(onSkipClick)
                .padding(vertical = 15.dp),
        )
    }
}

@Preview
@Composable
private fun OnboardingBottomBarPreview() {
    NapzakMarketTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(NapzakMarketTheme.colors.gray900)
        ) {
            OnboardingBottomBar(
                isButtonEnabled = true,
                onCompleteClick = {},
                onSkipClick = {},
                modifier = Modifier
            )
        }
    }
}