<template>
  <!-- 챌린지 게시글 카드 구조 -->
  <div class="challenge-card">
    <a style="text-decoration: none" href="../views/challengeFitDetail.html">
      <!-- 챌린지 카드 이미지 -->
      <img src="../../assets/images/run.jpg" alt="" />
      <!-- 챌린지 카드 내용 -->
      <div class="card-content">
        <h3>30일 아침 러닝 챌린지!!</h3>
        <div class="card-badges">
          <span class="badge activity"><i class="fas fa-running"></i>러닝</span>
          <span class="badge location"
            ><i class="fas fa-map-marker-alt"></i> 서울</span
          >
          <span class="badge level"><i class="fas fa-star"></i> 초보</span>
        </div>
        <!-- 챌린지 게시글 내용 -->
        <p class="card-desc">
          매일 아침 6시에 함께 러닝할 사람 모집합니다. 시작은 힘들지만 끝은
          상쾌해요! 서울 근방 러너 환영! 혼자 달리기 어렵다면 함께 해봐요 💪
        </p>
        <!-- 참여 인원 프로그래스바 -->
        <div class="progress-container">
          <div class="progress-bar">
            <div class="fill"></div>
          </div>
        </div>

        <p class="participants">참여: 5 / 10명</p>
        <p class="date">
          2025년 4월 29일 · <span class="comment-count">2</span>개의 댓글
        </p>
        <div class="card-footer">
          <div class="writer-info">
            <img src="../../assets/images/run.jpg" alt="" />
            <span>게시글 작성자</span>
          </div>
          <div class="like">
            <i class="fas fa-heart"></i>
            <span>12</span>
          </div>
        </div>
      </div>
    </a>
  </div>
</template>

<script setup></script>

<style scoped>
/* 챌린지 카드 디자인 */

.challenge-card {
  font-family: "Arial", sans-serif;
  background-color: #ffffff;
  width: 280px;
  height: 405px;
  border-radius: 15px;
  overflow: hidden;
  transition: all 0.3s ease;
  box-shadow: 0px 6px 20px rgba(0, 0, 0, 0.15);
  cursor: pointer;
}

.challenge-card:hover {
  transform: translateY(-4px);
}

.challenge-card img {
  width: 100%;
  height: 160px;
  object-fit: cover;
}

.challenge-card .card-content {
  padding: 12px 16px;
}

.card-content h3 {
  margin: 0px;
  font-size: 1rem;
  font-weight: bold;
  margin-bottom: 10px;
  color: #222;
}

.card-content .card-badges {
  margin-bottom: 12px;
}

.card-badges .badge {
  display: inline-block;
  color: #ffffff;
  padding: 2px 8px;
  border-radius: 5px;
  font-size: 0.65rem;
  margin-right: 7px;
}

.badge.activity {
  background-color: #3cb371; /* 활동 */
}

.badge.location {
  background-color: #4dabf7; /* 지역 */
}

.badge.level {
  background-color: #ffa94d; /* 난이도 */
}

.badge i {
  margin-right: 4px;
}

.card-desc {
  margin: 0px;
  font-size: 0.8rem;
  color: #555;
  margin-bottom: 10px;
  line-height: 1.4;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}

.progress-container {
  margin-bottom: 8px;
}

.progress-container .progress-bar {
  height: 8px;
  background-color: #eee;
  border-radius: 5px;
  overflow: hidden;
}

.progress-bar .fill {
  height: 100%;
  background-color: #3cb371;
  width: 50%;
  transition: width 0.3s;
}

.participants {
  margin: 0px;
  font-size: 0.75rem;
  color: #444;
  margin-bottom: 8px;
}

.date {
  margin: 0px;
  font-size: 0.7rem;
  color: #888;
  margin-bottom: 12px;
}

.card-footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 8px 14px;
  border-top: 1px solid #eee;
  font-size: 0.7rem;
  color: #666;
}

.card-footer .writer-info {
  display: flex;
  align-items: center;
  gap: 8px;
}

.card-footer .writer-info img {
  width: 24px;
  height: 24px;
  border-radius: 50%;
}

.card-footer .like {
  display: flex;
  align-items: center;
  gap: 4px;
}

.like i {
  color: #ff6b6b;
}
</style>
