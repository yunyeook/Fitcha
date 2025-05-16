<template>
  <div class="challenge-detail__container">
    <img
      src="../../assets/images/run.jpg"
      alt="러닝 이미지"
      class="challenge-detail__image"
    />

    <div class="challenge-detail__content">
      <div class="challenge-detail__header">
        <h2>🏃‍♂️ 30일 아침 러닝 챌린지</h2>
        <div class="challenge-detail__options">
          <i class="fas fa-ellipsis-v"></i>
        </div>
      </div>

      <div class="challenge-detail__badges">
        <span class="challenge-detail__badge running"
          ><i class="fas fa-person-running"></i> 러닝</span
        >
        <span class="challenge-detail__badge location"
          ><i class="fas fa-map-marker-alt"></i> 서울</span
        >

        <span class="challenge-detail__badge hard"
          ><i class="fas fa-star"></i> 초보자 가능</span
        >
      </div>

      <p class="challenge-detail__desc">
        매일 아침 6시에 함께 러닝할 사람을 모집합니다. 서로 응원하며 건강한
        하루를 시작해요!
      </p>

      <div class="challenge-detail__progress">
        <div class="challenge-detail__progress-bar">
          <div class="challenge-detail__progress-fill" style="width: 50%"></div>
        </div>
        <p class="challenge-detail__participants">참여: 5 / 10명</p>
      </div>

      <div class="challenge-detail__avatars-section">
        <div class="challenge-detail__host">
          <img src="https://via.placeholder.com/32" alt="Host" />
          <span class="challenge-detail__host-badge">방장</span>
        </div>
        <div class="challenge-detail__avatar-stack">
          <img src="https://via.placeholder.com/32/FF5733" />
          <img src="https://via.placeholder.com/32/33C3FF" />
          <img src="https://via.placeholder.com/32/85FF33" />
          <img src="https://via.placeholder.com/32/FF33A6" />
          <img src="https://via.placeholder.com/32/FFD433" />
          <span class="challenge-detail__more-count">+5</span>
        </div>
      </div>

      <div class="challenge-detail__actions">
        <button class="challenge-detail__join-btn">
          <i class="fas fa-sign-in-alt"></i> 참여하기
        </button>
        <button class="challenge-detail__certify-btn">
          <a href="../views/registFitLog.html" style="text-decoration: none">
            <i class="fas fa-pen"></i> 인증글 쓰기
          </a>
        </button>
      </div>

      <div class="challenge-detail__meta">
        <span>2025년 4월 29일</span>
        <div class="challenge-detail__meta-right">
          <span>댓글 2개</span>
          <span class="challenge-detail__likes"
            ><i class="fas fa-heart"></i> 151명</span
          >
        </div>
      </div>

      <!-- 탭 섹션 시작 -->
      <div class="challenge-detail__tab-section">
        <div class="challenge-detail__tabs">
          <div class="challenge-detail__tab active" data-tab="comments">
            댓글 보기
          </div>
          <div class="challenge-detail__tab" data-tab="certs">인증글 보기</div>
        </div>

        <!-- 댓글 탭 -->
        <div class="challenge-detail__tab-content active" id="comments">
          <div class="challenge-detail__comment-form">
            <input type="text" placeholder="댓글을 남기세요..." />
            <button>작성</button>
          </div>
          <div class="challenge-detail__comment">
            <img src="https://via.placeholder.com/36/FF5733" />
            <div class="challenge-detail__comment-body">
              <div>
                <div class="challenge-detail__comment-author">사용자1</div>
                <div class="challenge-detail__comment-text">
                  저도 참가할게요! 매일 아침 달리기 기대돼요.
                </div>
                <div class="challenge-detail__comment-date">2025년 5월 5일</div>
              </div>
              <div class="challenge-detail__options">
                <i class="fas fa-ellipsis-v"></i>
              </div>
            </div>
          </div>
          <!-- 추가 댓글... -->
        </div>

        <!-- 인증글 탭 -->
        <div class="challenge-detail__tab-content" id="certs">
          <div class="challenge-detail__certification-item">
            <div class="challenge-detail__cert-header">
              <div class="challenge-detail__cert-author">
                <img src="https://via.placeholder.com/32/FF5733" />
                <span class="author">러너1</span>
              </div>
              <span class="date">5월 10일</span>
            </div>
            <div class="challenge-detail__cert-body">
              오늘도 5km 완주했어요! 상쾌한 하루 시작 💪
            </div>
          </div>
        </div>
      </div>

      <a href="#" class="challenge-detail__back">
        <i class="fas fa-arrow-left"></i> 뒤로 가기
      </a>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
/* 챌린지 카드 디테일 디자인 */

.challenge-detail__container {
  max-width: 640px;
  margin: 0 auto;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 0 12px rgba(0, 0, 0, 0.05);
  overflow: hidden;
}

.challenge-detail__image {
  width: 100%;
  height: 330px;
  object-fit: cover;
}

.challenge-detail__content {
  padding: 20px;
}

.challenge-detail__header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 1.3rem;
  font-weight: 500;
}
.challenge-detail__header h2 {
  margin: 0px;
  font-size: 1.3rem;
}

.challenge-detail__badges {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin: 12px 0;
}

.challenge-detail__badge {
  padding: 4px 10px;
  border-radius: 12px;
  font-size: 12px;
  color: #ffffff;
}

.challenge-detail__badge.running {
  background-color: #3cb371;
}
.challenge-detail__badge.location {
  background-color: #4dabf7;
}
.challenge-detail__badge.hard {
  background-color: #ffa94d;
}

