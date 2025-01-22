package com.napzak.market.core.designsystem.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.component.text.SingleLineText
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun OnboardingGenreItem(
    imgUrl: String,
    genreName: String,
    isSelected: Boolean,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val itemColor =
        if (isSelected) NapzakMarketTheme.colors.purple30 else NapzakMarketTheme.colors.gray900

    Column(
        modifier = modifier
            .noRippleClickable(onItemClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(color = NapzakMarketTheme.colors.gray200),
        ) {
            if (imgUrl.isNotBlank()) {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(imgUrl).build(),
                    contentDescription = genreName,
                    clipToBounds = true,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (isSelected) {
                Image(
                    imageVector = ImageVector.vectorResource(id = com.napzak.market.R.drawable.ic_checkbox_select_16),
                    contentDescription = genreName,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.TopEnd),
                )
            }
        }

        SingleLineText(
            text = genreName,
            style = NapzakMarketTheme.typography.bodySemi16,
            color = itemColor,
            modifier = Modifier.padding(top = 6.dp),
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun NapzakGenreItemPreview() {
    NapzakMarketTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(19.dp, Alignment.CenterHorizontally),
        ) {
            items(10) {
                OnboardingGenreItem(
                    imgUrl = "",
                    genreName = "장르",
                    isSelected = true,
                    onItemClick = {},
                )
            }
        }
    }
}