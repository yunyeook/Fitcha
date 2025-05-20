<template>
  <div class="profile-wrapper">
    <!-- 프로필 헤더 -->
    <div class="profile-info-wrapper">
      <img
        src="../../assets/images/run.jpg"
        alt="프로필 사진"
        class="profile-img"
      />
      <div class="user-info">
        <h2 class="user-name">김싸피</h2>
        <p class="user-nickname">@러너러너러너</p>
      </div>
      <a href="../views/myFitUpdate.html">
        <button class="edit-btn">프로필 수정</button>
      </a>

      <!-- 관심 분야 태그 -->
      <div class="tags">
        <span class="tag">헬스</span>
        <span class="tag">요가</span>
        <span class="tag">홈트레이닝</span>
      </div>
    </div>

    <!-- 탭 메뉴 -->
    <div class="tab-menu">
      <button class="tab active">참여한 챌린지</button>
      <button class="tab">내 인증 게시글</button>
    </div>

    <!-- 콘텐츠 예시 -->
    <div class="tab-content">
      <!-- 아래는 테스트 위한 복붙 -->
    </div>
  </div>
</template>

<script setup>
import ChallengeFitCard from "../challengefit/ChallengeFitCard.vue";
import FitLogCard from "../fitlog/FitLogCard.vue";
</script>

<style scoped>
.profile-wrapper {
  font-family: "Noto Sans KR", sans-serif;
  max-width: 480px;
  margin: 0 auto;
  border-radius: 20px;
  background-color: #ffffff;
  box-shadow: 0px 6px 20px rgba(0, 0, 0, 0.15);
}

.profile-info-wrapper {
  padding: 24px 24px 0px 24px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
  margin-bottom: 16px;
}

.profile-info-wrapper .profile-img {
  width: 110px;
  height: 110px;
  border-radius: 50%;
  object-fit: cover;
}

.profile-info-wrapper .user-info {
  flex: 1;
}

.profile-info-wrapper .user-name {
  text-align: center;
  font-size: 1.2rem;
  margin: 0;
  font-weight: 600;
}

.profile-info-wrapper .user-nickname {
  margin: 0px;
  text-align: center;
  font-size: 0.9rem;
  color: #666;
  margin-top: 4px;
}

.edit-btn {
  padding: 6px 12px;
  font-size: 0.85rem;
  border: 1px solid #ccc;
  border-radius: 20px;
  background-color: white;
  color: #666;
  cursor: pointer;
}

.tags {
  margin-bottom: 16px;
  display: flex;
  justify-content: center;
  gap: 10px;
}

.tag {
  display: inline-block;
  background-color: #e0f7ec;
  color: #2ecc71;
  padding: 4px 10px;
  border-radius: 12px;
  font-size: 0.75rem;
  margin-right: 6px;
}

.tab-menu {
  display: flex;
  border: 1px solid #e0e0e0;
  margin-bottom: 16px;
}

.tab {
  flex: 1;
  padding: 10px 0;
  background: none;
  border: none;
  font-size: 1rem;
  cursor: pointer;
  color: #666;
}

.tab.active {
  color: #ffffff;
  background-color: #3cb371;
  font-weight: bold;
  border-color: #222;
}

.tab-content {
  padding: 10px 24px;
  font-size: 0.9rem;
  color: #444;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
}
</style>
