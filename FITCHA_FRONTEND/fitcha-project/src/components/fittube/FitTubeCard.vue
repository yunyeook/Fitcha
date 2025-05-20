<template>
  <!-- 유튜브 영상 카드 구조 -->
  <div class="ytc-card">
    <div class="ytc-thumbnail">
      <img src="../../assets/images/run.jpg" alt="썸네일 이미지" />
      <span class="ytc-duration">12:34</span>
    </div>
    <div class="ytc-info">
      <img src="../assets/images/profile.jpg" alt="채널 프로필" class="ytc-profile" />
      <div class="ytc-text">
        <div class="ytc-title">이것은 유튜브 영상 제목입니다</div>
        <div class="ytc-channel">채널명</div>
        <div class="ytc-meta">
          <span class="ytc-views">조회수 102만회</span> ·
          <span class="ytc-date">2주 전</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.ytc-card {
  width: 280px;
  background-color: #fff;
  border-radius: 12px;
  overflow: hidden;
  transition: all 0.3s ease;
  cursor: pointer;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
}

.ytc-card:hover {
  transform: translateY(-4px);
}

.ytc-thumbnail {
  position: relative;
  width: 100%;
  height: 160px;
  overflow: hidden;
  background-color: #ddd;
}

.ytc-thumbnail img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.ytc-duration {
  position: absolute;
  bottom: 8px;
  right: 8px;
  background-color: rgba(0, 0, 0, 0.8);
  color: #fff;
  padding: 2px 6px;
  font-size: 0.75rem;
  border-radius: 4px;
}

.ytc-info {
  display: flex;
  padding: 12px 16px;
}

.ytc-profile {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  margin-right: 12px;
  object-fit: cover;
}

.ytc-text {
  flex: 1;
}

.ytc-title {
  font-size: 0.95rem;
  font-weight: 500;
  color: #0f0f0f;
  margin-bottom: 6px;
}

.ytc-channel {
  font-size: 0.85rem;
  color: #606060;
  margin-bottom: 2px;
}

.ytc-meta {
  font-size: 0.8rem;
  color: #909090;
}
</style>
