package com.napzak.market.presentation.onboarding.state

import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.genre.model.Genre

data class OnboardingUiState(
    val genreList: UiState<List<Genre>> = initialGenreList,
    val selectedGenreList: List<Genre> = emptyList(),
    val searchText: String = "",
) {
    companion object {
        val initialGenreList = UiState.Success(
            listOf(
                Genre(
                    genreId = 1,
                    genreName = "나루토",
                    genreImgUrl = "https://dh.aks.ac.kr/Edu/wiki/images/2/2e/%EB%82%98%EB%A3%A8%ED%86%A0_%EC%A0%84%EC%B2%B4.PNG"
                ),
                Genre(
                    genreId = 2,
                    genreName = "원피스",
                    genreImgUrl = "https://i.namu.wiki/i/LpkAOSE_oosO8tQVw4vZKLqaWyk6pu_pTegqW5WVf3JOqRgHJJJHsl-EpYBifUlZ6wxy7a1913LSlvZHcYc717e82bIl7-lU81Pc0HrhGcP8PTIdYuGH9hyeIrLTqLqWUOn9aUfNf9npxclKVSbceA.webp"
                ),
                Genre(
                    genreId = 3,
                    genreName = "블리치",
                    genreImgUrl = "https://i.namu.wiki/i/3Ho3EiZ1aryxSj-EXfCHanSMrCwz2D8Se3743bo4ykB0i67_a587bUe63mCr2UHnsL5sU_SXhgDcOSWhGC7Uqnuvv0F2g-kmj1zPHPjSqdAW36PGfezm60Pwm3DuMIgUijkiqZfFKdPtDbaqftBGPQ.webp"
                ),
                Genre(
                    genreId = 4,
                    genreName = "귀멸의 칼날",
                    genreImgUrl = "https://pbs.twimg.com/media/FXYXlAMUUAA9CIu?format=png&name=small"
                ),
                Genre(
                    genreId = 5,
                    genreName = "주술회전",
                    genreImgUrl = "https://i.namu.wiki/i/D_KtZ8UbtmM0l1uMKh9uKKp3SlurSwzCws7g2Ems9p28ZMtfZcAuTXGAv5i5T1l8iPRDXG60tkAOQiWArTVX1hWkA00nIr2DyO3R2zKZskgQ2Ob92bhsHLc8bl1lzLvqzHkm8KX6zJyCYUJ5Nb6kcw.webp"
                ),
                Genre(
                    genreId = 6,
                    genreName = "진격의 거인",
                    genreImgUrl = "https://i.namu.wiki/i/yO3vD2NieG0oaofam4r4PskWb-rj-Xfqsrcx8uyhIXv-V04v8ngxxwN_TxZ0tqvZ7w4hQILEKUam1fSx6nPXugZdI8Cb5CIVSemviR0rRsKorhoojoMsWXT26n_mQqP2RO-Ap8BW5SGRv54Yw1DECg.webp"
                ),
                Genre(
                    genreId = 7,
                    genreName = "데스노트",
                    genreImgUrl = "https://i.namu.wiki/i/QESwzJexnEqu7ANbzRghRkG9SdCKjRz_gnia5C6OUBp266PKsbPiRTaLqYs7nJ0c0wXxkCyfegFWy3wKYbtt6j2yfvOvEp3xaWXNy2X6ku_3ibnmC6v0Uq9XpAlAVErMpVuqm2uKLq9mxe-faXshdg.webp"
                ),
                Genre(
                    genreId = 8,
                    genreName = "짱구는 못말려",
                    genreImgUrl = "https://upload.wikimedia.org/wikipedia/ko/4/4a/%EC%8B%A0%EC%A7%B1%EA%B5%AC.png"
                ),
                Genre(
                    genreId = 9,
                    genreName = "나루토",
                    genreImgUrl = "https://dh.aks.ac.kr/Edu/wiki/images/2/2e/%EB%82%98%EB%A3%A8%ED%86%A0_%EC%A0%84%EC%B2%B4.PNG"
                ),
                Genre(
                    genreId = 10,
                    genreName = "원피스",
                    genreImgUrl = "https://i.namu.wiki/i/LpkAOSE_oosO8tQVw4vZKLqaWyk6pu_pTegqW5WVf3JOqRgHJJJHsl-EpYBifUlZ6wxy7a1913LSlvZHcYc717e82bIl7-lU81Pc0HrhGcP8PTIdYuGH9hyeIrLTqLqWUOn9aUfNf9npxclKVSbceA.webp"
                ),
                Genre(
                    genreId = 11,
                    genreName = "블리치",
                    genreImgUrl = "https://i.namu.wiki/i/3Ho3EiZ1aryxSj-EXfCHanSMrCwz2D8Se3743bo4ykB0i67_a587bUe63mCr2UHnsL5sU_SXhgDcOSWhGC7Uqnuvv0F2g-kmj1zPHPjSqdAW36PGfezm60Pwm3DuMIgUijkiqZfFKdPtDbaqftBGPQ.webp"
                ),
                Genre(
                    genreId = 4,
                    genreName = "귀멸의 칼날",
                    genreImgUrl = "https://pbs.twimg.com/media/FXYXlAMUUAA9CIu?format=png&name=small"
                ),
                Genre(
                    genreId = 5,
                    genreName = "주술회전",
                    genreImgUrl = "https://i.namu.wiki/i/D_KtZ8UbtmM0l1uMKh9uKKp3SlurSwzCws7g2Ems9p28ZMtfZcAuTXGAv5i5T1l8iPRDXG60tkAOQiWArTVX1hWkA00nIr2DyO3R2zKZskgQ2Ob92bhsHLc8bl1lzLvqzHkm8KX6zJyCYUJ5Nb6kcw.webp"
                ),
                Genre(
                    genreId = 6,
                    genreName = "진격의 거인",
                    genreImgUrl = "https://i.namu.wiki/i/yO3vD2NieG0oaofam4r4PskWb-rj-Xfqsrcx8uyhIXv-V04v8ngxxwN_TxZ0tqvZ7w4hQILEKUam1fSx6nPXugZdI8Cb5CIVSemviR0rRsKorhoojoMsWXT26n_mQqP2RO-Ap8BW5SGRv54Yw1DECg.webp"
                ),
                Genre(
                    genreId = 7,
                    genreName = "데스노트",
                    genreImgUrl = "https://i.namu.wiki/i/QESwzJexnEqu7ANbzRghRkG9SdCKjRz_gnia5C6OUBp266PKsbPiRTaLqYs7nJ0c0wXxkCyfegFWy3wKYbtt6j2yfvOvEp3xaWXNy2X6ku_3ibnmC6v0Uq9XpAlAVErMpVuqm2uKLq9mxe-faXshdg.webp"
                ),
                Genre(
                    genreId = 8,
                    genreName = "짱구는 못말려",
                    genreImgUrl = "https://upload.wikimedia.org/wikipedia/ko/4/4a/%EC%8B%A0%EC%A7%B1%EA%B5%AC.png"
                ),
            )
        )
    }
}