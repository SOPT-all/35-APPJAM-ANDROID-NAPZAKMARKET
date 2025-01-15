package com.napzak.market.presentation.chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.chip.TextChip
import com.napzak.market.core.designsystem.component.chip.model.CustomChipColors
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * 채팅 화면의 정보 섹션 컴포넌트.
 *
 * 제목, 설명, 가격 정보를 표시하며, 필요에 따라 가격 앞에 레이블을 추가로 표시할 수 있습니다.
 * Modifier와 색상을 커스터마이즈할 수 있습니다.
 *
 * @param title 정보 섹션에 표시될 제목 텍스트
 * @param description 정보 섹션에 표시될 설명 텍스트
 * @param price 정보 섹션에 표시될 가격 텍스트
 * @param modifier 컴포넌트 외부에서 전달받은 Modifier로 레이아웃 속성을 설정
 * @param showPriceLabel 가격 레이블을 표시할지 여부 (기본값: false)
 * @param titleColor 제목 텍스트의 색상 (기본값: NapzakMarketTheme.colors.purple30)
 */

@Composable
fun ChatInfoSection(
    title: String,
    description: String,
    price: String,
    modifier: Modifier = Modifier,
    showPriceLabel: Boolean = false,
    titleColor: Color = NapzakMarketTheme.colors.purple30,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = NapzakMarketTheme.colors.white),
    ) {
        HorizontalDivider(
            color = NapzakMarketTheme.colors.gray100,
            thickness = 1.dp,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 14.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        color = NapzakMarketTheme.colors.gray300,
                        shape = RoundedCornerShape(4.dp),
                    )
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = title,
                        color = titleColor,
                        style = NapzakMarketTheme.typography.bodyBold14,
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = description,
                        color = NapzakMarketTheme.colors.gray900,
                        style = NapzakMarketTheme.typography.bodyMedium14,
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (showPriceLabel) {
                        TextChip(
                            text = stringResource(id = R.string.chat_price_label),
                            textStyle = NapzakMarketTheme.typography.capSemi12,
                            chipColors = CustomChipColors(
                                contentColor = NapzakMarketTheme.colors.purple30,
                                containerColor = NapzakMarketTheme.colors.purple10,
                            ),
                            shape = RoundedCornerShape(4.dp),
                            innerPadding = PaddingValues(horizontal = 5.dp, vertical = 3.dp),
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                    }

                    Text(
                        text = price,
                        color = NapzakMarketTheme.colors.gray900,
                        style = NapzakMarketTheme.typography.bodyBold16,
                    )
                }
            }
        }

        HorizontalDivider(
            color = NapzakMarketTheme.colors.gray100,
            thickness = 1.dp,
        )
    }
}
