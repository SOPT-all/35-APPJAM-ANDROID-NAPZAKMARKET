package com.napzak.market.presentation.onboarding.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun OnboardingTopBar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(top = 60.dp)
    ) {
        Text(
            text = stringResource(R.string.onboarding_top_title),
            style = NapzakMarketTheme.typography.titleBold22.copy(color = NapzakMarketTheme.colors.gray900),

            )

        Text(
            text = stringResource(R.string.onboarding_top_choice),
            style = NapzakMarketTheme.typography.bodySemi14.copy(color = NapzakMarketTheme.colors.gray500),
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingTopBarPreview() {
    NapzakMarketTheme {
        OnboardingTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                )
        )
    }
}