package com.napzak.market.presentation.registration.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun RegistrationBuyGroup(
    number: String,
    onNumberChange: (String) -> Unit,
    isOfferAvailable: Boolean,
    onCheckChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Spacer(modifier = Modifier.height(36.dp))
        HorizontalDivider(
            thickness = 8.dp,
            color = NapzakMarketTheme.colors.gray50,
        )
        Spacer(modifier = Modifier.height(36.dp))
    }
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.regi_price),
            style = NapzakMarketTheme.typography.bodySemi16,
            color = NapzakMarketTheme.colors.gray900,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.regi_price_description),
            style = NapzakMarketTheme.typography.bodyMedium14,
            color = NapzakMarketTheme.colors.gray600,
        )
        Spacer(modifier = Modifier.height(24.dp))
        RegistrationNumberTextField(
            number = number,
            placeholder = stringResource(R.string.regi_price_range_placeholder),
            onNumberChange = onNumberChange,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            RegistrationCheckBox(
                isChecked = isOfferAvailable,
                onCheckChange = onCheckChange,
            )
            Text(
                text = stringResource(R.string.regi_price_offer),
                style = NapzakMarketTheme.typography.bodySemi14,
                color = NapzakMarketTheme.colors.gray700,
            )
        }
        Spacer(modifier = Modifier.height(56.dp))
    }
}

@Preview
@Composable
private fun RegistrationBuyGroupPreview() {
    NapzakMarketTheme {
        RegistrationBuyGroup(
            number = "",
            onNumberChange = {},
            isOfferAvailable = true,
            onCheckChange = {},
        )
    }
}
