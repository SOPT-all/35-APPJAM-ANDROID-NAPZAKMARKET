package com.napzak.market.core.designsystem.component.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.napzak.market.R
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

//TODO: 주석 넣기

@Composable
fun BackTopBar(
    title: String,
    onBackClick: () -> Unit,
    textStyle: TextStyle = NapzakMarketTheme.typography.titleSemi18,
    backgroundColor: Color = NapzakMarketTheme.colors.white,
    contentColor: Color = NapzakMarketTheme.colors.black,
    modifier: Modifier = Modifier
) {
    CommonTopBar(
        title = title,
        navigationIcon = ImageVector.vectorResource(R.drawable.ic_back_24),
        onNavigationClick = onBackClick,
        modifier = modifier,
        textStyle = textStyle,
        backgroundColor = backgroundColor,
        contentColor = contentColor
    )
}

@Preview(showBackground = true, widthDp = 375, heightDp = 54)
@Composable
fun PreviewGenreTopBar() {
    NapzakMarketTheme {
        BackTopBar(
            title = "장르",
            onBackClick = { /* Preview Action */ }
        )
    }
}