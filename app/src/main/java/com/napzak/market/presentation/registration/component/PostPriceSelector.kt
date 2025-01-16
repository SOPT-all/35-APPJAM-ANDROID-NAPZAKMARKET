package com.napzak.market.presentation.registration.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R.string.regi_post_price_include
import com.napzak.market.R.string.regi_post_price_exclude
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * Post price selector
 *
 * 배송비 포함 혹은 별도 고르는 selector
 *
 * @param selectedTabIndex
 * @param onIndexSelected
 * @param modifier
 */

@Composable
fun PostFeeSelector(
    selectedTabIndex: Int,
    onIndexSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val feeTab = listOf(
        stringResource(regi_post_price_include),
        stringResource(regi_post_price_exclude),
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(NapzakMarketTheme.colors.gray100, RoundedCornerShape(10.dp))
            .padding(3.dp),
    ) {
        Row {
            feeTab.forEachIndexed { index, tab ->
                PostFeeSelectorIndicator(
                    modifier = Modifier
                        .padding(3.dp)
                        .weight(1f)
                        .noRippleClickable { onIndexSelected(index) },
                    isSelected = selectedTabIndex == index,
                    title = tab,
                )
            }
        }
    }
}

@Composable
private fun PostFeeSelectorIndicator(
    isSelected: Boolean,
    title: String,
    modifier: Modifier = Modifier,
) {
    val textColor = if (isSelected) NapzakMarketTheme.colors.gray900 else NapzakMarketTheme.colors.gray600
    val backgroundColor = if (isSelected) NapzakMarketTheme.colors.white else NapzakMarketTheme.colors.white0

    Text(
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(10.dp))
            .padding(vertical = 8.dp),
        text = title,
        style = NapzakMarketTheme.typography.bodySemi14,
        color = textColor,
        textAlign = TextAlign.Center,
    )
}

@Preview
@Composable
private fun PostFeeSelectorPreview() {
    NapzakMarketTheme {
        var selectedTabIndex by remember { mutableStateOf(0) }
        PostFeeSelector(
            selectedTabIndex = selectedTabIndex,
            onIndexSelected = { selectedTabIndex = it }
        )
    }
}
