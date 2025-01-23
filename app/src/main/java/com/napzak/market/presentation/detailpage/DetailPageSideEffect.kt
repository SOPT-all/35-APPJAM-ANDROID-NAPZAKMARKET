package com.napzak.market.presentation.detailpage

sealed interface DetailPageSideEffect {
    data object ShowLikeSnackBar : DetailPageSideEffect
}