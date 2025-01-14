package com.napzak.market.core.designsystem.component.textField

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable

/**
 * 검색창 컴포넌트
 *
 * @param placeholder 검색어가 입력되지 않았을 때 보여지는 텍스트
 * @param searchTerm 사용자가 입력한 검색어 텍스트
 * @param onTextChange 사용자가 텍스트 입력 시 실행할 콜백
 * @param onSearchButtonClick 검색 아이콘 클릭 시 실행할 콜백
 * @param modifier 수정자
 */

@Composable
fun SearchBox(
    placeholder: String,
    searchTerm: String,
    onTextChange: (String) -> Unit,
    onSearchButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = NapzakMarketTheme.colors.gray100,
                shape = RoundedCornerShape(12.dp),
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            value = searchTerm,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f),
            textStyle = NapzakMarketTheme.typography.bodySemi14,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchButtonClick()
                    focusManager.clearFocus()
                }
            ),
            decorationBox = { innerTextField ->
                if (searchTerm.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = NapzakMarketTheme.typography.bodySemi14,
                        color = NapzakMarketTheme.colors.gray400,
                    )
                }
                innerTextField()
            }
        )

        if (searchTerm.isNotEmpty()) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_x_circle_16),
                contentDescription = stringResource(id = R.string.x_circle_button),
                tint = Color.Unspecified,
                modifier = Modifier.noRippleClickable {
                    onTextChange("")
                },
            )
            Spacer(Modifier.width(4.dp))
        }

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_search_24),
            contentDescription = stringResource(id = R.string.search_button),
            tint = Color.Unspecified,
            modifier = Modifier.noRippleClickable {
                focusManager.clearFocus()
                onSearchButtonClick()
            },
        )
    }
}

@Preview
@Composable
fun SearchBoxPreview(modifier: Modifier = Modifier) {
    var searchTerm by remember {mutableStateOf("")}
    SearchBox(
        placeholder = "어떤 아이템을 찾고 계신가요?",
        searchTerm = searchTerm,
        onTextChange = { searchTerm = it },
        onSearchButtonClick = { },
        modifier = modifier
    )
}