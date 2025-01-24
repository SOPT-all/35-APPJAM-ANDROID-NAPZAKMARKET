## 👾 덕후들이 사랑하는 거래공간 : 납작마켓 👾
<img src="https://github.com/user-attachments/assets/003b8003-2fbf-4c77-a9a7-aede81e4a123" width = "800"/>

**납작마켓**은 2D 캐릭터부터 가상(Virtual) 장르의 아이템까지, **납작한 것들만**을 다루는 서브컬처 장르 전용 **중고거래 서비스**입니다.

## 🌟주요 기능

### 1️⃣ 덕후 취향 반영 온보딩
- 관심 장르를 직접 설정하고 취향에 딱 맞는 아이템들을 한눈에 확인해요.
- 개인 맞춤 상품 추천으로 취향 저격 아이템을 발견해보세요.

### 2️⃣ 500여개 장르로 세분화된 상세 탐색
- 불편한 검색은 이제 그만! 애니메이션, 게임 등 세분화된 장르로 더욱 편리하고 정확하게 상품을 찾아봐요.
- 검색, 필터 기능을 통해 원하는 장르 및 아이템을 쉽고 정확하게 탐색해보세요.

### 3️⃣ 원하는 상품은 '구해요'에서 쉽게 찾고, 팔고 싶은 굿즈는 '팔아요'에서 빠르게 거래해요
- ‘팔아요’와 ‘구해요’ 카테고리를 통해 거래 목적에 맞게, 보다 편리하고 확실하게 거래해보세요.

### 4️⃣ **덕후들의 거래 방식에 딱 맞는 등록 시스템!**
- 원하는 아이템을 구하기 위해 **매일 검색하거나 찾아다니지 않아도 돼요!**
- **장르 설정**부터 **상품 상태 설정**까지, 빠르고 간편한 거래가 가능해요.
- **가격 제시 버튼**과 **원하는 가격대 설정 기능**으로 위시템을 구할 수 있어요.

### 5️⃣ 나만의 덕질 마켓
- 자신만의 독특한 스타일로 마켓의 개성을 드러낼 수 있어요.
- 관심 장르, 소개글, 프로필 이미지로 직접 커스텀하여 나만의 마켓을 꾸며보세요.

  <br>

