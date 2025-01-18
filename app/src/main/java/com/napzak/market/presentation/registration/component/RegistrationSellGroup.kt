package com.napzak.market.presentation.registration.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.button.CommonButton
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.ProductConditionType

@Composable
fun RegistrationSellGroup(
    salePrice: String,
    salePricePlaceHolder: String,
    onSalePriceChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    productCondition: Int,
    onProductConditionChange: (Int) -> Unit,
    postFeeState: Int,
    onPostFeeChange: (Int) -> Unit,
    isNormalPostChecked: Boolean,
    onNormalPostCheckedChange: (Boolean) -> Unit,
    isHalfPostChecked: Boolean,
    onHalfPostCheckedChange: (Boolean) -> Unit,
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
            selectedIndex = productCondition,
            onIndexSelected = onProductConditionChange,
        )
    }
    Spacer(modifier = Modifier.height(36.dp))
    HorizontalDivider(
        thickness = 8.dp,
        color = NapzakMarketTheme.colors.gray50,
    )
    Spacer(modifier = Modifier.height(36.dp))
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.regi_price),
            style = NapzakMarketTheme.typography.bodySemi16,
            color = NapzakMarketTheme.colors.gray900,
        )
        Spacer(modifier = Modifier.height(12.dp))
        RegistrationPriceTextField(
            modifier = Modifier
                .border(1.dp, NapzakMarketTheme.colors.gray200, RoundedCornerShape(12.dp))
                .padding(vertical = 11.dp, horizontal = 14.dp),
            price = (salePrice),
            placeholder = salePricePlaceHolder,
            onPriceChange = onSalePriceChange,
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = stringResource(R.string.regi_post_fee),
            style = NapzakMarketTheme.typography.bodySemi16,
            color = NapzakMarketTheme.colors.gray900,
        )
        Spacer(modifier = Modifier.height(12.dp))
        PostFeeSelector(
            selectedTabIndex = postFeeState,
            onIndexSelected = onPostFeeChange,
        )
        AnimatedVisibility(
            visible = postFeeState == 1,
            modifier = Modifier.animateContentSize()
        ) {
            Column {
                Spacer(modifier = Modifier.height(12.dp))
                PostOptionCard(
                    title = "일반 택배",
                    price = "",
                    placeHolder = "",
                    onPriceChange = {},
                    isChecked = isNormalPostChecked,
                    onCheckedChange = onNormalPostCheckedChange,
                )
                Spacer(modifier = Modifier.height(16.dp))
                PostOptionCard(
                    title = "일반 택배",
                    price = "",
                    placeHolder = "",
                    onPriceChange = {},
                    isChecked = isHalfPostChecked,
                    onCheckedChange = onHalfPostCheckedChange,
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        Spacer(modifier = Modifier.height(68.dp))
    }
}

@Preview
@Composable
private fun RegistrationSellGroupPreview() {
    NapzakMarketTheme {
        RegistrationSellGroup(
            salePrice = "",
            salePricePlaceHolder = "100~30,000",
            onSalePriceChange = { },
            productCondition = -1,
            onProductConditionChange = {},
            postFeeState = 0,
            onPostFeeChange = {},
            isNormalPostChecked = false,
            onNormalPostCheckedChange = {},
            isHalfPostChecked = false,
            onHalfPostCheckedChange = {},
        )
    }
}
