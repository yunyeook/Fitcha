<template>
  <div class="right-sidebar">
    <!-- 로그인 유저 정보 섹션 -->

    <div class="user-info" v-if="nickName">
      <img
        v-if="profileImgWithCache"
        :src="profileImgWithCache"
        alt="profile image"
        class="profile-img"
      />
      <img
        v-else
        :src="defaultProfileImg"
        alt="default profile image"
        class="profile-img"
      />
      <div class="user-details">
        <span class="nickname">{{ nickName }}</span>
        <span class="status-indicator" title="온라인 상태"></span>
      </div>
    </div>

    <!-- 로그인 버튼 -->
    <router-link style="text-decoration: none" to="/login">
      <div v-if="!nickName" class="loginBtn">
        <button type="button"><i class="fas fa-user-circle"></i> Login</button>
      </div>
    </router-link>

    <!-- 달력 영역 (현재 비어있음, 스타일만 지정됨) -->
    <div class="calendar-container">
      <Datepicker v-model="selectedDate" :inline="true" />
    </div>
    <!-- 위치 권한 요청 모달 -->
    <div v-if="showLocationRequest" class="modal-overlay">
      <div class="modal-content">
        <p>위치 기반 날씨 정보를 제공하려면 위치 권한이 필요합니다.</p>
        <div class="modal-buttons">
          <!-- 위치 권한 허용 버튼 -->
          <button @click="requestLocation">위치 권한 허용</button>
          <!-- 위치 권한 거부 버튼 -->
          <button class="reject-btn" @click="rejectLocation">거부</button>
        </div>
      </div>
    </div>

    <!-- 위치 권한 요청 모달이 닫힌 후 보여지는 날씨 정보 영역 -->
    <div class="weather" v-else>
      <div class="weather-icon">
        <!-- 하늘 상태에 따른 아이콘 클래스 바인딩 -->
        <i :class="weatherIconClass"></i>
      </div>

      <!-- 날씨 정보 로딩 중 표시 -->
      <div v-if="loading">날씨 정보를 불러오는 중입니다...</div>

      <!-- 에러 메시지 출력 -->
      <div v-else-if="error" class="error-msg">{{ error }}</div>

      <!-- 정상적으로 날씨 데이터가 있을 경우 출력, 값이 없으면 "-" 표시 -->
      <div v-else>
        <p><strong>기온:</strong> {{ temperature || "-" }}°C</p>
        <p><strong>습도:</strong> {{ humidity || "-" }}%</p>
        <p><strong>강수:</strong> {{ rain || "-" }}</p>
        <p><strong>하늘:</strong> {{ sky || "-" }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
// 사용자 상태 관리를 위해 Pinia 스토어 사용
import { useUserStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import Datepicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";

const selectedDate = ref(new Date());

// Vue Composition API
import { ref, computed } from "vue";

// 기본 프로필 이미지 임포트
import defaultProfileImg from "@/assets/images/myfit/profile-default.svg";

// API 호출을 위한 공통 api 모듈 임포트
import api from "@/api/api";

// --- 상태 변수 선언 ---

// 날씨 데이터 저장 변수들 (초기값 빈 문자열)
const temperature = ref("");
const humidity = ref("");
const rain = ref("");
const sky = ref("");

// 로딩 상태 (날씨 정보 요청 중인지 여부)
const loading = ref(false);

// 에러 메시지 저장 변수
const error = ref(null);

// 위치 권한 요청 모달 표시 여부
const showLocationRequest = ref(true);

// --- 날씨 API 호출 함수 ---
async function fetchWeather(lat, lon) {
  try {
    loading.value = true; // 로딩 시작
    error.value = null; // 에러 초기화

    // 백엔드에 현재 위치(lat, lon) 전달해 날씨 정보 요청
    const { data } = await api.get("/weather", {
      params: { lat, lon },
    });

    // 받아온 날씨 데이터를 각각 변수에 저장
    temperature.value = data.temperature;
    humidity.value = data.humidity;
    rain.value = data.rain;
    sky.value = data.sky;
  } catch (err) {
    // 요청 실패 시 에러 메시지 저장 및 콘솔에 출력
    error.value = "날씨 정보를 불러오는데 실패했습니다.";
    console.error(err);
  } finally {
    loading.value = false; // 로딩 종료
  }
}

// --- 위치 권한 허용 요청 함수 ---
function requestLocation() {
  if (navigator.geolocation) {
    // 브라우저 위치 기능이 있다면 위치 요청
    navigator.geolocation.getCurrentPosition(
      (pos) => {
        // 위치 권한 허용 시 모달 닫고 위치 기반 날씨 조회
        showLocationRequest.value = false;
        fetchWeather(pos.coords.latitude, pos.coords.longitude);
      },
      (err) => {
        // 위치 권한 거부 시 모달 닫고 기본 위치(서울) 날씨 조회, 에러 메시지 출력
        showLocationRequest.value = false;
        error.value =
          "위치 권한이 거부되었습니다. 기본 위치(서울)로 설정합니다.";
        fetchWeather(37.5665, 126.978);
      }
    );
  } else {
    // 브라우저가 위치 기능을 지원하지 않는 경우
    showLocationRequest.value = false;
    error.value = "이 브라우저는 위치 정보를 지원하지 않습니다.";
  }
}

// --- 위치 권한 거부 버튼 클릭 시 실행할 함수 ---
function rejectLocation() {
  // 모달 닫고 에러 메시지 띄운 뒤 서울 기본 위치로 날씨 조회
  showLocationRequest.value = false;
  error.value =
    "위치 권한 요청이 거부되었습니다. 기본 위치(서울)로 설정합니다.";
  fetchWeather(37.5665, 126.978);
}

// --- 하늘 상태에 따른 아이콘 클래스 계산 (font awesome 클래스) ---
const weatherIconClass = computed(() => {
  switch (sky.value) {
    case "맑음":
      return "fas fa-sun";
    case "구름 많음":
    case "흐림":
      return "fas fa-cloud";
    case "비":
      return "fas fa-cloud-showers-heavy";
    case "눈":
      return "fas fa-snowflake";
    case "비/눈":
      return "fas fa-cloud-meatball";
    default:
      return "fas fa-smog";
  }
});

// --- 사용자 정보 스토어에서 닉네임과 프로필 이미지 URL 가져오기 ---
const userStore = useUserStore();
const { nickName, profileImgUrl } = storeToRefs(userStore);

// --- 프로필 이미지 캐시 무효화용 타임스탬프 (변경 시 이미지 강제 새로고침) ---
const cacheBuster = ref(Date.now());

// --- 프로필 이미지 URL에 타임스탬프 쿼리 추가 (없으면 빈 문자열 반환) ---
const profileImgWithCache = computed(() => {
  if (profileImgUrl.value) {
    return `http://localhost:8080/${profileImgUrl.value}?t=${cacheBuster.value}`;
  }
  return "";
});
</script>

<style scoped>
/* 우측 사이드바 전체 레이아웃 */
.right-sidebar {
  width: 220px;
  padding: 40px 20px 30px;
}
/* 유저 정보 카드 스타일 */
.user-info {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 12px;
  padding: 12px 16px;
  border-radius: 12px;
  cursor: default;
  transition: box-shadow 0.2s ease;
}

/* 프로필 이미지 스타일 */
.profile-img {
  width: 44px;
  height: 44px;
  border-radius: 50%;
  object-fit: cover;
  box-shadow: 0 1px 5px rgba(0, 0, 0, 0.1);
}

/* 닉네임과 상태 인디케이터 컨테이너 */
.user-details {
  display: flex;
  align-items: center;
  gap: 6px;
}

/* 닉네임 텍스트 */
.nickname {
  font-size: 1.1rem;
  font-weight: 600;
  color: #334155; /* slate-700 계열, 깔끔하고 차분 */
  user-select: none;
}

/* 온라인 상태 점 (심플하게, 연한 초록색) */

/* 로그인 버튼 심플 스타일 */
.loginBtn {
  display: flex;
  justify-content: center;
}
.loginBtn button {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 10px 28px;
  border-radius: 9999px;
  background-color: white;
  border: 1.5px solid #cbd5e1; /* 연한 회색 테두리 */
  color: #475569; /* slate-600 */
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  box-shadow: none;
  transition: all 0.25s ease;
}
.loginBtn button:hover {
  background-color: #f1f5f9; /* 아주 연한 회색 */
}
.loginBtn i {
  font-size: 18px;
}

/* 달력 컨테이너 기본 스타일 */
.calendar-container {
  width: 220px;
  padding: 0.8rem;
  margin-top: 4rem;
  border-radius: 1rem;
  background: #f0fdfa;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  font-size: 0.9rem;
  box-sizing: border-box;

  /* 자식 요소가 넘치지 않도록 */
  overflow-x: hidden;
}
/* Datepicker 내부 스타일 override (선택사항) */
:deep(.dp__theme_light) {
  --dp-background-color: white;
  --dp-primary-color: #34d399;
  --dp-border-radius: 12px;
  --dp-text-color: #334155;
  --dp-hover-color: #d1fae5;
  --dp-border-color: #a7f3d0;
}
/* Datepicker를 container에 맞게 100%로 설정 */
:deep(.dp__main) {
  width: 100% !important;
  transform: scale(0.75); /* 크기 줄이기 */
  transform-origin: top left;
}
:deep(.dp__input_wrap) {
  width: 100%;
}

/* 날씨 영역 스타일 */
/* 날씨 영역 스타일 */
.weather {
  height: auto;
  margin-top: 30px;
  padding: 20px;
  border-radius: 16px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-size: 15px;
  text-align: center;
  animation: fadeIn 0.4s ease-in-out;
  transition: background-color 0.3s ease;
}

/* 날씨 아이콘 */
.weather-icon {
  font-size: 80px;
  margin-bottom: 10px;
  color: #fbbf24; /* 햇살 같은 노란색 */
}

/* 텍스트 부분 */
.weather p {
  margin: 4px 0;
  color: #334155; /* slate-700 */
  font-weight: 500;
}

/* 에러 메시지 */
.error-msg {
  color: #ef4444;
  font-weight: 600;
  margin-top: 10px;
}

/* 부드러운 fade-in 등장 효과 */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(8px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* 모달 오버레이 스타일 (화면 전체 반투명 배경) */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.4);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
}

/* 모달 내부 컨텐츠 박스 */
.modal-content {
  background: white;
  padding: 30px 40px;
  border-radius: 12px;
  text-align: center;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
  font-size: 1rem;
}

/* 모달 문단 */
.modal-content p {
  margin-bottom: 20px;
  font-weight: 600;
  color: #333;
}

/* 모달 내 버튼 컨테이너 (허용, 거부 버튼을 가로로 배치) */
.modal-content .modal-buttons {
  display: flex;
  justify-content: center;
  gap: 20px;
}

/* 공통 모달 버튼 스타일 */
.modal-content button {
  padding: 10px 30px;
  font-size: 1rem;
  font-weight: 600;
  border-radius: 9999px;
  border: none;
  background-color: #34d399;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.modal-content button:hover {
  background-color: #059669;
}

/* 거부 버튼만 빨간색으로 스타일 별도 지정 */
.modal-content .reject-btn {
  background-color: #ef4444;
}

.modal-content .reject-btn:hover {
  background-color: #b91c1c;
}
</style>