## ⛰️ Issue Tracking
[납작마켓 안드로이드 이슈 관리 노션](https://understood-soldier-501.notion.site/10543e9a6b8346f2b166f5dbcb6e0625?v=c8a51898f31f4e08a656b600b617e0f2&pvs=4)

<br>

## 📄 Conventions

**💻 [Github Convention](https://www.notion.so/Github-Convention-43d85e549715430383c9cafc6211edc8?pvs=4)**

**🧔 [Naming Convention](https://www.notion.so/Naming-Convention-8fa2e29e82434fd38dc3ee218d72818c?pvs=4)**

**📦 [Package Convention](https://www.notion.so/Packaging-Convention-6a4dea5a32e94fa9b14c307b5bcb7c2c?pvs=4)**

**📁 [Project Setting](https://www.notion.so/Project-Setting-56ed8782613046508f680ab89873cabd?pvs=4)**

<br>

## 🤙🏻 Corporation Rule

**💡 [안드 납자기들 협업수칙 보러가기](https://www.notion.so/0a619942d89f4960aa449b336a307a5d?pvs=4)**

<br>

## 🛠️ Tech Stack

### Language
 ![Kotlin](https://img.shields.io/badge/Kotlin-%2B%20JVM-7F52FF?style=for-the-badge&logo=kotlin)

### UI Framework
 ![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android)
 ![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-0F7F78?style=for-the-badge&logo=android)

### Dependency Injection
 ![Hilt](https://img.shields.io/badge/Hilt-2A4D6E?style=for-the-badge&logo=android)

### Network
 ![Retrofit2](https://img.shields.io/badge/Retrofit-FF2D20?style=for-the-badge&logo=android)

### Asynchronous Processing
 ![Coroutine](https://img.shields.io/badge/Coroutines-3D8AD3?style=for-the-badge&logo=kotlin)

### Image Processing
 ![Coil](https://img.shields.io/badge/Coil-FF9500?style=for-the-badge&logo=android)
 ![Lottie](https://img.shields.io/badge/Lottie-FF64F4?style=for-the-badge&logo=lottie)

### Logging
 ![Timber](https://img.shields.io/badge/Timber-%23181818?style=for-the-badge&logo=android)


### 주요 설계 및 아키텍처

| 항목                    | 내용                                                         |
|-------------------------|--------------------------------------------------------------|
| **Architecture**         | **Clean Architecture**: 각 계층을 분리하여 유지보수성과 확장성을 확보. |
| **Pattern**              | **MVVM**: UI와 비즈니스 로직 분리를 통해 효율적인 상태 관리 및 코드 유지보수 용이. |
| **UI Framework**         | **Jetpack Compose**: 직관적이고 선언적인 UI 작성 가능, 코드 재사용성 증가. |
| **Dependency Injection** | **Hilt**: 의존성 관리 간소화, 코드의 모듈화와 테스트 용이성 증대. |
| **Navigation**           | **Jetpack Navigation**: 화면 간 이동 및 데이터 전달을 안전하고 효율적으로 처리. |
| **Network**              | **Retrofit2, OkHttp**: 네트워크 요청 처리, 타입 안정성 보장. |
| **Image Processing**     | **Coil, Lottie**: 이미지 로딩 및 애니메이션 처리 효율적. |
| **Asynchronous**         | **Coroutine, Flow**: 비동기 처리 및 동시성 프로그래밍 지원. |

<br>

## 🤓 OverView
```
📦com.napzak.market
├─📂core
│  ├─📂common
│  ├─📂designsystem
│  ├─📂local
│  └─📂network
│  └─📂type
├─📂data
│  ├─📂chat
│  ├─📂detailpage
│  ├─📂dummy
│  ├─📂explore
│  ├─📂genre
│  ├─📂home
│  ├─📂interest
│  ├─📂marketinfo
│  ├─📂mypage
│  └─📂registration
├─📂domain
│  ├─📂chat
│  ├─📂detailpage
│  ├─📂dummy
│  ├─📂explore
│  ├─📂genre
│  ├─📂home
│  ├─📂interest
│  ├─📂marketinfo
│  ├─📂mypage
│  └─📂registration
├─📂presentaion
│  ├─📂chat
│  ├─📂detailpage
│  ├─📂dummy
│  ├─📂explore
│  ├─📂home
│  ├─📂main
│  ├─📂genre
│  ├─📂home
│  ├─📂interest
│  ├─📂marketinfo
│  ├─📂mypage
│  ├─📂onboarding
│  ├─📂prepare
│  ├─📂splash
│  └─📂registration
```
</br>


## 👾 Contributors
| <img src="https://avatars.githubusercontent.com/u/101652649?v=4" width = "200"/> | <img src="https://avatars.githubusercontent.com/u/89915076?v=4" width = "200" /> | <img src="https://github.com/user-attachments/assets/6b4d5b3c-6c6b-433b-8e21-2f3d2a1da2ec" width = "200"/> | <img src="https://avatars.githubusercontent.com/u/182846193?v=4" width = "200"/> | <img src="https://github.com/user-attachments/assets/a78f59d9-6e3b-4ee9-8382-9149f111c442" width = "200"/> |
|:-------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------:|
|                      [이석준](https://github.com/boiledEgg-s)                      |                        [김채린](https://github.com/chrin05)                        |                       [김종명](https://github.com/jm991014)                        |                       [장재원](https://github.com/jangsjw)                        |                        [이연진](https://github.com/yeonjeen)                        |
| `온보딩` <br> `홈` | `탐색` <br> `내마켓` | `등록` | `상세보기` <br> `마이페이지` | `멘토` |

