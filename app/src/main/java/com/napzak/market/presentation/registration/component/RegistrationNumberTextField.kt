package com.napzak.market.presentation.registration.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * Registration number text field
 *
 * 구매 등록에 사용되는 TextField입니다
 *
 * @param number
 * @param placeholder
 * @param onNumberChange
 * @param modifier
 * @receiver
 */

@Composable
fun RegistrationNumberTextField(
    number: String,
    placeholder: String,
    onNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val borderColor = NapzakMarketTheme.colors.gray300

    Row(
        modifier = modifier,
    ) {
        Box {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                BasicTextField(
                    modifier = Modifier
                        .width(96.dp)
                        .drawBehind {
                            drawLine(
                                color = borderColor,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 1.dp.toPx(),
                            )
                        },
                    value = number,
                    textStyle = NapzakMarketTheme.typography.bodyMedium16.copy(
                        color = NapzakMarketTheme.colors.gray900,
                    ),
                    onValueChange = onNumberChange,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.NumberPassword,
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .padding(end = 61.dp, bottom = 8.dp),
                        ) {
                            if (number.isEmpty()) {
                                Text(
                                    modifier = Modifier,
                                    text = placeholder,
                                    style = NapzakMarketTheme.typography.bodyMedium16,
                                    color = NapzakMarketTheme.colors.gray400,
                                )
                            }
                            innerTextField()
                        }
                    },
                )
            }
        }
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = stringResource(R.string.regi_price_range),
            style = NapzakMarketTheme.typography.bodyMedium16,
            color = NapzakMarketTheme.colors.gray900,
        )
    }
}

@Preview
@Composable
private fun RegistrationNumberTextFieldPreview() {
    NapzakMarketTheme {
        val text = remember { mutableStateOf("") }
        RegistrationNumberTextField(
            number = text.value,
            placeholder = "000",
            onNumberChange = {
                if(it.length <= 3) text.value = it
            }
        )
    }
}
