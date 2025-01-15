package com.napzak.market.presentation.mypage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.button.CommonButton
import com.napzak.market.core.designsystem.component.topbar.CenteredTextTopBar
import com.napzak.market.presentation.mypage.type.MyPageMenuType
import com.napzak.market.R.string.profile_image_description
import com.napzak.market.R.string.view_my_market

@Composable
fun MyPageScreen(
    onMyMarketClick: () -> Unit,
    viewModel: MyPageViewModel = androidx.hilt.navigation.compose.hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NapzakMarketTheme.colors.white),
    ) {
        CenteredTextTopBar(
            title = stringResource(id = R.string.mypage_title),
            textStyle = NapzakMarketTheme.typography.titleBold18,
            backgroundColor = NapzakMarketTheme.colors.white,
            contentColor = NapzakMarketTheme.colors.gray900,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(NapzakMarketTheme.colors.gray50),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = NapzakMarketTheme.colors.white),
                border = BorderStroke(1.dp, NapzakMarketTheme.colors.gray200),
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(
                                    color = NapzakMarketTheme.colors.purple10,
                                    shape = CircleShape,
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(uiState.profileImageUrl)
                                    .placeholder(R.drawable.ic_profile_basic_60)
                                    .error(R.drawable.ic_profile_basic_60)
                                    .build(),
                                contentDescription = stringResource(id = profile_image_description),
                                modifier = Modifier.size(60.dp),
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Text(
                            text = uiState.nickname,
                            style = NapzakMarketTheme.typography.titleBold20,
                            color = NapzakMarketTheme.colors.gray900,
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    CommonButton(
                        text = stringResource(id = view_my_market),
                        onClick = {
                            onMyMarketClick()
                        },
                        buttonColors = ButtonDefaults.buttonColors(containerColor = NapzakMarketTheme.colors.purple30),
                        shape = RoundedCornerShape(12.dp),
                        textStyle = NapzakMarketTheme.typography.bodyBold16,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }

            Spacer(modifier = Modifier.height(38.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyPageMenuType.entries.forEach { menuType ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                when (menuType) {
                                    MyPageMenuType.HISTORY -> {
                                        // TODO: 판매/구매 내역 화면으로 이동
                                    }

                                    MyPageMenuType.GENRE -> {
                                        // TODO: 관심 장르 화면으로 이동
                                    }

                                    MyPageMenuType.FAVORITE -> {
                                        // TODO: 찜한 상품 화면으로 이동
                                    }

                                    MyPageMenuType.RECENT -> {
                                        // TODO: 최근 본 상품 화면으로 이동
                                    }
                                }
                            },
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = menuType.iconResourceId),
                            contentDescription = stringResource(id = menuType.titleResourceId),
                            modifier = Modifier.size(54.dp),
                            tint = Color.Unspecified,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(id = menuType.titleResourceId),
                            style = NapzakMarketTheme.typography.bodyMedium14,
                            color = NapzakMarketTheme.colors.gray700,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(38.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
    NapzakMarketTheme {
        MyPageScreen(
            onMyMarketClick = {
                println("Navigating to 'My Market' screen")
            }
        )
    }
}