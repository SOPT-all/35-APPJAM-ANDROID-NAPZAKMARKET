package com.napzak.market.presentation.registration.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * Post option card
 *
 * 배송비를 설정하는 card입니다.
 *
 * @param title
 * @param price
 * @param placeHolder
 * @param onPriceChange 가격 마스킹은 외부에서 람다로 받음
 * @param modifier
 */

@Composable
fun PostOptionCard(
    title: String,
    price: TextFieldValue,
    placeHolder: String,
    onPriceChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isChecked by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(NapzakMarketTheme.colors.white, RoundedCornerShape(12.dp))
            .border(1.dp, NapzakMarketTheme.colors.gray200, RoundedCornerShape(12.dp))
            .padding(vertical = 10.dp, horizontal = 15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RegistrationCheckBox(
                    isChecked = isChecked,
                    onCheckedChange = { isChecked = !isChecked },
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = title,
                    style = NapzakMarketTheme.typography.bodySemi14,
                    color = NapzakMarketTheme.colors.gray900
                )
            }
            Icon(
                modifier = Modifier.rotate(
                    animateFloatAsState(
                        targetValue = if (isChecked) 180f else 0f,
                        animationSpec = tween(durationMillis = 300)
                    ).value
                ),
                imageVector = ImageVector.vectorResource(R.drawable.ic_up_24),
                contentDescription = null,
                tint = NapzakMarketTheme.colors.gray400
            )
        }
        AnimatedVisibility(
            visible = isChecked,
            enter = expandVertically(
                expandFrom = Alignment.Top
            ),
            exit = shrinkVertically(
                shrinkTowards = Alignment.Top
            )
        ) {
            RegistrationPriceTextField(
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 10.dp)
                    .background(NapzakMarketTheme.colors.gray50, RoundedCornerShape(10.dp))
                    .padding(vertical = 11.dp, horizontal = 15.dp),
                price = price,
                placeholder = placeHolder,
                onPriceChange = onPriceChange
            )
        }
    }
}

/**
 * Registration check box
 *
 * 등록 화면에서 사용되는 checkbox
 *
 * @param isChecked
 * @param onCheckedChange
 * @param modifier
 */

@Composable
private fun RegistrationCheckBox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val checkBox =
        if (isChecked) R.drawable.ic_checkbox_selected_20 else R.drawable.ic_checkbox_unselected_20
    Icon(
        modifier = modifier
            .noRippleClickable { onCheckedChange(!isChecked) },
        imageVector = ImageVector.vectorResource(checkBox),
        contentDescription = null,
        tint = Color.Unspecified,
    )
}

@Preview
@Composable
private fun PostOptionCardPreview() {
    NapzakMarketTheme {
        var price by remember { mutableStateOf(TextFieldValue("")) }
        Column {
            PostOptionCard(
                title = "일반 택배",
                price = price,
                placeHolder = "100~30,000",
                onPriceChange = { price = it }
            )
            PostOptionCard(
                title = "알뜰/반값 택배",
                price = price,
                placeHolder = "0~5,000",
                onPriceChange = { price = it }
            )
        }
    }
}
