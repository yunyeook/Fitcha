<template>
  <!-- 왼쪽 사이드바  -->
  <div class="left-sidebar">
    <!-- 로고 또는 프로젝트 이름 -->
    <div class="logo">
      <span>FITCHA</span>
    </div>

    <!-- 다른 페이지로 이동하는 nav바 -->
    <ul>
      <li>
        <a href="../views/home.html">
          <i class="fas fa-home"></i>
          Home
        </a>
      </li>
      <li>
        <a @click="ChallengeFitView">
          <i class="fas fa-fire"></i>
          ChallengeFit
        </a>
      </li>
      <li>
        <a @click="FitTubeView">
          <i class="fab fa-youtube"></i>
          FitTube
        </a>
      </li>
      <li>
        <a href="../views/fitLog.html">
          <i class="fas fa-users"></i>
          Fitlog
        </a>
      </li>
      <li>
        <a href="../views/myFit.html">
          <i class="fas fa-user-circle"></i>
          MyFit
        </a>
      </li>
      <li>
        <a href="#">
          <i class="fas fa-comments"></i>
          FitTalk
        </a>
      </li>
    </ul>

    <!-- 로그아웃 버튼 -->
    <div class="logout-btn">
      <button>
        <i class="fas fa-sign-out-alt"></i>
        logout
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();

function ChallengeFitView() {
  router.push({ name: "ChallengeFit" });
}
function FitTubeView() {
  router.push({ name: "FitTube" });
}
</script>

<style scoped>
/* 전체 공통 레이아웃 중 왼쪽 사이드바 css */

/* Left Sidebar */
.left-sidebar {
  width: 160px;
  padding: 60px 20px 30px;
}

.left-sidebar .logo {
  font-size: 1.7rem;
  font-weight: bold;
  margin-bottom: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.left-sidebar ul {
  list-style: none;
  padding: 0px;
}

.left-sidebar li a {
  text-decoration: none;
  height: 50px;
  margin-bottom: 25px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 15px;
  cursor: pointer;
  color: #9fa3a6;
  gap: 8px;
}

.left-sidebar li a:hover {
  background-color: #1ec08a;
  color: #ffff;
}

.left-sidebar li i {
  padding-bottom: 5px;
}

.left-sidebar .logout-btn {
  display: flex;
  justify-content: center;
  align-items: center;
}
.left-sidebar .logout-btn button {
  width: 180px;
  height: 50px;
  background-color: #fff;
  border: none;
  color: #9fa3a6;
  font-size: 1rem;
  margin-top: 150px;
  cursor: pointer;
}
</style>
