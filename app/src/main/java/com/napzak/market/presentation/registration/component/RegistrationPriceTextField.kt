package com.napzak.market.presentation.registration.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.napzak.market.R
import com.napzak.market.core.common.util.priceSeparatorTransformation
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * Registration price text field
 *
 * 판매 등록에서 사용되는 상품 가격 / 배송비 가격 TextField입니다.
 *
 * @param price
 * @param placeholder
 * @param onPriceChange
 * @param modifier
 */

@Composable
fun RegistrationPriceTextField(
    price: String,
    placeholder: String,
    onPriceChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        value = price,
        textStyle = NapzakMarketTheme.typography.bodyMedium16.copy(
            color = NapzakMarketTheme.colors.gray900,
        ),
        onValueChange = onPriceChange,
        singleLine = true,
        visualTransformation = priceSeparatorTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.NumberPassword,
        ),
        decorationBox = { innerTextField ->
            Box {
                if (price.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = NapzakMarketTheme.typography.bodyMedium16,
                        color = NapzakMarketTheme.colors.gray400,
                    )
                }
                innerTextField()
                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = stringResource(R.string.regi_price_won),
                    style = NapzakMarketTheme.typography.bodyMedium16,
                    color = NapzakMarketTheme.colors.gray600,
                )
            }
        },
    )
}

@Preview
@Composable
private fun RegistrationPriceTextFieldPreview() {
    NapzakMarketTheme {
        var text by remember { mutableStateOf("") }
        Column {
            RegistrationPriceTextField(
                price = text,
                placeholder = "0",
                onPriceChange = { text = it }
            )
        }
    }
}
