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
                    genreName = "도라에몽",
                    genreImgUrl = "https://i.namu.wiki/i/V2ctisehHwhwOxx1jRevPa7wMHxTqHFaFbZgqmjNNbF4-93vYVxbApUIv0qtIzYSVuybjuzBGcdkqMAF3xrcb_Ie_ZSlP1qGidHZ2blOY19aacH8JJEm-as45SggiYR5x42ricoQeiLJ5AW1AWiL4g.webp"
                ),
                Genre(
                    genreId = 10,
                    genreName = "강철의 연금술사",
                    genreImgUrl = "https://i.namu.wiki/i/M7PXg6bOs9QstsmznJMZ5o2WOBONKW8WDH5JsUbn1L0ThfxqD-G1VcJwqytgTGcG-OlRoihlAJx6CkFC5ojmkiyO7R3qpprgXrJxGRbNRcK9UO2oNPP_prFAuRxGEJ4BjxZ2wOQb-RlglZmdZIw1qQ.webp"
                ),
                Genre(
                    genreId = 11,
                    genreName = "체인소맨",
                    genreImgUrl = "https://i.namu.wiki/i/3VWWyAZAaiagLDddkbuKn0varjaiwojkDaLxXJlszJuaGqz05NAlj086zhJmcDcD4jASvg7VPOw4Omkz2iPP_tFAnqvuHuWPw6zB0IyunDlntKlOFxJi_KWJZ5tB5f6OzZp-zORp70mE8z-v0ub-uQ.webp"
                ),
                Genre(
                    genreId = 12,
                    genreName = "원펀맨",
                    genreImgUrl = "https://i.namu.wiki/i/IgwJDSVEyb8c58_ML2-MH_eazGBRM3szM_rhV7apCh4MQKtR5XLU8xM_cwmcqaSeIBkbHpAoZdEGqKbtsQzFumgftVdIdKYXxZQYs2r7ojtU_GurM8XVNGnKeh13SFCcs7sOdqJp8HvAijFnZvBCrQ.webp"
                ),
                Genre(
                    genreId = 13,
                    genreName = "드래곤볼",
                    genreImgUrl = "https://i.namu.wiki/i/dMDecm6r_Fu3U8HJxOJOx4yvGIlI8NJzcaM0g5GxY8UG0SpuKFKyJSI53EnSs_Lq4uqrEH8MiIxpIvBRS9tRLz2pBJl-Vbzrc3GGSGuwddq-ficopEOEDcQjAJF9nAOENtyrLBbszEuMF6BnH3a6pg.webp"
                ),
                Genre(
                    genreId = 14,
                    genreName = "명탐정 코난",
                    genreImgUrl = "https://i.namu.wiki/i/qg91tU6-CXOUGTcJsTKaTuvwoYhqktMbZjJGhuChDlAq2sm1qFE0L2hFi_Iniw0VNkNU3SqAE6SpCv3O_nxaDi-RaKEEBEBF_d-msyBTFNf0Wbcn-xKUZdbiqW0hJI32UGDgM9CdMkCsXI-MluS05A.webp"
                ),
                Genre(
                    genreId = 15,
                    genreName = "슬램덩크",
                    genreImgUrl = "https://i.namu.wiki/i/BzA5I_fUnYP1m4V9N7PhZJI5PZQ5saExhqyrvVDBowd7ThtQGaVkPRS20TrIg86rt5miGCVaPl0mCt0SbL_lE1Yb-3F8lCoL65LqkURSu1xhb6ZVCu8poWhO7U-HtgYLBOvgM3nRqb8CRAwA99PQew.webp"
                ),
                Genre(
                    genreId = 16,
                    genreName = "하이큐",
                    genreImgUrl = "https://upload.wikimedia.org/wikipedia/ko/6/63/%ED%95%98%EC%9D%B4%ED%81%90_%EC%9D%BC%EB%B3%B8%EC%96%B4_1%EA%B6%8C_%EC%BB%A4%EB%B2%84.jpg"
                ),
                Genre(
                    genreId = 17,
                    genreName = "포켓몬스터",
                    genreImgUrl = "https://static.inven.co.kr/column/2022/02/04/news/i8209557416.jpg"
                ),
                Genre(
                    genreId = 18,
                    genreName = "나의 히어로 아카데미",
                    genreImgUrl = "https://i.namu.wiki/i/ae3yhYLahQPjZZRSQR8qgsLWmG-gRz_GJi4hRAkm98dVQUrKXCRFIE6Hbi2DdoSVOF-K-8LgTIPS0Gec6Z7fQzpNrE7klBxGXuwnOYMNSpUU9NFrxSf1DW1Cm_QBMr7nALf6iyrOMf_t4Y4gr8yyxA.webp"
                ),
                Genre(
                    genreId = 19,
                    genreName = "Re: 제로부터 시작하는 이세계 생활",
                    genreImgUrl = "https://i.namu.wiki/i/TRHf99H010gCp8Dta7pVpNW9jzIBG_j1xsYE6_pWSefqCGP4PQXF8pReoanaUkxToKkTDf3tPmaXEBgHYarwf0jVVxx3gbXrLtgdjdavQcncf_aPLjZ5GMiayoLVTIMYkGhjDjNWoMYcoVG129Nj9Q.webp"
                ),
                Genre(
                    genreId = 20,
                    genreName = "이 멋진 세계에 축복을!",
                    genreImgUrl = "https://i.namu.wiki/i/MMVW-6oTQtIAFnnuuryQD7gR0W3U87YV28chtDHUrD7DXpS1_CCnoqMXV2QB8XiktrXYUMm65zIBEJYrvXd-dDIRiCSVNTzYqgdyXU3GkHKzMLJd3DeK70x_AEKUfjQlT8Efp5lpuHB-JCTBe0XvjA.webp"
                ),
            )
        )
    }
}