.challenge-detail__desc {
  margin-top: 20px;
  margin-bottom: 16px;
  line-height: 1.5;
  font-size: 1rem;
  color: #495057;
}

.challenge-detail__progress {
  margin: 30px 0 16px;
}

.challenge-detail__progress-bar {
  background: #e0e0e0;
  height: 8px;
  border-radius: 4px;
  overflow: hidden;
}

.challenge-detail__progress-fill {
  height: 100%;
  background: #4caf50;
}

.challenge-detail__participants {
  font-size: 14px;
  color: #555;
  margin-top: 6px;
}

/* 아바타 */
.challenge-detail__avatars-section {
  display: flex;
  gap: 15px;
  align-items: center;
  margin-top: 18px;
}

.challenge-detail__host {
  display: flex;
  align-items: center;
  gap: 6px;
  background-color: #e3fafc;
  padding: 4px 10px;
  border-radius: 20px;
}

.challenge-detail__host img {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  border: 2px solid #0c8599;
}

.challenge-detail__host-badge {
  background-color: #0c8599;
  color: #fff;
  font-size: 11px;
  font-weight: 600;
  padding: 3px 6px;
  border-radius: 12px;
}

.challenge-detail__avatar-stack {
  display: flex;
  position: relative;
}

.challenge-detail__avatar-stack img {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  border: 2px solid #fff;
  margin-left: -10px;
  background-color: #eee;
  box-shadow: 0 0 0 1px #ccc;
}

.challenge-detail__avatar-stack img:first-child {
  margin-left: 0;
}

.challenge-detail__more-count {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background: #dee2e6;
  color: #495057;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 6px;
  font-size: 13px;
  font-weight: bold;
}

/* 버튼 */
.challenge-detail__actions {
  display: flex;
  gap: 12px;
  margin: 16px 0;
}

.challenge-detail__actions button {
  flex: 1;
  padding: 10px;
  font-size: 14px;
  font-weight: bold;
  border: none;
  border-radius: 10px;
  color: #fff;
  cursor: pointer;
}

.challenge-detail__join-btn {
  background: #51cf66;
}

.challenge-detail__join-btn:hover {
  background: #40c057;
}

.challenge-detail__certify-btn {
  background: #339af0;
}

.challenge-detail__certify-btn:hover {
  background: #228be6;
}

/* 메타 정보 */
.challenge-detail__meta {
  display: flex;
  justify-content: space-between;
  font-size: 13px;
  color: #777;
}

.challenge-detail__likes {
  margin-left: 12px;
  color: #e03131;
}

/* 탭 섹션 */
.challenge-detail__tab-section {
  background: #f9f9f9;
  border-radius: 12px;
  border: 1px solid #e5e5e5;
  margin-top: 24px;
  overflow: hidden;
}

.challenge-detail__tabs {
  display: flex;
  background: #f1f3f5;
  border-bottom: 1px solid #ddd;
}

.challenge-detail__tab {
  flex: 1;
  padding: 12px;
  text-align: center;
  cursor: pointer;
  font-weight: bold;
  color: #666;
  transition: all 0.2s ease;
}

.challenge-detail__tab:hover {
  background: #e7f5ff;
  color: #1c7ed6;
}

.challenge-detail__tab.active {
  background: #fff;
  color: #1c7ed6;
  border-bottom: 3px solid #1c7ed6;
}

.challenge-detail__tab-content {
  display: none;
  padding: 20px;
  background: #fff;
}

.challenge-detail__tab-content.active {
  display: block;
}

/* 댓글 영역 */
.challenge-detail__comment-form {
  display: flex;
  gap: 8px;
  margin-bottom: 20px;
}

.challenge-detail__comment-form input {
  flex: 1;
  padding: 10px;
  border-radius: 8px;
  border: 1px solid #ccc;
}

.challenge-detail__comment-form button {
  padding: 10px 16px;
  background: #51cf66;
  color: #fff;
  font-weight: bold;
  border-radius: 8px;
  border: none;
  cursor: pointer;
}

.challenge-detail__comment {
  display: flex;
  gap: 10px;
  margin-bottom: 16px;
}

.challenge-detail__comment img {
  width: 36px;
  height: 36px;
  border-radius: 50%;
}

.challenge-detail__comment-body {
  flex: 1;
  display: flex;
  justify-content: space-between;
}

.challenge-detail__comment-author {
  font-size: 0.9rem;
  margin-bottom: 4px;
}

.challenge-detail__comment-text {
  margin-bottom: 4px;
  font-size: 0.8rem;
  color: #444;
}

.challenge-detail__comment-date {
  font-size: 12px;
  color: #888;
}

/* 인증글 */
.challenge-detail__certification-item {
  background: #fff;
  border: 1px solid #dee2e6;
  border-radius: 8px;
  padding: 14px;
  margin-bottom: 16px;
}

.challenge-detail__cert-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
}

.challenge-detail__cert-author {
  display: flex;
  align-items: center;
  gap: 6px;
}

.challenge-detail__cert-author img {
  width: 32px;
  height: 32px;
  border-radius: 50%;
}

.challenge-detail__cert-body {
  font-size: 14px;
  line-height: 1.5;
}

/* 뒤로가기 */
.challenge-detail__back {
  display: inline-flex;
  align-items: center;
  margin-top: 24px;
  text-decoration: none;
  color: #444;
  gap: 6px;
}
</style>
