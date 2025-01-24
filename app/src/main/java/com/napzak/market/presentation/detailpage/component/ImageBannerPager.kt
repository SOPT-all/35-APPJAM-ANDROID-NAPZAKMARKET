package com.napzak.market.presentation.detailpage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.napzak.market.core.designsystem.component.indicator.PageIndicator
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ImageBannerPager(
    bannerImages: ImmutableList<String>,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val pagerState = rememberPagerState(
        pageCount = { bannerImages.size },
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        contentAlignment = Alignment.BottomCenter,
    ) {
        if (bannerImages.isNotEmpty()) {
            HorizontalPager(
                state = pagerState,
                modifier = modifier,
            ) { page ->
                AsyncImage(
                    model = ImageRequest.Builder(context).data(bannerImages[page]).build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = NapzakMarketTheme.colors.gray100),
            )
        }

        if (bannerImages.size > 1) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                NapzakMarketTheme.colors.black.copy(alpha = 0f),
                                NapzakMarketTheme.colors.black.copy(alpha = 0.35f)
                            ),
                        )
                    ),
            ) {
                PageIndicator(
                    imageCount = bannerImages.size,
                    pagerState = pagerState,
                    selectedColor = NapzakMarketTheme.colors.white,
                    unselectedColor = NapzakMarketTheme.colors.gray300,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeBannerPagerPreview() {
    NapzakMarketTheme {
        ImageBannerPager(
            bannerImages = listOf<String>().toImmutableList(),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(360 / 230f),
        )
    }
}
