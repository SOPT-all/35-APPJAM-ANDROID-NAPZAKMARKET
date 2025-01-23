package com.napzak.market.presentation.registration.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.napzak.market.R
import com.napzak.market.R.string.regi_image_number
import com.napzak.market.R.string.x_circle_button
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.common.extension.noRippleCombineClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * Registration photo picker
 *
 * 사진을 추가하는 포토 픽커입니다.
 *
 * @param imageUrlList
 * @param onPhotoClick
 * @param onPress
 * @param onDeleteClick
 * @param modifier
 */

@Composable
fun RegistrationPhotoPicker(
    imageUrlList: List<String>,
    onPhotoClick: () -> Unit,
    onPress: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
    ) {
        item {
            PhotoRegisterButton(
                modifier = Modifier
                    .padding(start = 20.dp),
                imageNumber = imageUrlList.size,
                onPhotoClick = onPhotoClick,
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
        itemsIndexed(
            items = imageUrlList,
            key = { index, _ -> index },
            contentType = { _, item -> item },
        ) { index, url ->
            Row {
                PhotoContainer(
                    modifier = Modifier
                        .padding(end = if (index == imageUrlList.lastIndex) 16.dp else 0.dp),
                    index = index,
                    imageUrl = url,
                    onPress = { onPress(index) },
                    onDeleteClick = onDeleteClick,
                )
                Spacer(modifier = Modifier.width(3.dp))
            }
        }
    }
}

/**
 * Photo register button
 *
 * 이미지를 업로드하기 위한 버튼, 현재 업로드된 이미지를 보여줌
 *
 * @param imageNumber
 * @param onPhotoClick
 * @param modifier
 */
@Composable
private fun PhotoRegisterButton(
    imageNumber: Int,
    onPhotoClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(top = 8.dp)
            .width(80.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .background(NapzakMarketTheme.colors.gray100)
            .noRippleClickable { if (imageNumber < 10) onPhotoClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_photo_28),
            contentDescription = null,
            tint = Color.Unspecified,
        )
        val textColor =
            if (imageNumber > 0) NapzakMarketTheme.colors.gray700 else NapzakMarketTheme.colors.gray500
        val imageNumberText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = textColor)) {
                append(imageNumber.toString())
            }
            withStyle(style = SpanStyle(color = NapzakMarketTheme.colors.gray500)) {
                append(stringResource(regi_image_number))
            }
        }
        Text(
            text = imageNumberText,
            style = NapzakMarketTheme.typography.capSemi12,
        )
    }
}

/**
 * Photo container
 *
 * 사용자가 업로드한 사진을 보여주는 container, 길게 눌러서 대표 사진으로 변경 가능
 *
 * @param index
 * @param onDeleteClick
 * @param onPress
 * @param modifier
 */

@Composable
private fun PhotoContainer(
    index: Int,
    imageUrl: String,
    onDeleteClick: (Int) -> Unit,
    onPress: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .padding(top = 8.dp, end = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(NapzakMarketTheme.colors.gray600)
                .width(80.dp)
                .aspectRatio(1f)
                .noRippleCombineClickable { onPress(index) },
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            if (index == 0) {
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(NapzakMarketTheme.colors.black70),
                    text = stringResource(R.string.regi_represent_image),
                    style = NapzakMarketTheme.typography.capMedium12,
                    color = NapzakMarketTheme.colors.white,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .noRippleClickable { onDeleteClick(index) },
            imageVector = ImageVector.vectorResource(R.drawable.ic_close_circle_28),
            contentDescription = stringResource(x_circle_button),
            tint = Color.Unspecified,
        )
    }
}

@Preview
@Composable
private fun RegistrationPhotoPickerPreview() {
    NapzakMarketTheme {
        RegistrationPhotoPicker(
            imageUrlList = listOf("1", "2", "3"),
            onPhotoClick = { },
            onPress = { },
            onDeleteClick = { },
        )
    }
}
