package com.napzak.market.presentation.registration.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.ProductConditionType

@Composable
fun RegistrationSellGroup(
    normalPrice: String,
    normalPricePlaceHolder: String,
    onNormalPriceChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.background(NapzakMarketTheme.colors.white),
    ) {
        Column(
            modifier = modifier,
        ) {
            Text(
                text = stringResource(R.string.regi_product_condition),
                style = NapzakMarketTheme.typography.bodySemi16,
                color = NapzakMarketTheme.colors.gray900,
            )
            Spacer(modifier = Modifier.height(12.dp))
            ProductConditionGridButton(
                conditionType = ProductConditionType.entries.map { it.condition },
                selectedIndex = -1,
                onIndexSelected = { /*TODO*/ },
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
        HorizontalDivider(
            thickness = 8.dp,
            color = NapzakMarketTheme.colors.gray50,
        )
        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Preview
@Composable
private fun RegistrationSellGroupPreview() {
    NapzakMarketTheme {
        var normalPrice by remember { mutableStateOf("") }
        RegistrationSellGroup(
            normalPrice = normalPrice,
            normalPricePlaceHolder = "100~30,000",
            onNormalPriceChange = { normalPrice = it.toString() },
        )
    }
}
