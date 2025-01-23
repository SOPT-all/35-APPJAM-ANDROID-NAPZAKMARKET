package com.napzak.market.presentation.registration.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * Registration plain text field
 *
 * 판매/구매 등록 View에서 사용되는 일반 TextField
 * isTitle 변수로 한 줄 혹은 여러 줄 작성 가능
 *
 * @param text
 * @param placeholder
 * @param onTextChange
 * @param modifier
 * @param isTitle 제목일 경우, 한 줄, 그 외에는 여러 줄
 */

@Composable
fun RegistrationPlainTextField(
    text: String,
    placeholder: String,
    maxLength: Int,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isTitle: Boolean = true,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .then(if (!isTitle) Modifier.aspectRatio(320f / 182f) else Modifier)
                .background(NapzakMarketTheme.colors.white, RoundedCornerShape(12.dp))
                .border(1.dp, NapzakMarketTheme.colors.gray200, RoundedCornerShape(12.dp))
                .padding(vertical = 11.dp, horizontal = 14.dp),
            value = text,
            textStyle = NapzakMarketTheme.typography.bodyMedium14.copy(
                color = NapzakMarketTheme.colors.gray900
            ),
            onValueChange = onTextChange,
            singleLine = isTitle,
            decorationBox = { innerTextField ->
                if (text.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = NapzakMarketTheme.typography.bodyMedium14,
                        color = NapzakMarketTheme.colors.gray400,
                    )
                }
                innerTextField()
            },
        )
        TextFieldCounter(
            currentLength = text.length,
            maxLength = maxLength,
        )
    }
}

@Composable
private fun TextFieldCounter(
    currentLength: Int,
    maxLength: Int,
    modifier: Modifier = Modifier,
) {
    val countColor =
        if (currentLength > 0) NapzakMarketTheme.colors.gray900 else NapzakMarketTheme.colors.gray400
    val annotatedString = buildAnnotatedString {
        withStyle(SpanStyle(color = countColor)) {
            append(currentLength.toString())
        }
        withStyle(SpanStyle(color = NapzakMarketTheme.colors.gray400)) {
            append("/$maxLength")
        }
    }
    Text(
        modifier = modifier,
        text = annotatedString,
        style = NapzakMarketTheme.typography.capMedium12,
    )
}

@Preview
@Composable
private fun RegistrationBaseTextFieldPreview() {
    NapzakMarketTheme {
        val text = remember { mutableStateOf("") }
        RegistrationPlainTextField(
            text = text.value,
            placeholder = "제목을 작성해주세요.",
            onTextChange = { text.value = it },
            maxLength = 240,
        )
    }
}
