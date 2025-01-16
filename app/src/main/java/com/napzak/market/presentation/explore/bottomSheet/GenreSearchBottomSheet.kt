package com.napzak.market.presentation.explore.bottomSheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomSheetDefaults.DragHandle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.textField.SearchBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.component.GenreChipButtonGroup
import com.napzak.market.core.designsystem.component.button.EnableDisableTextButton
import com.napzak.market.domain.genre.model.Genre

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreSearchBottomSheet(
    initialSelectedGenreList: List<Genre>,
    genreList: List<Genre>,
    onDismissRequest: () -> Unit,
    onTextChange: (String) -> Unit,
    onButtonClick: (List<Genre>) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = NapzakMarketTheme.colors.white,
        scrimColor = NapzakMarketTheme.colors.black70,
        dragHandle = {
            DragHandle(
                color = NapzakMarketTheme.colors.gray200,
                width = 36.dp,
                height = 4.dp,
            )
        },
        modifier = Modifier.wrapContentHeight()
    ) {
        var searchTerm by remember { mutableStateOf("") }
        var selectedGenreList by remember { mutableStateOf<List<Genre>>(initialSelectedGenreList) }

        LaunchedEffect(searchTerm) {
            onTextChange(searchTerm)
        }

        BackHandler(
            enabled = searchTerm.isNotBlank()
        ) {
            searchTerm = ""
        }

        Column(
            modifier = Modifier
                .background(NapzakMarketTheme.colors.white)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.genre_searching_select_genre),
                    style = NapzakMarketTheme.typography.titleBold18,
                    color = NapzakMarketTheme.colors.gray900
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = stringResource(R.string.onboarding_top_choice),
                    style = NapzakMarketTheme.typography.capSemi12,
                    color = NapzakMarketTheme.colors.gray500
                )

                Spacer(Modifier.height(18.dp))

                SearchBox(
                    placeholder = stringResource(R.string.genre_searching_genre_example),
                    searchTerm = searchTerm,
                )

                Spacer(Modifier.height(10.dp))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(345.dp),
                contentAlignment = Alignment.BottomCenter,
            ) {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    itemsIndexed(
                        items = genreList,
                        key = { _, genreItem -> genreItem.genreId }
                    ) { index, genreItem ->
                        val borderColor =
                            if (index == selectedGenreList.size - 1) NapzakMarketTheme.colors.white else NapzakMarketTheme.colors.gray100

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .drawBehind {
                                    drawLine(
                                        color = borderColor,
                                        start = Offset(0f, size.height - 1),
                                        end = Offset(size.width, size.height - 1),
                                        strokeWidth = 1.dp.toPx(),
                                    )
                                }
                                .noRippleClickable {
                                    if (selectedGenreList.size < 4) {
                                        selectedGenreList = selectedGenreList + genreItem
                                    }
                                }
                                .padding(vertical = 20.dp)
                        ) {
                            Text(
                                text = genreItem.genreName,
                                style = NapzakMarketTheme.typography.bodySemi14,
                                color = NapzakMarketTheme.colors.gray800
                            )
                        }
                    }
                }

                if (selectedGenreList.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .background(NapzakMarketTheme.colors.gray50)
                            .padding(bottom = 12.dp),
                    ) {
                        GenreChipButtonGroup(
                            genreList = selectedGenreList,
                            onGenreClick = { selectedGenre ->
                                selectedGenreList =
                                    selectedGenreList.filter { it.genreId != selectedGenre.genreId }
                            },
                            onResetClick = { selectedGenreList = emptyList() },
                            contentPaddingValues = PaddingValues(horizontal = 20.dp),
                            modifier = Modifier
                                .padding(top = 16.dp),
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            EnableDisableTextButton(
                text = "적용하기",
                isEnabled = isButtonActivate(initialSelectedGenreList, selectedGenreList),
                onClick = { onButtonClick(selectedGenreList) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )

            Spacer(Modifier.height(35.dp))
        }
    }
}

private fun isButtonActivate(list1: List<Genre>, list2: List<Genre>): Boolean {
    return list1.size == list2.size && list1.containsAll(list2)
}

//private fun setButtonText(isEnabled: Boolean): String {
//    return if (isEnabled)
//}