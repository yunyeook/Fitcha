# <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Objects/Running%20Shoe.png" width="35" height="35"/> FITCHA (피차)

> **"혼자 하던 운동을 함께하는 챌린지로, 나의 핏(Fit)을 찾아가는 여정"**
>
> **FITCHA**는 Fitness와 Challenge의 결합으로, 운동의 꾸준함을 소셜 경험으로 풀어내는 **통합 피트니스 소셜 플랫폼**입니다.

---

## 📺 1. 서비스 소개

### 1-1) FITCHA란?
운동은 꾸준함이 핵심이지만, 혼자 하면 쉽게 동기부여가 떨어집니다.  
FITCHA는 챌린지 참여 → 기록 인증(FitLog) → 커뮤니티 소통 → 실시간 채팅(FitTalk)으로 이어지는 유기적인 흐름을 통해 사용자가 지치지 않고 운동 습관을 형성할 수 있도록 돕습니다.

### 🌟 핵심 가치
- **Keep Going**: 챌린지 기반 참여로 강력한 운동 지속성 부여
- **Together**: 인증글과 실시간 채팅으로 동료들과 함께 성장

---

## ✨ 2. 주요 기능 (Key Features)

### 🏆 ChallengeFit (챌린지)
- 운동 계획을 실천으로 옮기는 **챌린지 생성 및 참여**
- 운동 타입, 부위, 난이도별 필터링을 통한 맞춤형 탐색
- 챌린지 기반의 커뮤니티 피드백 (댓글 및 좋아요)
  
![ChallengeFit](https://github.com/user-attachments/assets/f2dbc1ed-2838-4427-9acb-170b22158f61)


### 📸 FitLog (기록 및 인증)
- 오늘의 운동 완료(오운완) **사진 인증 시스템**
- 타임라인 형식의 기록 관리 및 사용자 간 응원 문화 형성
  
![Fitlog](https://github.com/user-attachments/assets/3183c846-c1fe-48f8-bc15-a5eed61bec9f)


### 🎬 FitTube (운동 영상 탐색)
- **YouTube Data API** 연동을 통한 키워드 맞춤형 홈트 영상 검색
- 운동 영상에 특화된 커뮤니티 기능 제공
  
![FitTube](https://github.com/user-attachments/assets/310afd78-e4cf-4dfc-9077-075c42c6b700)


### 💬 FitTalk (실시간 소통)
- **WebSocket + STOMP** 기반의 실시간 공개 채팅 시스템
- 운동 고민이나 정보를 공유할 수 있는 역동적인 소통 공간

![FitTalk](https://github.com/user-attachments/assets/2f3eb827-477c-40cf-a63f-ebea8d583129)


### 🏠 Home & Dashboard
- **OpenWeatherMap API** 기반의 실시간 날씨 위젯 (실외 운동 적합도 안내)
- 인기 챌린지 및 추천 영상 큐레이션
  
![Home](https://github.com/user-attachments/assets/3550a581-ffed-4175-8c68-8c5ec687d29e)

---

## 🛠 3. 기술 스택 (Tech Stack)

### 🖱 Backend
- **Core**: Java 17, Spring Boot 3.4.5
- **Security**: Spring Security, OAuth 2.0 (Google/Kakao), JWT
- **Database**: MySQL (MyBatis)
- **Real-time**: WebSocket & STOMP
- **API Support**: Springdoc OpenAPI (Swagger)

### 🖱 Frontend
- **Framework**: Vue 3 (Vite)
- **State**: Pinia (상태 및 유저 보안 정보 관리)
- **Communication**: Axios, Vue Router

### 🖱 External APIs
- YouTube Data API v3
- OpenWeatherMap API
- (Experimental) OpenAI API

---

## 📁 4. 프로젝트 구조

```text
.
├── FITCHA_BACKEND      # Spring Boot 소스 코드
│   ├── src/main/java   # 비즈니스 로직 (Controller, Service, DAO, DTO)
│   └── src/resources   # 설정 파일 및 MyBatis Mapper SQL
├── FITCHA_FRONTEND     # Vue.js 소스 코드
│   └── fitcha-project  # Vite 프로젝트 루트
└── README.md           # 프로젝트 전체 안내문
```

---

## 🤝 5. 협업 환경

### 1) Git Branch Strategy
- **`main`**: 최종 배포 및 서비스 운영 브랜치
- **`feature/기능명`**: 새로운 기능 개발용 브랜치 (PR을 통한 코드 리뷰 후 병합)

### 2) Commit Convention
실제 프로젝트 이력에 따라 **`Type: Description`** 형식을 권장합니다.
- `Feat: `: 새로운 기능 추가
- `Fix: `: 버그 수정
- `Chore: `: 빌드 업무, 패키지 매니저 설정, 단순 코드 정리 등
- `Docs: `: 문서 수정
- `Refactor: `: 코드 리팩토링 (기능 변경 없는 코드 구조 변경)
- `Style: `: 코드 포맷팅, 세미콜론 누락 등 (비즈니스 로직 변경 없음)

---
