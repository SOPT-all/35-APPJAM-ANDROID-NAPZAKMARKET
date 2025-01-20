package com.napzak.market.presentation.registration.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.ProductConditionType
import com.napzak.market.presentation.registration.type.PostFeeType

@Composable
fun RegistrationSellGroup(
    salePrice: String,
    salePricePlaceHolder: String,
    onSalePriceChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    productCondition: ProductConditionType?,
    onProductConditionChange: (ProductConditionType) -> Unit,
    postFeeType: PostFeeType,
    onPostFeeChange: (PostFeeType) -> Unit,
    isNormalPostChecked: Boolean,
    onNormalPostCheckedChange: (Boolean) -> Unit,
    normalPostFee: String,
    onNormalPostFeeChange: (String) -> Unit,
    isHalfPostChecked: Boolean,
    onHalfPostCheckedChange: (Boolean) -> Unit,
    halfPostFee: String,
    onHalfPostFeeChange: (String) -> Unit,
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
            selectedCondition = productCondition,
            onConditionSelected = onProductConditionChange,
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
            selectedTab = postFeeType,
            onTabSelected = onPostFeeChange,
        )
        AnimatedVisibility(
            visible = postFeeType == PostFeeType.EXCLUDED,
            modifier = Modifier.animateContentSize()
        ) {
            Column {
                Spacer(modifier = Modifier.height(12.dp))
                PostOptionCard(
                    title = stringResource(R.string.regi_normal_post),
                    price = normalPostFee,
                    placeHolder = stringResource(R.string.regi_normal_post_placeholder),
                    onPriceChange = onNormalPostFeeChange,
                    isChecked = isNormalPostChecked,
                    onCheckedChange = onNormalPostCheckedChange,
                )
                Spacer(modifier = Modifier.height(16.dp))
                PostOptionCard(
                    title = stringResource(R.string.regi_half_price_post),
                    price = halfPostFee,
                    placeHolder = stringResource(R.string.regi_half_price_post_placeholder),
                    onPriceChange = onHalfPostFeeChange,
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
            productCondition = ProductConditionType.GOOD,
            onProductConditionChange = {},
            postFeeType = PostFeeType.INCLUDED,
            onPostFeeChange = {},
            isNormalPostChecked = false,
            onNormalPostCheckedChange = {},
            normalPostFee = "",
            onNormalPostFeeChange = {},
            isHalfPostChecked = false,
            onHalfPostCheckedChange = {},
            halfPostFee = "",
            onHalfPostFeeChange = {},
        )
    }
}
