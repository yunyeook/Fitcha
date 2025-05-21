<template>
  <div class="right-sidebar">
    <!-- 오른쪽 사이드바   -->

    <!-- 로그인 유저 정보 -->
    <!-- 로그인 안했을시에는 로그인 버튼 떠야함 -->
    <div class="user-info">
      <img src="" alt="" />
      <span>{{ nickName }}</span>
    </div>

    <!-- 달력 -->
    <div class="calendar-container">
      <div id="calendar"></div>
    </div>
    <div class="weather">
      <div class="weather-icon">
        <i class="fas fa-cloud-sun"></i>
      </div>
      <!-- 날씨 정보 -->
      <div class="weather-info">
        <p><strong>기온:</strong> 18°C</p>
        <p><strong>습도:</strong> 55%</p>
        <p><strong>강수:</strong> 10%</p>
        <p><strong>하늘:</strong> 맑음</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { storeToRefs } from "pinia";

// Pinia store 인스턴스 가져오기
const userStore = useUserStore();

// state 값은 storeToRefs()를 사용해서 반응형으로 꺼내기
const { userId, nickName } = storeToRefs(userStore);
</script>

<style scoped>
/* 전체 공통 레이아웃 중 오른쪽 사이드바 css */

/* Right Sidebar */
.right-sidebar {
  width: 200px;
  padding: 60px 20px 30px;
}

.right-sidebar .user-info {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 15px;
  font-size: 1.2rem;
  font-weight: 600;
}

.right-sidebar .user-info img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: #90e4c8;
}

/* 달력 */
.right-sidebar .calendar-container {
  width: 200px;
  height: 340px;
  margin-top: 40px;
  border: 1px solid #ccc;
  border-radius: 12px;
  overflow: hidden;
  background: white;
  font-size: 11px;
}

.right-sidebar .fc {
  font-size: 10px;
}

.right-sidebar .fc .fc-toolbar {
  padding: 4px;
  font-size: 11px;
}

.right-sidebar .fc .fc-toolbar-title {
  font-size: 14px;
}

.right-sidebar .fc .fc-button {
  background: none;
  border: none;
  padding: 0 4px;
  font-size: 14px;
  color: #333;
  cursor: pointer;
}

.right-sidebar .fc .fc-button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.right-sidebar .fc .fc-day-today {
  background-color: #f2f2f2 !important;
  border: 1px solid #aaa;
}

.right-sidebar .fc .fc-daygrid-day-number {
  font-size: 10px;
  padding: 2px;
}

.right-sidebar .fc .fc-daygrid-event {
}

/* 날씨 정보 */
.right-sidebar .weather {
  box-shadow: 0px 1px 3px #90e4c8;
  height: 200px;
  margin-top: 130px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-radius: 12px;
  font-size: 14px;
  text-align: center;
}

.right-sidebar .weather-icon {
  font-size: 70px;
  margin-bottom: 12px;
}

.right-sidebar .weather-info p {
  margin: 4px 0;
  color: #444;
}
</style>
