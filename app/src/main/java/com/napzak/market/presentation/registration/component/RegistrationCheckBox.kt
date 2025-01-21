package com.napzak.market.presentation.registration.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * Registration check box
 *
 * 등록 화면에서 사용되는 checkbox
 *
 * @param isChecked
 * @param onCheckChange
 * @param modifier
 */

@Composable
fun RegistrationCheckBox(
    isChecked: Boolean,
    onCheckChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val checkBox =
        if (isChecked) R.drawable.ic_checkbox_selected_20 else R.drawable.ic_checkbox_unselected_20

    Icon(
        modifier = modifier
            .noRippleClickable { onCheckChange(!isChecked) },
        imageVector = ImageVector.vectorResource(checkBox),
        contentDescription = null,
        tint = Color.Unspecified,
    )
}

@Preview
@Composable
private fun PostOptionCardPreview() {
    NapzakMarketTheme {
        RegistrationCheckBox(
            isChecked = true,
            onCheckChange = {}
        )
    }
}
