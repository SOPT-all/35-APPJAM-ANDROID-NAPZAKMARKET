package com.napzak.market.presentation.registration.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.presentation.registration.type.PostFeeType

/**
 * Post price selector
 *
 * 배송비 포함 혹은 별도 고르는 selector
 *
 * @param selectedTab
 * @param onTabSelected
 * @param modifier
 */

@Composable
fun PostFeeSelector(
    selectedTab: PostFeeType,
    onTabSelected: (PostFeeType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(NapzakMarketTheme.colors.gray100, RoundedCornerShape(12.dp)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            PostFeeType.entries.forEach { tab ->
                PostFeeSelectorIndicator(
                    modifier = Modifier
                        .weight(1f)
                        .padding(3.dp)
                        .noRippleClickable { onTabSelected(tab) },
                    isSelected = selectedTab == tab,
                    title = tab.label,
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
    val textColor =
        if (isSelected) NapzakMarketTheme.colors.gray900 else NapzakMarketTheme.colors.gray600
    val backgroundColor =
        if (isSelected) NapzakMarketTheme.colors.white else NapzakMarketTheme.colors.white0

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
        PostFeeSelector(
            selectedTab = PostFeeType.EXCLUDED,
            onTabSelected = {},
        )
    }
}
